import java.sql.Date;
import java.time.LocalDate;

public class Main {

    private final String[] errors = {"email", "password", "username"};

    public void checkValidity(boolean bool, int index){
        if (!bool){
            System.out.println("wrong format: " + errors[index]);
        }
    }
    public static void main(String[] args) throws Exception {
        Main man = new Main();

        LocalDate date = LocalDate.of(2001,12,11);
        Date sqlDate = Date.valueOf(date);

        signUp signup = new signUp("Mohammad ishaq ", sqlDate, "Afghanistan", "Male", "shaqinair", "house1231", "khan@mail.com");
        signup.connect();
        
        System.out.println(signup.inputValidity_email());
        signup.inputValidity_name();
        System.out.println(signup.inputValidity_pass());
        System.out.println(signup.inputValidity_usrname());
        System.out.println(signup.check4email());
        System.out.println(signup.check4usrname());

        if (signup.inputValidity_email() || signup.inputValidity_pass() || signup.inputValidity_usrname()){
            if (signup.check4email() && signup.check4email()){
                System.out.println("User already exist. (try a new username/email or both)");
            }else{
                boolean isUserSaved = signup.saveUser();
                if (isUserSaved){
                System.out.println("everythig good, saving...");
                }else{
                    System.out.println("Something went wrong!");
                }
            }

        }else{
            man.checkValidity(signup.inputValidity_email(), 0);
            man.checkValidity(signup.inputValidity_pass(), 1);
            man.checkValidity(signup.inputValidity_usrname(), 2);

        }




        signup.disconnect();
    }
}
