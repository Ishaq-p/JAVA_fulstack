package UserInterface;
import javax.swing.*;
import java.awt.*;

public class Hi extends JFrame {

    public Hi() {
        JPanel p_login = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10); // Add some padding

        // Create header panel
        JPanel p_header = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel header = new JLabel("LogIn");
        header.setFont(new Font("MuseoSans-900", Font.BOLD, 32));
        p_header.add(header);

        p_login.add(p_header, gbc);

        // Create login panel
        JPanel miniPanel = new JPanel(new GridBagLayout());
        gbc.gridy++;
        miniPanel.add(new JLabel("Username:"), gbc);

        gbc.gridy++;
        JTextField username = new JTextField(15);
        miniPanel.add(username, gbc);

        gbc.gridy++;
        miniPanel.add(new JLabel("Password:"), gbc);

        gbc.gridy++;
        JPasswordField password = new JPasswordField(15);
        miniPanel.add(password, gbc);

        gbc.gridy++;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JButton submit = new JButton("Submit");
        miniPanel.add(submit, gbc);

        p_login.add(miniPanel, gbc);

        add(p_login);
        setTitle("Login main page");
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Hi();
    }
}
