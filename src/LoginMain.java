public class LoginMain {
    

    public static void main(String[] args){
        Login login = new Login("shaqina", "house1231");

        login.connect();
        boolean isLogged = login.logingIn();

        if (isLogged){
            System.out.println("Logged in!");
        }else{
            System.out.println("username and password doesn't match!!");
        }

        if(login.isLoggedIn){
            System.out.println("user already logged in!");
        }else{
            System.out.println("user not logged in.");
        }

        login.disconnect();

    }
}
