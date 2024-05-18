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
    public boolean check4user(String keyAttribute){
        String query = String.format("SELECT * FROM CustomerInfo WHERE email = '%s';", keyAttribute);
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

    public int getID(String email){
        String query = String.format("SELECT id FROM CustomerInfo WHERE email = '%s';", email);
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            resultSet.next();
            return resultSet.getInt("id");
        }catch (SQLException e){
                e.printStackTrace();
                return 0;
        }
    }
    public int getID_byusername(String username){
        String query = String.format("SELECT id FROM CustomerInfo WHERE username = '%s';", username);
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            resultSet.next();
            return resultSet.getInt("id");
        }catch (SQLException e){
                e.printStackTrace();
                return 0;
        }
    }
    public int getID_ticketHolder(String email){
        String query = String.format("SELECT id FROM `TicketHolders` WHERE email = '%s';", email);
        System.out.println(query);
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query)){
            System.out.println(query);
            resultSet.next();
            return resultSet.getInt("id");
        }catch (SQLException e){
                e.printStackTrace();
                return 0;
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
    public boolean save_SignUp_user(String name, Date dob, String country, String gender, String usrname, String passcode, String email, int isTurk){
        String query = "INSERT INTO `CustomerInfo` (`fulname`, `Nationality`, `DOB`, `gender`, `username`, `password`, `email`, `isTurk`) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, name);
            statement.setString(2, country);
            statement.setDate(3, dob);
            statement.setString(4, gender);
            statement.setString(5, usrname);
            statement.setString(6, passcode);
            statement.setString(7, email);
            statement.setInt(8, isTurk);
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

    public boolean save_passenger_info(String name, Date dob, String title, String email, int isTurk){
        System.out.println(title);
        String query = "INSERT INTO `TicketHolders` (`title`, `fulName`, `dob`, `email`, `isTurk`) VALUES (?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, title);
            statement.setString(2, name);
            statement.setDate(3, dob);
            statement.setString(4, email);
            statement.setInt(5, isTurk);
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

 
    public String[] flightSearch(String from, String to, Date date1, int passengersNum){
        String query = "SELECT flightID FROM Flights WHERE from_ = ? AND to_ = ? AND departureTime >= ? AND (B_seatsLeft >= ? OR B_seatsLeft >= ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setDate(3, date1);
            statement.setInt(4, passengersNum);
            statement.setInt(5, passengersNum);

            try (ResultSet resultSet = statement.executeQuery()) {
            List<String> flights = new ArrayList<>();
            while (resultSet.next()) {
                // Add relevant flight information to the flights list
                flights.add(resultSet.getString("flightID"));
            }
            return flights.toArray(new String[0]);
        }
        }catch (SQLException e){
            e.printStackTrace();
            return new String[0];
        }
    }

    // getting info about a single flight using its flightID
    public String[] flightIDsearch(String flight_id){
        String query = String.format("SELECT * FROM Flights WHERE flightID='%s';", flight_id);
        try(Statement statement = connection.createStatement(); 
            ResultSet resultSet = statement.executeQuery(query)){

            List<String> flightsInfo = new ArrayList<>();

            String[] columns = {"flightID", "from_", "to_", "planeID", "departureTime", "flightDuration", "E_seatsLeft", "B_seatsLeft", "destinationType", "ecoPrice", "buisPrice"};
            
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

    // public String username2customerID(String username){
    //     String query = String.format("SELECT customerID FROM CustomerInfo WHERE username='%s';", username);
    //     try(Statement statement = connection.createStatement(); 
    //         ResultSet resultSet = statement.executeQuery(query)){
    //             if (resultSet.next()){
    //                 return resultSet.getString("customerID");
    //             }else{
    //                 return "";
    //             }
    //         }catch (SQLException e){
    //             e.printStackTrace();
    //             return "";
    //         }
    // }

    protected boolean cutomerPurchasedFlight(String customerID, String flightID, int E_class){
        String query = String.format("INSERT INTO `FlightCustomers` (`ticket_holder`, `customerID`, `flightID`, `class_E`) VALUES ('%s', '%s', '%s', '%s');", customerID, flightID, E_class);
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


    public boolean saveCard(int buyerID, String name, String phone, String cardNumber, String expDate, String cvv) {
        String query = "INSERT INTO CardInfo (customerID, fulName, phone, cardNumber, expDate, CVV) VALUES (?, ?, ?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, buyerID);
            statement.setString(2, name);
            statement.setString(3, phone);
            statement.setString(4, cardNumber);
            statement.setString(5, expDate);
            statement.setString(6, cvv);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean save2FlihgtCustm(int tktHolder, int custID, String flightID, int class_E){
        String query = "INSERT INTO FlightCustomers (ticket_holder, customerID, flightID, class_E) VALUES (?, ?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, tktHolder);
            statement.setInt(2, custID);
            statement.setString(3, flightID);
            statement.setInt(4, class_E);
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static void main(String[] args){
        DB_connection connection = new DB_connection();

    //     LocalDate localDate = LocalDate.of(2024,5,3);
    //     Date sqlDate = Date.valueOf(localDate);
    //     connection.connect();


        connection.connect();
        System.out.println(connection.getID("dsfdvd@kljfd.vd"));
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
        connection.disconnect();

    }


}
