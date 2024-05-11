package UserInterface;


import UserInterface.LoginPage;
import UserInterface.MainPage;
import UserInterface.SignUpPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Hi extends JFrame {
    private String[] barButtonsNames = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", "SignUp", "LogIn"};
    private int barRed = 33, barGreen = 37, barBlue = 42;
    private Color navbarColor = new Color(barRed, barGreen, barBlue);

    private MainPage mainPage = new MainPage();
    private SignUpPage signUpPage = new SignUpPage();
    private LoginPage loginPage = new LoginPage();

    private CardLayout cardLayout;

    public Hi() {
        setLayout(new BorderLayout());
        setTitle("MainPage");
        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p_Window = new JPanel(new BorderLayout());

        JPanel p_navBar = new JPanel(new BorderLayout());
        p_navBar.setBorder(new EmptyBorder(10, 0, 10, 0));
        p_navBar.setBackground(navbarColor);

        JPanel p_logo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        p_logo.setBackground(navbarColor);

        // navbar
        ImageIcon icon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/THY-LOGO.png");
        Image scaledImage = icon.getImage().getScaledInstance(218, 40, Image.SCALE_SMOOTH);
        ImageIcon icon_scaled = new ImageIcon(scaledImage);

        JLabel Logolabel = new JLabel(icon_scaled);
        p_logo.add(Logolabel);

        JPanel p_barButtons = new JPanel(new GridLayout(1, barButtonsNames.length));
        for (String button : barButtonsNames) {
            JButton barBtn = new JButton(button);
            barBtn.setBackground(navbarColor);
            barBtn.setForeground(Color.WHITE);
            barBtn.setBorder(null);
            Font customFont = new Font("MuseoSans-900", Font.BOLD, 16);
            barBtn.setFont(customFont);
            if (button.equals("LogIn")) {
                barBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(p_Window, "login");
                    }
                });
            } else if (button.equals("SignUp")) {
                barBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(p_Window, "signup");
                    }
                });
            } else if (button.equals("Book&Plane")) {
                barBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        cardLayout.show(p_Window, "main");
                    }
                });
            }
            p_barButtons.add(barBtn);
        }

        p_navBar.add(p_barButtons, BorderLayout.EAST);
        p_navBar.add(p_logo, BorderLayout.WEST);

        cardLayout = new CardLayout();
        p_Window.setLayout(cardLayout);

        p_Window.add(mainPage, "main");
        p_Window.add(signUpPage, "signup");
        p_Window.add(loginPage, "login");

        getContentPane().add(p_navBar, BorderLayout.NORTH);
        getContentPane().add(p_Window, BorderLayout.CENTER);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Hi();
        });
    }
}
