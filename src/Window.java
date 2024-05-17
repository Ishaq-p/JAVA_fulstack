import UserInterface.Alert;
import UserInterface.FLightSelection;
import UserInterface.LoginPage;
import UserInterface.MainPage;
import UserInterface.PassengerDetails;
import UserInterface.Payment;
import UserInterface.SignUpPage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import java.util.List;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;


public class Window extends JFrame{
    private String flightID1;
    private String flightID2;
    private boolean isEco1;
    private boolean isEco2;
    private int numPassengers;
    private List<String[]> flightDetails_oneway = new ArrayList<>();
    private List<String[]> flightDetails_round = new ArrayList<>();

    private JPanel p_bottomButtons = new JPanel(new FlowLayout());

    private JButton btn_flightFinal1 = new JButton("Next"){{setPreferredSize(new Dimension(300, 80));}};
    private JButton btn_flightFinal2 = new JButton("Next"){{setPreferredSize(new Dimension(300, 80));}};
    private JButton btn_passengerFinal = new JButton("Next"){{setPreferredSize(new Dimension(300, 80));}};

    private JButton btn_flightFinal1_back = new JButton("Back"){{setPreferredSize(new Dimension(300, 80));}};
    private JButton btn_flightFinal2_back = new JButton("Back"){{setPreferredSize(new Dimension(300, 80));}};
    private JButton btn_passengerFinal_back = new JButton("Back"){{setPreferredSize(new Dimension(300, 80));}};
    private JButton btn_payment_back = new JButton("Back"){{setPreferredSize(new Dimension(300, 80));}};

    private String[] barButtonsNames = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", "SignUp", "LogIn"};
    private int barRed=33, barGreen=37, barBlue=42;
    private Color navbarColor = new Color(barRed,barGreen,barBlue);

    private MainPage mainPage = new MainPage();
    private SignUpPage signUpPage = new SignUpPage(); 
    private FLightSelection flightSelection1 = new FLightSelection();
    private FLightSelection flightSelection2 = new FLightSelection();
    private PassengerDetails passengerDetails = new PassengerDetails(4);
    private Payment payment = new Payment(false);
    private LoginPage loginPage = new LoginPage();

    private FlightChecker flightChecker = new FlightChecker();
    private boolean isLoggedIn = false;
    private String loggedUsername;
    // private SignUp signup = new SignUp();

    public Window(){
        setLayout(new BorderLayout());
        setTitle("MainPage");
        setSize(1920,1080);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        JPanel p_navBar = navBar(barButtonsNames);
        
        

                


        // action listener for the search button on the main page
        mainPage.searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                boolean bool = mainPage.check4Null();
                String[] flightIds;
                
                if (bool){
                    removePages();
                    mainPage.storeMainPageValues();

                    System.out.println("if:");
                    flightChecker = new FlightChecker(mainPage.from, mainPage.to, mainPage.departDate, mainPage.arrivalDate, mainPage.passengerFinalNum);
                    flightIds = flightChecker.searchFlights_oneway();
                    for (String i : flightIds){
                        String[] flightInfo = flightChecker.flightDetails(i);
                        flightDetails_oneway.add(flightInfo);
                    }
                    numPassengers = mainPage.passengerFinalNum;
                    assingFlightSelection(true);

                    add(flightSelection1, BorderLayout.CENTER);
                    bottomButtons(btn_flightFinal1_back, btn_flightFinal1);
                    add(p_bottomButtons, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                    // mainPage.emptyMainPageFields();
                }else{
                    new Alert("please fill all the feilds!!");
                }
            }
        });

        // the problem is that, everything is set but still this button is not performing its task, in other words when i click it, th ebutton litrally doesn run
        this.btn_flightFinal1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("btn_flightFinal1:");
                String[] flightIds;

                if(flightSelection1.flightSelected!=null){
                    flightID1 = flightSelection1.flightSelected;
                    isEco1 = flightSelection1.isEco;

                    if(mainPage.oneWay){
                        System.out.println("flight is oneWay:");
                            removePages();
                            System.out.println(flightID1+" "+ isEco1);
                            add(passengerDetails, BorderLayout.CENTER);
                            bottomButtons(btn_passengerFinal_back, btn_passengerFinal);
                            add(p_bottomButtons, BorderLayout.SOUTH);
                            revalidate();
                            repaint();        
                    }else{
                        System.out.println("flight is roundTrip:");
                    
                        removePages();
                        System.out.println(flightID1+" "+ isEco1);
                        flightChecker = new FlightChecker(mainPage.to, mainPage.from, mainPage.arrivalDate, mainPage.arrivalDate, mainPage.passengerFinalNum);
                        flightIds = flightChecker.searchFlights_oneway();
                        for (String i : flightIds){
                            String[] flightInfo = flightChecker.flightDetails(i);
                            flightDetails_round.add(flightInfo);
                        }
                        assingFlightSelection(false);  // assigning the objects

                        add(flightSelection2, BorderLayout.CENTER);
                        bottomButtons(btn_flightFinal2_back, btn_flightFinal2);
                        add(p_bottomButtons, BorderLayout.SOUTH);  
                        revalidate();
                        repaint();        
                    }
                }else{
                    new Alert("please select a flight!!");
                }   
                // System.out.println(flightSelection.flightSelected+" "+ isEco1);
                // new Alert("please select somthin");
            }
        });

        this.btn_flightFinal2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                System.out.println("btn_flightFinal2:");
                if(flightSelection2.flightSelected!=null){
                    removePages();
                    flightID2 = flightSelection2.flightSelected;
                    isEco2 = flightSelection2.isEco;
                    System.out.println(flightID2+" "+isEco2);
                    add(passengerDetails, BorderLayout.CENTER);
                    bottomButtons(btn_passengerFinal_back, btn_passengerFinal);
                    add(p_bottomButtons, BorderLayout.SOUTH);
                    revalidate();
                    repaint();    
                }else{
                    new Alert("please select a flight!!");
                }
            }
        });

        this.btn_passengerFinal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                passengerDetails.check4nulls();
                if(!passengerDetails.isThereNull){
                    removePages();
                    payment = new Payment(isLoggedIn);
                    add(payment, BorderLayout.CENTER);
                    add(btn_payment_back, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                }else{
                    new Alert("please fill all the feilds!!");
                }
            }
        });

        payment.btn_final.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                payment.check4nulls();
                if(!payment.isThereNull){
                    removePages();
                    mainPage.emptyMainPageFields();
                    add(mainPage, BorderLayout.CENTER);
                    revalidate();
                    repaint();
                    if(mainPage.oneWay){
                        new Alert("ticket No:"+flightID1+" is purchased.");
                    }else{
                        new Alert("ticket No:"+flightID1+ "and No:"+flightID2+" are purchased.");
                    }
                }
                else{
                    new Alert("please fill all the feilds!!");
                }
            }
        });

        // signUp page final submit button action lisrener
        signUpPage.submitButton.addActionListener(new ActionListener() {
            private boolean bool_empty=false;
            @Override
            public void actionPerformed(ActionEvent e){
                bool_empty = signUpPage.check4empty();
                if (bool_empty){
                    signUpPage.storeSignupData();
                    SignUp signUp = new SignUp(signUpPage.firstName+" "+signUpPage.lastName
                                                , signUpPage.dob
                                                , signUpPage.country
                                                , signUpPage.title
                                                , signUpPage.username
                                                , signUpPage.password
                                                , signUpPage.emial);
                    boolean submissionState = signUp.submitSignup();
                    // dbConnection.disconnect();
                    if (submissionState){
                        isLoggedIn=true;
                        loggedUsername = signUpPage.username;
                        remove(p_navBar);
                        signUpPage.setToNull();
                        removePages();
                        add(p_navBar, BorderLayout.NORTH);
                        add(mainPage, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                }else{
                    new Alert("please fill all the feilds!!");
                }
            }
        });
        // signUp page final submit button action lisrener
        loginPage.submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (loginPage.check4empty()){
                    loginPage.storeValues();
                    Login login = new Login(loginPage.user_name, loginPage.passcode);
                    if (login.logingIn()){
                        new Alert("Logged in!!");
                        isLoggedIn=true;
                        loggedUsername = loginPage.user_name;
                        remove(p_navBar);
                        changeBarButtons();
                        add(navBar(barButtonsNames), BorderLayout.NORTH);
                        revalidate();
                        repaint();
                        loginPage.setFieldsNull();
                        loginPage.dispose();
                    }else{
                        new Alert("username and password doesnt match!!");
                    }

                }else{
                    new Alert("please fill all the fields!!");
                }
            }
        });


        this.btn_flightFinal1_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                add(mainPage , BorderLayout.CENTER);
                deleteFlightDetails();
                revalidate();
                repaint();
            }
        });
        this.btn_flightFinal2_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                add(flightSelection1, BorderLayout.CENTER);
                bottomButtons(btn_flightFinal1_back, btn_flightFinal1);
                add(p_bottomButtons, BorderLayout.SOUTH);
                revalidate();
                repaint();
            }
        });
        this.btn_passengerFinal_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                if(mainPage.oneWay){
                    add(flightSelection1, BorderLayout.CENTER);
                    bottomButtons(btn_flightFinal1_back, btn_flightFinal1);
                    add(p_bottomButtons, BorderLayout.SOUTH);
                }else{
                    add(flightSelection2, BorderLayout.CENTER);
                    bottomButtons(btn_flightFinal2_back, btn_flightFinal2);
                    add(p_bottomButtons, BorderLayout.SOUTH);
                }
                revalidate();
                repaint();
            }
        });
        this.btn_payment_back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                add(passengerDetails , BorderLayout.CENTER);
                bottomButtons(btn_passengerFinal_back, btn_passengerFinal);
                add(p_bottomButtons, BorderLayout.SOUTH);
                revalidate();
                repaint();
            }
        });
        

        add(p_navBar, BorderLayout.NORTH);
        add(mainPage, BorderLayout.CENTER);
        add(p_bottomButtons, BorderLayout.SOUTH);

    }
    public void removePages(){
        remove(mainPage);
        remove(signUpPage);
        remove(flightSelection1);
        remove(flightSelection2);
        remove(passengerDetails);
        remove(payment);
        remove(btn_flightFinal1);
        remove(btn_flightFinal2);
        remove(btn_passengerFinal);
        remove(btn_payment_back);
        remove(p_bottomButtons);
    }   


    private void assingFlightSelection(boolean isOneway){
        if(isOneway){
            this.flightSelection1 = new FLightSelection(this.flightDetails_oneway);
            this.passengerDetails = new PassengerDetails(this.numPassengers);
        }else{
            this.flightSelection2 = new FLightSelection(this.flightDetails_round);
            this.passengerDetails = new PassengerDetails(this.numPassengers);
        }
    }

    public void bottomButtons(JButton button1, JButton button2){
        p_bottomButtons = new JPanel(new FlowLayout());
        p_bottomButtons.add(button1);
        p_bottomButtons.add(button2);
    }

    public void deleteFlightDetails(){
        this.flightDetails_oneway = new ArrayList<>();
        this.flightDetails_round = new ArrayList<>();
    }


    private void changeBarButtons(){
        String[] newBarButtons = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", this.loggedUsername};
        this.barButtonsNames = newBarButtons;
    }

    private JPanel navBar(String[] barButtonNames){
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
        for (String button : barButtonNames){
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
                        loginPage.loginPage();
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
                        mainPage.emptyMainPageFields();
                        add(mainPage, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                });
            }
            p_barButtons.add(barBtn);
        }
    

        // the turkish airlines logo button action listener
        logolabel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                removePages();
                mainPage.emptyMainPageFields();
                add(mainPage, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        
        p_navBar.add(p_barButtons, BorderLayout.EAST);
        p_navBar.add(p_logo, BorderLayout.WEST);
        return p_navBar;

    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new Window();
        });
    }
}
