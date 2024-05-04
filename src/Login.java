

public class Login extends DB_connection{

    private String username;
    private String password;

    public Login(String usrname, String passcode){
        super();
        this.username = usrname;
        this.password = passcode;
    }

    public boolean logingIn(){
        boolean loginBool = loginUser(username, password);
        isLoggedIn = (loginBool) ? true : false;      // the original variable resides in DB_connection
        return loginBool;
    }
    
}
