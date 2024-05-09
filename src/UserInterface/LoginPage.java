package UserInterface;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame{
    
    public LoginPage(){
        // Create a panel for login
        JPanel p_login = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=0;
        gbc.insets = new Insets(10,10,10,10);
        
        // Create a label for the header
        JPanel p_header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel header = new JLabel("LogIn");
        header.setFont(new Font("MuseoSans-900", Font.BOLD, 40));
        header.setHorizontalAlignment(JLabel.CENTER);
        p_header.add(header);

        p_login.add(p_header, gbc);

        JPanel miniPanel = new JPanel(new GridBagLayout());
        gbc.gridy++;
        JLabel lbl_username = new JLabel("username:");
        lbl_username.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(lbl_username, gbc);

        gbc.gridy++;
        JTextField username = new JTextField();
        username.setPreferredSize(new Dimension(300, 50));
        username.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(username, gbc);

        gbc.gridy++;
        JLabel lbl_password = new JLabel("password:");
        lbl_password.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(lbl_password, gbc);

        gbc.gridy++;
        JPasswordField password = new JPasswordField();
        password.setPreferredSize(new Dimension(300, 50));
        password.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(password, gbc);

        gbc.gridy++;
        JButton submit = new JButton("Submit");
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
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args){
        // Create an instance of the LoginPage class
        LoginPage loginPage = new LoginPage();
    }
}
