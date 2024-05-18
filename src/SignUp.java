import java.sql.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import UserInterface.Alert;
import UserInterface.SignUpPage;

import java.sql.Date;

// import javax.print.DocFlavor.STRING;

public class SignUp  extends DB_connection{

    private static final String email_regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    private static final Pattern pattern_email = Pattern.compile(email_regex);

    private static final String username_regex = "^[a-zA-Z0-9_]{3,16}$";
    private static final Pattern pattern_username = Pattern.compile(username_regex);

    private String fulName;
    private Date DOB;
    private String nationality;
    private String gender;
    private String username;
    private String password;
    private String email;

    public int id;

    private SignUpPage signUpPage = new SignUpPage();

    public SignUp(){}

    public SignUp(String fulname, Date dob, String country, String title, String username, String passcode, String email){
        super();
        this.fulName = fulname;
        this.DOB = dob;
        this.nationality = country;
        this.gender = (title=="Mr.") ? "Male" : "Female";
        this.username = username;
        this.password = passcode;
        this.email = email;
    }

    public boolean inputValidity_email(){
        System.out.println(this.email);
        Matcher match = pattern_email.matcher(this.email);
        return match.matches();
    }
    public boolean inputValidity_email(String email){
        System.out.println(email);
        Matcher match = pattern_email.matcher(email);
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
    public void inputValidity_name(){
        String final_fulname = "";
        String[] fulname_list = this.fulName.split("\\s+"); // one or more spaces
        for (String i : fulname_list){
            final_fulname += " " + i.replaceAll("^\\s+|\\s+$", "");
        }
        this.fulName = final_fulname.replaceAll("^\\s+|\\s+$", "");
    }

    public boolean check4email(){
        connect();
        boolean isUserExist = check4user(this.email);
        disconnect();
        return isUserExist;
    }   //done by the database obj
    
    public boolean saveUser(){
        connect();
        int isTurk = (this.nationality=="Turkey" || this.nationality=="turkey") ? 1:0;
        boolean isSaved = save_SignUp_user(this.fulName, 
                                            this.DOB, 
                                            this.nationality, 
                                            this.gender, 
                                            this.username, 
                                            this.password, 
                                            this.email,
                                            isTurk);
        disconnect();
        return isSaved;
    }

    public boolean submitSignup(){
        System.out.println("email: "+this.email);
        if (this.inputValidity_email()){
            this.inputValidity_name();
            if (this.check4email()){
                JOptionPane.showMessageDialog(null,"User already exists", "Already Exists", JOptionPane.ERROR_MESSAGE);
                return false;
            }else{
                this.saveUser();
                JOptionPane.showMessageDialog(null,"User Saved", "Info", JOptionPane.INFORMATION_MESSAGE);
                return true;
            }
        }else{
            JOptionPane.showMessageDialog(null,"Not a valid email", "Email validity", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public int setID(String email){
        connect();
        int id = getID(email);
        System.out.println("signUp: "+id+" "+email);
        disconnect();
        return id;
    }


    // public static void main(String[] args){
    //     @SuppressWarnings("deprecation")
    //     Date date = new Date(22, 2, 2);
    //     SignUp sign = new SignUp("hell khan", date, "email_regex", "username_regex", "username_regex", "email_regex", "cds@vd.vd");
    //     sign.setID();
    //     System.out.println("this is: "+sign.id);
    // }
    
}
