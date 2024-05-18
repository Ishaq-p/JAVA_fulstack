package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Properties;
import java.util.concurrent.Flow;
import java.sql.Date;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class SignUpPage extends JPanel{
    public String firstName;
    public String lastName;
    public String emial;
    public String country;
    public Date   dob;
    public String title;
    public String username;
    public String password;
    
    private Properties p = new Properties();
    private UtilDateModel model = new UtilDateModel();

    private JTextField txt_firstname = new JTextField(15);
    private JTextField txt_lastname = new JTextField(15);
    private JTextField txt_email = new JTextField(15);
    private JTextField txt_country = new JTextField(15);
    private JDatePickerImpl txt_dob = new JDatePickerImpl(new JDatePanelImpl(this.model, p), new DateComponentFormatter()){{setBorder(new EmptyBorder(0,0,0,10)); setBackground(Color.white);}};
    private String[] titles = {"Mr.","Mrs."};
    private JComboBox<String> txt_title = new JComboBox<>(titles){{setBackground(Color.WHITE);}};
    private JTextField txt_username = new JTextField(15);
    private JPasswordField txt_passwprd = new JPasswordField(15);

    public JButton submitButton = new JButton("Create Account"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(new Color(199, 10, 15)); setForeground(Color.white);setPreferredSize(new Dimension(300,60));}};



    public SignUpPage(){
        setBackground(Color.WHITE);
        JPanel mainPanel = new JPanel(){{setBackground(Color.WHITE);}};
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(995, 900));
        mainPanel.setBorder(new LineBorder(Color.WHITE, 2));

        JPanel p_header = new JPanel(new FlowLayout(FlowLayout.CENTER)){{setBackground(Color.WHITE);}};
        JLabel header = new JLabel("Miles&Smiles Membership");
        header.setFont(new Font("MuseoSans-900", Font.BOLD, 55));
        header.setHorizontalAlignment(JLabel.CENTER);
        p_header.add(header);



        JPanel p_personalInfo1Parent = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_personalInfo1headerAndBody = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        p_personalInfo1headerAndBody.setPreferredSize(new Dimension(900,250));
        p_personalInfo1headerAndBody.setBorder(new LineBorder(Color.WHITE, 2));
        JPanel p_personalInfo1 = new JPanel(new GridLayout(3,2)){{setBackground(Color.WHITE);}};
        p_personalInfo1.setBorder(new LineBorder(Color.WHITE, 2));

        JLabel lbl_perosnalInfo = new JLabel("Perosnal Info"){{setHorizontalAlignment(SwingConstants.CENTER);}};
        lbl_perosnalInfo.setFont(new Font("MuseoSans-900", Font.BOLD, 32));

        JPanel p_firstname = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_firstnameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_firstname = new JLabel("First Name:");
        lbl_firstname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_firstname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_firstnameMini.add(lbl_firstname, BorderLayout.NORTH);
        p_firstnameMini.add(txt_firstname, BorderLayout.SOUTH);
        p_firstname.add(p_firstnameMini);

        JPanel p_lastname = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_lastnameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_lastname = new JLabel("Last Name:");
        lbl_lastname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_lastname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_lastnameMini.add(lbl_lastname, BorderLayout.NORTH);
        p_lastnameMini.add(txt_lastname, BorderLayout.SOUTH);
        p_lastname.add(p_lastnameMini);

        JPanel p_email = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_emailMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_email = new JLabel("Email:");
        lbl_email.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_email.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_emailMini.add(lbl_email, BorderLayout.NORTH);
        p_emailMini.add(txt_email, BorderLayout.SOUTH);
        p_email.add(p_emailMini);

        JPanel p_country = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_countryMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_country = new JLabel("Nationality:");
        lbl_country.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_country.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_countryMini.add(lbl_country, BorderLayout.NORTH);
        p_countryMini.add(txt_country, BorderLayout.SOUTH);
        p_country.add(p_countryMini);

        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JPanel p_DobTitle = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        // p_DobTitle.setBorder(new EmptyBorder(0,150,0,0));
        JPanel p_DobTitleMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JPanel p_dobMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_dob = new JLabel("Date of Birth:");
        lbl_dob.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_dob.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_dobMini.add(lbl_dob, BorderLayout.NORTH);
        p_dobMini.add(txt_dob, BorderLayout.SOUTH);
        p_DobTitleMini.add(p_dobMini, BorderLayout.WEST);
        JPanel p_titleMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_title = new JLabel("Title:"){{setBackground(Color.WHITE);}};
        lbl_title.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_title.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_titleMini.add(lbl_title, BorderLayout.NORTH);
        p_titleMini.add(txt_title, BorderLayout.SOUTH);
        p_DobTitleMini.add(p_titleMini, BorderLayout.EAST);
        p_DobTitle.add(p_DobTitleMini);


        p_personalInfo1.add(lbl_perosnalInfo);
        p_personalInfo1.add(p_firstname);
        p_personalInfo1.add(p_lastname);
        p_personalInfo1.add(p_email);
        p_personalInfo1.add(p_country);
        p_personalInfo1.add(p_DobTitle);

        p_personalInfo1headerAndBody.add(lbl_perosnalInfo, BorderLayout.NORTH);
        p_personalInfo1headerAndBody.add(p_personalInfo1, BorderLayout.SOUTH);

        p_personalInfo1Parent.add(p_personalInfo1headerAndBody);



        JPanel p_personalInfo2Parent = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_personalInfo2headerAndBody = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        p_personalInfo2headerAndBody.setPreferredSize(new Dimension(900,100));
        p_personalInfo2headerAndBody.setBorder(new LineBorder(Color.WHITE, 2));
        JPanel p_personalInfo2 = new JPanel(new GridLayout(1,2)){{setBackground(Color.WHITE);}};
        p_personalInfo1.setBorder(new LineBorder(Color.WHITE, 2));

        JLabel lbl_perosnalInfo2 = new JLabel("Security Info"){{setHorizontalAlignment(SwingConstants.CENTER);}};
        lbl_perosnalInfo2.setFont(new Font("MuseoSans-900", Font.BOLD, 32));

        JPanel p_username = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_usernameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_username = new JLabel("Userame:");
        lbl_username.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_username.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_usernameMini.add(lbl_username, BorderLayout.NORTH);
        p_usernameMini.add(txt_username, BorderLayout.SOUTH);
        p_username.add(p_usernameMini);

        JPanel p_password = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_passwordMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_password = new JLabel("Password:");
        lbl_password.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        txt_passwprd.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_passwordMini.add(lbl_password, BorderLayout.NORTH);
        p_passwordMini.add(txt_passwprd, BorderLayout.SOUTH);
        p_password.add(p_passwordMini);

        p_personalInfo2.add(p_username);
        p_personalInfo2.add(p_password);

        p_personalInfo2headerAndBody.add(lbl_perosnalInfo2, BorderLayout.NORTH);
        p_personalInfo2headerAndBody.add(p_personalInfo2, BorderLayout.CENTER);

        p_personalInfo2Parent.add(p_personalInfo2headerAndBody);

        // p_personalInfo2Parent.add(p_personalInfo2);

        JPanel p_submitButton = new JPanel(new FlowLayout(FlowLayout.CENTER)){{setBackground(Color.WHITE);}};
        submitButton.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        p_submitButton.add(submitButton);

        
    
        mainPanel.add(p_header);
        mainPanel.add(p_personalInfo1Parent);
        mainPanel.add(p_personalInfo2Parent);
        mainPanel.add(p_submitButton);

        add(p_header, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);

    }

    public void storeSignupData(){
        this.firstName = this.txt_firstname.getText();
        this.lastName = this.txt_lastname.getText();
        this.emial = this.txt_email.getText();
        this.country = this.txt_country.getText();
        int[] dateInt = {this.txt_dob.getModel().getYear(), this.txt_dob.getModel().getMonth(), this.txt_dob.getModel().getDay()};
        Calendar calendar = Calendar.getInstance();
        calendar.set(dateInt[0], dateInt[1], dateInt[2]);
        long miliSeconds = calendar.getTimeInMillis();
        this.dob = new java.sql.Date(miliSeconds);
        this.title = (String) this.txt_title.getSelectedItem();
        this.username = this.txt_username.getText();
        this.password = new String(this.txt_passwprd.getPassword());

        // System.out.println(firstName+" "+lastName+" "+emial+" "+country+" "+dob+" "+title+" "+username+" "+password);
    }

    public void setToNull(){
        this.txt_firstname.setText("");
        this.txt_lastname.setText("");
        this.txt_email.setText("");
        this.txt_country.setText("");
        this.txt_dob.getModel().setValue(null);
        this.txt_title.setSelectedItem(-1);
        this.txt_username.setText("");
        this.txt_passwprd.setText("");
    }

    public boolean check4empty(){
        if (this.txt_firstname.getText() == "" || this.txt_lastname.getText() == "" || this.txt_email.getText() == "" || this.txt_country.getText() == "" || this.txt_dob.getModel().getValue() == null || this.txt_username.getText() == ""
                || this.txt_passwprd.getPassword().length == 0){
            return false;
        }else{
            return true;
        }
    }
    
}