import UserInterface.LoginPage;
import UserInterface.MainPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Window extends JFrame{
    private String[] barButtonsNames = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", "SignUp", "LogIn"};
    private int barRed=33, barGreen=37, barBlue=42;
    private Color navbarColor = new Color(barRed,barGreen,barBlue);

    public Window(){
        JPanel p_navBar = new JPanel(new BorderLayout());  // 11
        p_navBar.setBorder(new EmptyBorder(10, 0, 10, 0)); // top, left, bottom, right
        p_navBar.setBackground(navbarColor);
        
        JPanel p_logo = new JPanel();  //12
        p_logo.setBackground(navbarColor);
        
        //  navbar
        ImageIcon icon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/THY-LOGO.png"); // Replace "path/to/your/image.jpg" with the path to your image file
        Image scaledImage = icon.getImage().getScaledInstance(218, 40, Image.SCALE_SMOOTH); // Set the desired width and height
        ImageIcon icon_scaled = new ImageIcon(scaledImage);
        
        JLabel Logolabel = new JLabel(icon_scaled);
        p_logo.add(Logolabel);

        JPanel p_barButtons = new JPanel(new GridLayout(1, barButtonsNames.length));  // 13
        for (String button : barButtonsNames){
            JButton barBtn = new JButton(button);
            barBtn.setBackground(navbarColor);
            barBtn.setForeground(Color.WHITE);
            barBtn.setBorder(null);
            Font customFont = new Font("MuseoSans-900", Font.BOLD, 16); // Set bold font with size 14
            barBtn.setFont(customFont);
            if (button=="LogIn"){
                barBtn.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        new LoginPage();
                    }
                });
            }
            p_barButtons.add(barBtn);
        }

        p_navBar.add(p_barButtons, BorderLayout.EAST);
        p_navBar.add(p_logo, BorderLayout.WEST);

        add(p_navBar);

        
        setTitle("MainPage");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
    

    public static void main(String[] args){
        Window baseFrame = new Window();
        MainPage mainFrame = new MainPage();

        // baseFrame.add(mainFrame);
        // baseFrame.setVisible(true);


        mainFrame.setVisible(true);
    }
}
