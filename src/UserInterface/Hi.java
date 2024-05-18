package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Hi extends JFrame {
    public String user_name;
    public String passcode;

    private JPasswordField password = new JPasswordField();
    private JTextField username = new JTextField();
    public JButton submit = new JButton("Submit");

    // void constructor
    public Hi() {}

    // the popup login page
    public void loginPage(JFrame previousFrame) {
        // Close the previous frame
        if (previousFrame != null) {
            // previousFrame.dispose();
        }

        JFrame frame = new JFrame();
        JPanel p_login = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);

        // label for the header
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
        username.setPreferredSize(new Dimension(300, 50));
        username.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        miniPanel.add(username, gbc);

        gbc.gridy++;
        JLabel lbl_password = new JLabel("password:");
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

        frame.add(p_login);
        frame.pack();
        frame.setTitle("Login main page");
        frame.setSize(400, 500); // Setting a default size for demonstration
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public void storeValues() {
        this.user_name = this.username.getText();
        this.passcode = new String(this.password.getPassword());
    }

    public boolean check4empty() {
        return !this.username.getText().isEmpty() && this.password.getPassword().length > 0;
    }

    public void setFieldsNull() {
        this.username.setText("");
        this.password.setText("");
    }

    // Example main method to test
    public static void main(String[] args) {
        JFrame mainFrame = new JFrame("Main Frame");
        mainFrame.setSize(400, 300);
        // mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);

        JButton openLoginButton = new JButton("Open Login");
        openLoginButton.addActionListener(e -> {
            Hi loginPage = new Hi();
            loginPage.loginPage(mainFrame);
        });

        mainFrame.add(openLoginButton, BorderLayout.CENTER);
    }
}
