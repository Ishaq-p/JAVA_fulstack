package UserInterface;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

public class MainPage extends JFrame{
    private String[] barButtonsNames = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", "SignUp", "LogIn"};
    private int barRed=33, barGreen=37, barBlue=42;
    private Color navbarColor = new Color(barRed,barGreen,barBlue);

    private JRadioButton radBtn_roundTrip = new JRadioButton("Round Trip");
    private JRadioButton radBtn_oneWay = new JRadioButton("One Way");
    private JRadioButton radBtn_multiCity = new JRadioButton("Multy City");

    private JTextField input_from = new JTextField(15);
    private JTextField input_to = new JTextField(15);


    public MainPage(){
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
            p_barButtons.add(barBtn);
        }

        p_navBar.add(p_barButtons, BorderLayout.EAST);
        p_navBar.add(p_logo, BorderLayout.WEST);

        
        // body 
        JPanel p_body = new JPanel();  // 21

        JPanel p_bigImage = new JPanel();  // 22
        ImageIcon bigicon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/bigImage.png"); // Replace "path/to/your/image.jpg" with the path to your image file
        Image scaledBigImage = bigicon.getImage().getScaledInstance(1920, 549, Image.SCALE_SMOOTH); // Set the desired width and height
        ImageIcon bigicon_scaled = new ImageIcon(scaledBigImage);
        JLabel bigImgLabel = new JLabel(bigicon_scaled);
        p_bigImage.add(bigImgLabel);

        JPanel p_searchArea = new JPanel(new BorderLayout());  // 23

        JPanel p_searchTop = new JPanel(new BorderLayout());   // 231
        JPanel p_searchTopLeft = new JPanel();
        p_searchTopLeft.add(radBtn_roundTrip);
        p_searchTopLeft.add(radBtn_oneWay);
        p_searchTopLeft.add(radBtn_multiCity);
        ButtonGroup radGroup = new ButtonGroup();
        radGroup.add(radBtn_roundTrip);
        radGroup.add(radBtn_oneWay);
        radGroup.add(radBtn_multiCity);
        JPanel p_searchTopRight = new JPanel();
        p_searchTopRight.add(new JLabel("Gift a ticket"));

        p_searchTop.add(p_searchTopRight, BorderLayout.EAST);
        p_searchTop.add(p_searchTopLeft, BorderLayout.WEST);
        


        JPanel p_searchBottom = new JPanel(); // 232
        p_searchBottom.setLayout(new GridLayout(1,5));

        JPanel p_from = new JPanel(new BorderLayout());
        p_from.add(new JLabel("From"), BorderLayout.NORTH);
        p_from.add(input_from, BorderLayout.SOUTH);
        JPanel p_to = new JPanel(new BorderLayout());
        p_to.add(new JLabel("to"), BorderLayout.NORTH);
        p_to.add(input_to, BorderLayout.SOUTH);

        p_searchBottom.add(p_from);
        p_searchBottom.add(p_to);

        


        p_searchArea.add(p_searchBottom, BorderLayout.SOUTH);
        p_searchArea.add(p_searchTop, BorderLayout.NORTH);



        p_body.add(p_bigImage);
        p_body.add(p_searchArea);





        add(p_navBar, BorderLayout.NORTH);
        add(p_body, BorderLayout.CENTER);
    }

    public static void main(String[] args){
        MainPage frame = new MainPage();
        frame.setTitle("main page");
		frame.setSize(1920, 1080);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
    }
}
