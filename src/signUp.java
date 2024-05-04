import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.DocFlavor.STRING;


public class signUp  extends DB_connection{

    private static final String email_regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern_email = Pattern.compile(email_regex);

    private static final String username_regex = "^[a-zA-Z0-9_]{3,16}$";
    private static final Pattern pattern_username = Pattern.compile(username_regex);

    private String fulName;
    private Date DOB;
    private String nationality;
    private String username;
    private String password;
    private String email;

    public signUp(String name, Date dob_, String country, String usrname, String passcode, String Email){
        super();
        this.fulName = name;
        this.DOB = dob_;
        this.nationality = country;
        this.username = usrname;
        this.password = passcode;
        this.email = Email;
    }

    public boolean inputValidity_email(){
        Matcher match = pattern_email.matcher(this.email);
        return match.matches();
    }
    public boolean inputValidity_usrname(){
        Matcher match = pattern_username.matcher(this.username);
        return match.matches();
    }
    public boolean inputValidity_pass(){
        if (this.password.length()<7){
            return false;
        }else{
            return true;
        }
    }
    public String inputValidity_name(){
        String final_fulname = "";
        String[] fulname_list = this.fulName.split("\\s+"); // one or more spaces
        for (String i : fulname_list){
            final_fulname += " " + i.replaceAll("^\\s+|\\s+$", "");
        }
        return final_fulname.replaceAll("^\\s+|\\s+$", "");
    }

    // public String check4usrname(){} // done by the database object
    // public String check4email(){}   //done by the database obj


    public static void main(String[] args){
        signUp sign = new signUp("username_regex", null, "email_regex", "username_regex", "username_regex", "email_regex");

        String name = sign.inputValidity_name();
        System.out.println(name);
    }
    
}
