import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
// import java.util.Date;
import java.sql.Date;
import java.sql.ResultSetMetaData;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DB_connection {
    private String username;
    private String pass;
    private String url;
    public Connection connection;

    private Properties properties;

    // constructor
    public DB_connection(){
        this.properties = new Properties();
        try (FileInputStream fis = new FileInputStream("src/db_config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.username = properties.getProperty("db.username");
        this.pass = properties.getProperty("db.password");
        this.url = properties.getProperty("db.url");
    }

    // connection to the database
    public void connect(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
            return;
        }
        try{
            connection = DriverManager.getConnection(url, 
                                                    username, 
                                                    pass);
            System.out.println("Connected!!");
        }catch (SQLException e) {
            System.out.println("Connection Failed!!");
            e.printStackTrace();
        }
        
    }

    // discontinuing the connection
    public void disconnect(){
        try{
            if (connection!=null && !connection.isClosed()){
                connection.close();
                System.out.println("Connection Closed Successfuly!");
            }else{
                System.out.println("Already Closed.");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    // checkinkg for users either by their email or username
    public boolean check4user(String keyAttribute, boolean isEmail){
        String query = (isEmail) ? String.format("SELECT * FROM CustomerInfo WHERE email = '%s';", keyAttribute) : String.format("SELECT * FROM CustomerInfo WHERE username = '%s';", keyAttribute);
        // System.out.println(query);
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            return resultSet.next();
        }catch (SQLException e){
                e.printStackTrace();
                return false;
        }
    }

    // saving user's sigin up data
    public boolean save_SignUp_user(String name, java.sql.Date dob, String country, String gender, String usrname, String passcode, String email){
        String query = "INSERT INTO `CustomerInfo` (`customerID`, `fulname`, `Nationality`, `DOB`, `gender`, `username`, `password`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, usrname);
            statement.setString(2, name);
            statement.setString(3, country);
            statement.setDate(4, dob);
            statement.setString(5, gender);
            statement.setString(6, usrname);
            statement.setString(7, passcode);
            statement.setString(8, email);
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
               return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    // public static void main(String[] args){
    //     DB_connection connection = new DB_connection();

    //     LocalDate localDate = LocalDate.of(2003,5, 6);
    //     Date sqlDate = Date.valueOf(localDate);
    //     connection.connect();
    //     connection.connect();
    //     boolean bool = connection.check4user("ashleyalvarez58@gmail.com", true);
    //     System.out.println(bool);

    //     boolean bool1 = connection.save_SignUp_user("ishaq", sqlDate, "Afghanistan", "Male", "ishaqpaktin", "null", "null@null.com");
    //     System.out.println(bool1);

    //     connection.disconnect();
    //     connection.disconnect();
    //     connection.connect();
    //     connection.disconnect();


    // }


}
