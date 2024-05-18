package UserInterface;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame{
    public String user_name; 
    public String passcode; 

    private JPasswordField password = new JPasswordField();
    private JTextField username = new JTextField();
    public JButton submit = new JButton("Submit"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(new Color(199, 10, 15)); setForeground(Color.white);setPreferredSize(new Dimension(300,60));}};
    private JFrame frame = new JFrame();

    // void constructor
    public LoginPage(){}

    // the popup login page
    public void loginPage(){
        
        JPanel p_login = new JPanel(new GridBagLayout()){{setBackground(Color.WHITE);}};

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets = new Insets(10,10,10,10);
        
        // label for the header
        JPanel p_header = new JPanel(new FlowLayout(FlowLayout.CENTER)){{setBackground(Color.WHITE);}};
        JLabel header = new JLabel("LogIn");
        header.setFont(new Font("MuseoSans-900", Font.BOLD, 40));
        header.setHorizontalAlignment(JLabel.CENTER);
        p_header.add(header);

        p_login.add(p_header, gbc);

        JPanel miniPanel = new JPanel(new GridBagLayout()){{setBackground(Color.WHITE);}};
        gbc.gridy++;
        JLabel lbl_username = new JLabel("username:");
        lbl_username.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(lbl_username, gbc);

        gbc.gridy++;
        username.setPreferredSize(new Dimension(300, 50));
        username.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(username, gbc);

        gbc.gridy++;
        JLabel lbl_password = new JLabel("password:"){{setBackground(Color.WHITE);}};
        lbl_password.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(lbl_password, gbc);

        gbc.gridy++;
        password.setPreferredSize(new Dimension(300, 50));
        password.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(password, gbc);

        gbc.gridy++;
        submit.setPreferredSize(new Dimension(300, 50));
        submit.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(submit, gbc);

        // Add the header label to the login panel
        p_login.add(miniPanel, gbc);
        
        add(p_login);
        pack();
        setTitle("Login main page");
        setSize(400, 500); // Setting a default size for demonstration
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public void storeValues(){
        this.user_name = this.username.getText();
        this.passcode =  new String(this.password.getPassword());
    }

    public boolean check4empty(){
        if (this.username.getText()=="" || this.password.getPassword().length==0){
            return false;
        }else{
            return true;
        }
    }

    public void setFieldsNull(){
        this.username.setText("");
        this.password.setText("");
    }

    public void disposeFrame(){
        this.frame.dispose();
    }

    // public static void main(String[] args){
    //     // Create an instance of the LoginPage class
    //     LoginPage loginPage = new LoginPage();
    // }
}
