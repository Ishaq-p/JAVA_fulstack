import UserInterface.FLightSelection;
import UserInterface.LoginPage;
import UserInterface.MainPage;
import UserInterface.PassengerDetails;
import UserInterface.Payment;
import UserInterface.SignUpPage;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;


public class Window extends JFrame{
    private String[] barButtonsNames = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", "SignUp", "LogIn"};
    private int barRed=33, barGreen=37, barBlue=42;
    private Color navbarColor = new Color(barRed,barGreen,barBlue);

    private MainPage mainPage = new MainPage();
    private SignUpPage signUpPage = new SignUpPage(); 
    private FLightSelection flightSelection = new FLightSelection(6);
    private PassengerDetails passengerDetails = new PassengerDetails();
    private Payment payment = new Payment();

    private DB_connection dbConnection = new DB_connection();

    public Window(){
        setLayout(new BorderLayout());
        setTitle("MainPage");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        
        JPanel p_navBar = new JPanel(new BorderLayout());  // 11
        p_navBar.setBorder(new EmptyBorder(10, 0, 10, 0)); // top, left, bottom, right
        p_navBar.setBackground(navbarColor);
        
        JPanel p_logo = new JPanel(new FlowLayout(FlowLayout.LEFT));  //12
        p_logo.setBackground(navbarColor);
        
        //  navbar
        ImageIcon icon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/THY-LOGO.png"); // Replace "path/to/your/image.jpg" with the path to your image file
        Image scaledImage = icon.getImage().getScaledInstance(218, 40, Image.SCALE_SMOOTH); // Set the desired width and height
        ImageIcon icon_scaled = new ImageIcon(scaledImage);
        
        JButton logolabel = new JButton(icon_scaled){{setBackground(navbarColor);
                                                    setBorder(new EmptyBorder(0,0,0,0));}};
        p_logo.add(logolabel);

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
            }else if (button=="SignUp"){
                barBtn.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        removePages();
                        add(signUpPage, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                });
            }else if (button=="Book&Plane"){
                barBtn.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        removePages();
                        add(mainPage, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                });
            }else if (button=="Experience"){
                barBtn.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e){
                        removePages();
                        add(signUpPage, BorderLayout.CENTER);
                        for (Component component : signUpPage.getComponents()) {
                            if (component instanceof JTextField) {
                                ((JTextField) component).setText("");
                                System.out.println(component);
                            }
                        }
                        revalidate();
                        repaint();
                    }
                });
            }
            p_barButtons.add(barBtn);
        }

        // action listener for the search button on the main page
        mainPage.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                mainPage.storeMainPageValues();
                passengerDetails = new PassengerDetails(mainPage.passengerFinalNum);
                add(flightSelection);
                revalidate();
                repaint();
                mainPage.emptyMainPageFields();
            }
        });
        
        // the turkish airlines logo button action listener
        logolabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                add(mainPage, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        flightSelection.btn_flightFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                add(passengerDetails, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        passengerDetails.btn_passengerFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                add(payment, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });


        p_navBar.add(p_barButtons, BorderLayout.EAST);
        p_navBar.add(p_logo, BorderLayout.WEST);

        add(p_navBar, BorderLayout.NORTH);
        add(mainPage, BorderLayout.CENTER);

    }
    public void removePages(){
        remove(mainPage);
        remove(signUpPage);
        remove(flightSelection);
        remove(passengerDetails);
        remove(payment);
    }   
 
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new Window();
        });
    }
}
