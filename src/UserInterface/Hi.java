package UserInterface;

import javax.swing.*;
import java.awt.*;

public class Hi extends JFrame {

    public Hi() {
        setTitle("Number Field Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a spinner with default model
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0, 0, Integer.MAX_VALUE, 1); // Initial value, minimum, maximum, step
        JSpinner spinner = new JSpinner(spinnerModel);

        // Set preferred size for the spinner to ensure it's visible
        spinner.setPreferredSize(new Dimension(100, 30));

        // Add the spinner to the frame
        getContentPane().add(spinner, BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null); // Center the frame
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Hi example = new Hi();
            example.setVisible(true);
        });
    }
}
