package UserInterface;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hi {
    public static void main(String[] args) {
        // Create a new frame
        JFrame frame = new JFrame("ButtonGroup Example");
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Create radio buttons
        JRadioButton option1 = new JRadioButton("Option 1");
        option1.setBounds(50, 30, 100, 30);
        option1.setActionCommand("Option 1");

        JRadioButton option2 = new JRadioButton("Option 2");
        option2.setBounds(50, 60, 100, 30);
        option2.setActionCommand("Option 2");

        JRadioButton option3 = new JRadioButton("Option 3");
        option3.setBounds(50, 90, 100, 30);
        option3.setActionCommand("Option 3");

        // Create a button group and add radio buttons to it
        ButtonGroup group = new ButtonGroup();
        group.add(option1);
        group.add(option2);
        group.add(option3);

        // Create a button to check which radio button is selected
        JButton checkButton = new JButton("Check");
        checkButton.setBounds(50, 130, 100, 30);

        // Add an action listener to the button
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ButtonModel selectedModel = group.getSelection();
                if (selectedModel != null) {
                    String selectedOption = selectedModel.getActionCommand();
                    JOptionPane.showMessageDialog(frame, "Selected: " + selectedOption);
                } else {
                    JOptionPane.showMessageDialog(frame, "No option selected");
                }
            }
        });

        // Add components to the frame
        frame.add(option1);
        frame.add(option2);
        frame.add(option3);
        frame.add(checkButton);

        // Set frame visibility to true
        frame.setVisible(true);
    }
}
