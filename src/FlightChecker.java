import java.sql.Date;
import java.time.LocalDate;

public class FlightChecker extends DB_connection{
    private String from, to;
    private boolean roundTrip, Eclass_bool;
    private Date date1, date2;
    private int passengersNum, Eclass_int;
    private String customerID;
    private String username;
    private String flightID;

    public FlightChecker(){}
    // constructor, just to assign the core variables
    public FlightChecker(String from, String to, Date date1, Date date2,  int passengersNum){
        super();
        this.from = from;
        this.to = to;
        this.date1 = date1;
        this.date2 = (this.roundTrip) ? date2 : null;
        this.passengersNum = passengersNum;
    }

    // seach for oneway and return flights
    public String[] searchFlights_oneway(){
        connect();
        String[] flightsFound1 = flightSearch(this.from, this.to, this.date1, this.passengersNum);
        disconnect();
        return flightsFound1;
    }
    public String[][] searchFlights_round(){
        connect();
        String[] flightsFound1 = flightSearch(this.from, this.to, this.date1, this.passengersNum);
        String[] flightsFound2 = flightSearch(this.to, this.from, this.date2, this.passengersNum);
        disconnect();
        return new String[][] {flightsFound1, flightsFound2};
    }
    
    // to see if one available
    public boolean oneWay_flight(){
        connect();
        String[] flightsFound = flightSearch(this.from, this.to, this.date1, this.passengersNum);
        disconnect();
        if (flightsFound.length>0){
            return true;
        }else{
            return false;
        }
    }
    
    // to see if the return one is available
    public boolean return_flight(){
        String[] flightsFound = flightSearch(this.to, this.from, this.date2, this.passengersNum);
        if (flightsFound.length>0){
            return true;
        }else{
            return false;
        }
    }
    
    // flight details (suppose to use independent flight IDs since we will have many(oneway, round-way))
    public String[] flightDetails(String flight_id){
        connect();
        String[] details = flightIDsearch(flight_id); 
        disconnect();  
        return details;
    }
    
    // has to be logged in before using this method !!!!
    // getting the customerID using username
    public void user2cusID(){
        this.customerID = this.username2customerID(this.username);  
    }

    // see if the amount of seats available
    public boolean seatsAvailability(){
        String[] flighInfo = flightDetails(this.flightID);
        if (flighInfo.length>0){
            int numOfTotalSeats = (this.Eclass_bool) ? Integer.parseInt(flighInfo[6]) : Integer.parseInt(flighInfo[7]);
            return (numOfTotalSeats>=passengersNum) ? true : false;
        }else{
            return false;
        }
    }

    // the final process where the purchase is stored for the user
    // and the amount of seats are deducted from the Flights table row element
    public boolean finalizePurchase(){
        boolean custPurchase_bool = this.cutomerPurchasedFlight(customerID, flightID, Eclass_int, passengersNum);
        boolean  flightsTableUpdate = this.flightsUpdate(flightID, Eclass_int, passengersNum);
        if (custPurchase_bool && flightsTableUpdate){
            return true;
        }else{
            System.out.printf("customerID: %s \t flightID: %s;", customerID, flightID);
            return false;
        }
    }

    // the final, where all the process are combined
    public void bookFlights(String flightID, String username, int E_class, int passengersNum){
        this.username = username;
        this.flightID = flightID;
        user2cusID();
        boolean isSeatAvail = seatsAvailability();
        if (isSeatAvail){
            boolean  finalize = finalizePurchase();
            if (finalize){
                System.out.println("Purchase process done, seats reserved!!");
            }else{
                System.out.println("somthing went wrong!!");
            }
        }else{
            System.out.println("not enough seats available!!");
        }
    }

    // public static void main(String[] args){
    //     LocalDate localDate = LocalDate.of(2024,5,3);
    //     Date sqlDate = Date.valueOf(localDate);

    //     FlightChecker flight = new FlightChecker("IST", "LHR", false, sqlDate, null, false, 3);

    //     flight.connect();
        
    //     // for (String i : flight.flightDetails(flight.fli))
    //     // System.out.println(flight.flightDetails(null));

    //     flight.disconnect();
    // }


}
