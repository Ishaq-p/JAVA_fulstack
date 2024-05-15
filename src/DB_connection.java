// import java.time.LocalDate;
import java.sql.*;

// needed for storing the credentials safely
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DB_connection {
    private String username;
    private String pass;
    private String url;
    public Connection connection;
    public boolean isLoggedIn = false;

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
        System.out.println(query);
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            System.out.println(query);
            return resultSet.next();
        }catch (SQLException e){
                e.printStackTrace();
                return false;
        }
    }

    // check for user's username and password in the DB
    public boolean loginUser(String username, String password){
        String query = String.format("SELECT * FROM CustomerInfo WHERE username = '%s' AND password = '%s';", username, password);
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
    public boolean save_SignUp_user(String name, Date dob, String country, String gender, String usrname, String passcode, String email){
        String query = "INSERT INTO `CustomerInfo` (`customerID`, `fulname`, `Nationality`, `DOB`, `gender`, `username`, `password`, `email`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
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
    // getting info about a single flight using its flightID
    public String[] flightIDsearch(String flight_id){
        String query = String.format("SELECT * FROM Flights WHERE flightID='%s';", flight_id);
        try(Statement statement = connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery(query)){

            List<String> flightsInfo = new ArrayList<>();

            String[] columns = {"flightID", "from_", "to_", "planeID", "departureTime", "flightDuration", "E_seatsLeft", "B_seatsLeft", "destinationType"};
            
            while (resultSet.next()){
                for (String column : columns){
                    flightsInfo.add(resultSet.getString(column));
                }
            }
            return flightsInfo.toArray(new String[0]);
        }catch(SQLException e){
            e.printStackTrace();
            return new String[0];
        }
    }

    public String username2customerID(String username){
        String query = String.format("SELECT customerID FROM CustomerInfo WHERE username='%s';", username);
        try(Statement statement = connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery(query)){
                if (resultSet.next()){
                    return resultSet.getString("customerID");
                }else{
                    return "";
                }
            }catch (SQLException e){
                e.printStackTrace();
                return "";
            }
    }

    protected boolean cutomerPurchasedFlight(String customerID, String flightID, int E_class, int numSeats){
        String query = String.format("INSERT INTO `FlightCustomers` (`customerID`, `flightID`, `class_E`, `num_seats`) VALUES ('%s', '%s', '%s', '%s');", customerID, flightID, E_class, numSeats);
        try (PreparedStatement statement = connection.prepareStatement(query)){
            int rowsInserted = statement.executeUpdate();
            
            if (rowsInserted>0){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    
    protected boolean flightsUpdate(String flightID, int E_class, int num_seats){
        String query = (E_class==1) ? String.format("UPDATE `Flights` SET `E_seatsLeft` = `E_seatsLeft` - %s WHERE `flightID`='%s';", num_seats, flightID) : String.format("UPDATE `Flights` SET `B_seatsLeft` = `B_seatsLeft` - %s WHERE `flightID`='%s';", num_seats, flightID);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            int rowsUpdated = statement.executeUpdate();
            if(rowsUpdated>0){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
        
    } 

    // public static void main(String[] args){
    //     DB_connection connection = new DB_connection();

    //     LocalDate localDate = LocalDate.of(2024,5,3);
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



        // String[] flightIDs = connection.flightSearch("IST", "LHR", sqlDate, false, 2);
        // for (String flight : flightIDs){
        //     String[] flightInfo = connection.flightIDsearch(flight);
        //     for (String i : flightInfo){
        //         System.out.println(i);
        //     }
        //     System.out.println("\n");
        // }
        // String[] flightIDs2 = connection.flightSearch("LHR", "IST", sqlDate, false, 2);
        // for (String flight : flightIDs2){
        //     String[] flightInfo = connection.flightIDsearch(flight);
        //     for (String i : flightInfo){
        //         System.out.println(i);
        //     }
        //     System.out.println("\n");
        // }


    //     System.out.println(connection.cutomerPurchasedFlight("root", "FLIGHT004", 1, 3));
    //     System.out.println(connection.flightsUpdate("FLIGHT004", 1, 3));
    //     connection.disconnect();

    // }


}
