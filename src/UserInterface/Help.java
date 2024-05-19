package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Help extends JPanel {

    public Help() {
        setLayout(new BorderLayout());
        setBackground(Color.white);

        // Create a title for the help page
        JLabel title = new JLabel("Help Page", SwingConstants.CENTER){{setBackground(Color.white);}};
        title.setFont(new Font("MuseoSans-900", Font.BOLD, 54));
        add(title, BorderLayout.NORTH);

        // Create the content for the help page
        JTextArea helpText = new JTextArea(){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));}};
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setText(
            "Welcome to the Turkish Airlines User Interface Help Page!\n\n" +
            "Here you can find information on how to use the application:\n\n" +
            "1. Booking a Flight:\n" +
            "   - Go to the the home page or 'Book&Plane' tab.\n" +
            "   - Enter your departure and destination cities.\n" +
            "   - Select your travel dates and number of passengers.\n" +
            "   - Click 'Search' to find available flights.\n\n" +
            "2. Miles&Smiles Membership :\n" +
            "   - Go to the 'Sign up' tab.\n" +
            "   - Enter your Personal Info.\n" +
            "   - Click 'Create Account' to become a member.\n\n" +
            "3. Log in:\n" +
            "   - Go to the 'Login' tab.\n" +
            "   - Enter your Credentials if you have an account otherwise try the Sign Up tab.\n" +
            "   - press login to give you all the access for a Miles&Smiles member\n\n" +
            "For further assistance, please contact our customer support."
        );

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(helpText);
        add(scrollPane, BorderLayout.CENTER);
    }
}
