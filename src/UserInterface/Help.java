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
        JTextArea helpText = new JTextArea(){{setFont(new Font("MuseoSans-900", Font.BOLD, 20));}};
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);
        helpText.setText(
            "Welcome to the Turkish Airlines User Interface Help Page!\n\n" +
            "Here you can find information on how to use the application:\n\n" +
            "1. **Booking a Flight:**\n" +
            "   - Go to the 'Book a Flight' tab.\n" +
            "   - Enter your departure and destination cities.\n" +
            "   - Select your travel dates and number of passengers.\n" +
            "   - Click 'Search' to find available flights.\n\n" +
            "2. **Managing Bookings:**\n" +
            "   - Go to the 'Manage Booking' tab.\n" +
            "   - Enter your booking reference and last name.\n" +
            "   - Click 'Retrieve Booking' to view or modify your booking.\n\n" +
            "3. **Checking In:**\n" +
            "   - Go to the 'Check-In' tab.\n" +
            "   - Enter your booking details to check in online.\n" +
            "   - Print your boarding pass or save it to your mobile device.\n\n" +
            "For further assistance, please contact our customer support."
        );

        // Add the text area to a scroll pane
        JScrollPane scrollPane = new JScrollPane(helpText);
        add(scrollPane, BorderLayout.CENTER);
    }
}
