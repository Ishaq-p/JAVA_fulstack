import UserInterface.Alert;
import UserInterface.FLightSelection;
import UserInterface.FormField;
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
import java.util.Calendar;


public class Window extends JFrame{
    private String flightID1;
    private String flightID2;
    private boolean isEco1;
    private boolean isEco2;
    private int numPassengers;
    private List<String[]> flightDetails_oneway = new ArrayList<>();
    private List<String[]> flightDetails_round = new ArrayList<>();

    private JPanel p_bottomButtons = new JPanel(new FlowLayout());

    private JButton btn_flightFinal1 = new JButton("Next"){{setPreferredSize(new Dimension(300, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(new Color(199, 10, 15)); setForeground(Color.white);}};
    private JButton btn_flightFinal2 = new JButton("Next"){{setPreferredSize(new Dimension(300, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(new Color(199, 10, 15)); setForeground(Color.white);}};
    private JButton btn_passengerFinal = new JButton("Next"){{setPreferredSize(new Dimension(300, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(new Color(199, 10, 15)); setForeground(Color.white);}};
    private JButton btn_final = new JButton("Checkout"){{setPreferredSize(new Dimension(300, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(new Color(199, 10, 15)); setForeground(Color.white);}};

    private JButton btn_flightFinal1_back = new JButton("Back"){{setPreferredSize(new Dimension(200, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(Color.white); setForeground(new Color(199, 10, 15));}};
    private JButton btn_flightFinal2_back = new JButton("Back"){{setPreferredSize(new Dimension(200, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(Color.white); setForeground(new Color(199, 10, 15));}};
    private JButton btn_passengerFinal_back = new JButton("Back"){{setPreferredSize(new Dimension(200, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(Color.white); setForeground(new Color(199, 10, 15));}};
    private JButton btn_payment_back = new JButton("Back"){{setPreferredSize(new Dimension(200, 70));setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(Color.white); setForeground(new Color(199, 10, 15));}};

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
    private int loggedID;
    private SignUp signUp;
    // private SignUp signup = new SignUp();

    public Window(){
        setBackground(Color.BLACK);
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
                    mainPage.storeMainPageValues();
                    if(!mainPage.oneWay && (mainPage.miliSeconds1>mainPage.miliSeconds2)){
                        JOptionPane.showMessageDialog(null, "depart date cannot be greater than arrival ", "Miss Input", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(mainPage.from!=mainPage.to){
                            removePages();
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
                        }else{
                            JOptionPane.showMessageDialog(null, "From and To can't be the same place", "Miss Input", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    // mainPage.emptyMainPageFields();
                }else{
                    JOptionPane.showMessageDialog(null,"fill out all the fields", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
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
                    JOptionPane.showMessageDialog(null, "Select a flight", "Empty Feilds", JOptionPane.ERROR_MESSAGE);

                }   
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
                    JOptionPane.showMessageDialog(null, "please select a fligh", "Miss Input", JOptionPane.ERROR_MESSAGE);

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
                    bottomButtons(btn_payment_back, btn_final);
                    add(p_bottomButtons, BorderLayout.SOUTH);
                    revalidate();
                    repaint();
                }else{
                    JOptionPane.showMessageDialog(null,"fill out all the fields", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        this.btn_final.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(!isLoggedIn){
                    payment.check4nulls();
                }else{
                    payment.check4nulls_loggedIn();
                }
                System.out.println("isThereNull: "+payment.isThereNull);
                if(!payment.isThereNull){
                    payment.storeValues();
                        if(payment.miliSeconds1<1136073600000L){
                        boolean submissionState = (isLoggedIn) ? true : saveNonLoggedUser();
                        if(submissionState){
                            boolean cardInfo_savingStatus = saveCardInfo();
                            if (cardInfo_savingStatus){
                                saveTicketHolders();
                                removePages();
                                mainPage.emptyMainPageFields();
                                add(mainPage, BorderLayout.CENTER);
                                revalidate();
                                repaint();
                                if(mainPage.oneWay){
                                    JOptionPane.showMessageDialog(null,"ticket No:\"+flightID1+\" is purchased.", "Info", JOptionPane.INFORMATION_MESSAGE);

                                }else{
                                    JOptionPane.showMessageDialog(null,"ticket No:"+flightID1+ "and No:"+flightID2+" are purchased.", "Info", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,"Somthing went wrong with card saving", "saving error", JOptionPane.ERROR_MESSAGE);
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,"Somthing went wrong with saving user", "saving error", JOptionPane.ERROR_MESSAGE);
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,"Ticket Purchaser has to be 18+", "underAge", JOptionPane.ERROR_MESSAGE);

                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"please fill all the feilds!!", "Empty Feilds", JOptionPane.ERROR_MESSAGE);

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
                        removePages();
                        signUpPage.setToNull();
                        changeBarButtons();
                        add(navBar(barButtonsNames), BorderLayout.NORTH);
                        add(mainPage, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                }else{
                    JOptionPane.showMessageDialog(null,"fill out all the fields", "Empty Feilds", JOptionPane.ERROR_MESSAGE);

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
                        JOptionPane.showMessageDialog(null,"Logged in", "Info", JOptionPane.INFORMATION_MESSAGE);

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
                        JOptionPane.showMessageDialog(null,"username and password doesnt match", "Doens't Exist", JOptionPane.ERROR_MESSAGE);
                    }

                }else{
                    JOptionPane.showMessageDialog(null,"fill out all the fields", "Empty Feilds", JOptionPane.ERROR_MESSAGE);
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
        String[] newBarButtons = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", this.loggedUsername, "LogOut"};
        this.barButtonsNames = newBarButtons;
    }
    private void reChangeBarButtons(){
        String[] newBarButtons = {"Book&Plane", "Experience", "Deal&destination", "Miles&Smiles", "Help", "SignUp", "LogIn"};
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

        JPanel p_barButtons = new JPanel(new GridLayout(1, barButtonsNames.length));
        for (String button : barButtonsNames) {
            JButton barBtn = new JButton(button);
        
            if (button.equals(this.loggedUsername)) {
                ImageIcon logIcon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/PP.jpg"); // Replace with the path to your image file
                Image scaledIcon = logIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH); // Set the desired width and height
                ImageIcon iconScaled = new ImageIcon(scaledIcon);
                barBtn.setIcon(iconScaled);
            }
        
            barBtn.setBackground(navbarColor);
            barBtn.setForeground(Color.WHITE);
            barBtn.setBorder(null);
            Font customFont = new Font("MuseoSans-900", Font.BOLD, 16); // Set bold font with size 16
            barBtn.setFont(customFont);
        
            if (button.equals("LogIn")) {
                barBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        loginPage.loginPage();
                    }
                });
            } else if (button.equals("SignUp")) {
                barBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removePages();
                        add(signUpPage, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                });
            } else if (button.equals("Book&Plane")) {
                barBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        removePages();
                        mainPage.emptyMainPageFields();
                        add(mainPage, BorderLayout.CENTER);
                        revalidate();
                        repaint();
                    }
                });
            }else if(button.equals("LogOut")){
                barBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        isLoggedIn = false;
                        loggedUsername = "";
                        removePages();
                        remove(p_navBar);
                        reChangeBarButtons();
                        add(navBar(barButtonsNames), BorderLayout.NORTH);
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
    
    private boolean saveNonLoggedUser(){
        String country=null;
        if(payment.isTurkish){
            country = "Turkey";
        }
        this.signUp = new SignUp(payment.firsName+" "+payment.lastName, 
                                   payment.dob,
                                   country,
                                   payment.gender,
                                   "null",
                                   "null",
                                   payment.email);
        return this.signUp.submitSignup();
    }

    private boolean saveCardInfo(){
        DB_connection connection = new DB_connection();
        connection.connect();
        this.loggedID = (this.isLoggedIn) ? connection.getID_byusername(this.loggedUsername) : connection.getID(payment.email);
        boolean cardSavingStatus = connection.saveCard(this.loggedID, payment.cardFirstName+" "+payment.cardLastName
                            , payment.phoneNumber, payment.cardNumber, payment.cardExp, payment.cardCVC);

        connection.disconnect();
        return cardSavingStatus;
    }
    
    private void saveTicketHolders(){
        DB_connection connection = new DB_connection();
        connection.connect();
        for (int i=0; i<this.numPassengers; i++){
            int turk = 0;
            FormField form = passengerDetails.listOfFields.get(""+i);
            if(form.getIsTurkish().isSelected()){
                turk=1;
            }
            int[] dateInt1 = {form.getDOB().getModel().getYear(), form.getDOB().getModel().getMonth(), form.getDOB().getModel().getDay()};
            Calendar calender1 = Calendar.getInstance();
            calender1.set(dateInt1[0], dateInt1[1], dateInt1[2]);
            long miliSeconds1 = calender1.getTimeInMillis();
            Date dob = new java.sql.Date(miliSeconds1);
            System.out.println(dob);

            String title = "Mrs.";
            if(form.getButtonGroup().isSelected()){
                title = "Mr.";
                System.out.println("title is selected");
            }
            connection.save_passenger_info(form.getFirstName().getText()+" "+form.getLastName().getText(),
                                            dob,
                                            title,
                                            form.getPhone().getText(),
                                            turk);

            int tktHolderID = connection.getID_ticketHolder(form.getPhone().getText());
            if(mainPage.oneWay){
                int classE = (this.isEco1) ? 1 : 0;
                connection.save2FlihgtCustm(tktHolderID, this.loggedID, this.flightID1, classE);
            }else{
                int classE1 = (this.isEco1) ? 1 : 0;
                connection.save2FlihgtCustm(tktHolderID, this.loggedID, this.flightID1, classE1);
                int classE2 = (this.isEco1) ? 1 : 0;
                connection.save2FlihgtCustm(tktHolderID, this.loggedID, this.flightID2, classE2);
            }
        }
        int classE = (this.isEco1) ? 1 : 0;
        connection.flightsUpdate(this.flightID1, classE, this.numPassengers);
        connection.disconnect();
    }
     
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            new Window();
        });
    }
}
