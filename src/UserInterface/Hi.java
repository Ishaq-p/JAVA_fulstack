package UserInterface;

import org.jdatepicker.JDatePicker;

import javax.swing.*;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class Hi extends JFrame {
    public Hi() {
        JPanel panel = new JPanel(new FlowLayout());

        // Create a Properties object to configure the date picker
        Properties properties = new Properties();
        properties.put("text.today", "Today");
        properties.put("text.month", "Month");
        properties.put("text.year", "Year");

        // Create a JDatePicker with the configured properties
        JDatePicker datePicker = new JDatePicker(properties);

        panel.add(datePicker);
        add(panel);

        // Other frame settings...
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Hi frame = new Hi();
            frame.setTitle("Date Picker Example");
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
