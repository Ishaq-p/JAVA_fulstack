package UserInterface;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class SignUpPage extends JPanel{


    public SignUpPage(){
        setBackground(Color.WHITE);
        JPanel mainPanel = new JPanel(){{setBackground(Color.WHITE);}};
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setPreferredSize(new Dimension(995, 900));
        mainPanel.setBorder(new LineBorder(Color.RED, 2));

        JPanel p_header = new JPanel(new FlowLayout(FlowLayout.CENTER)){{setBackground(Color.WHITE);}};
        JLabel header = new JLabel("Membership with us");
        header.setFont(new Font("MuseoSans-900", Font.BOLD, 40));
        header.setHorizontalAlignment(JLabel.CENTER);
        p_header.add(header);



        JPanel p_personalInfo1Parent = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_personalInfo1headerAndBody = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        p_personalInfo1headerAndBody.setPreferredSize(new Dimension(900,250));
        p_personalInfo1headerAndBody.setBorder(new LineBorder(Color.GREEN, 2));
        JPanel p_personalInfo1 = new JPanel(new GridLayout(3,2)){{setBackground(Color.WHITE);}};
        p_personalInfo1.setBorder(new LineBorder(Color.GREEN, 2));

        JLabel lbl_perosnalInfo = new JLabel("Perosnal Info:");
        lbl_perosnalInfo.setFont(new Font("MuseoSans-900", Font.BOLD, 32));

        JPanel p_firstname = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_firstnameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_firstname = new JLabel("First Name:");
        lbl_firstname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JTextField txt_firstname = new JTextField(15);
        txt_firstname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_firstnameMini.add(lbl_firstname, BorderLayout.NORTH);
        p_firstnameMini.add(txt_firstname, BorderLayout.SOUTH);
        p_firstname.add(p_firstnameMini);

        JPanel p_lastname = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_lastnameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_lastname = new JLabel("Last Name:");
        lbl_lastname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JTextField txt_lastname = new JTextField(15);
        txt_lastname.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_lastnameMini.add(lbl_lastname, BorderLayout.NORTH);
        p_lastnameMini.add(txt_lastname, BorderLayout.SOUTH);
        p_lastname.add(p_lastnameMini);

        JPanel p_email = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_emailMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_email = new JLabel("Email:");
        lbl_email.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JTextField txt_email = new JTextField(15);
        txt_email.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_emailMini.add(lbl_email, BorderLayout.NORTH);
        p_emailMini.add(txt_email, BorderLayout.SOUTH);
        p_email.add(p_emailMini);

        JPanel p_country = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_countryMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_country = new JLabel("Nationality:");
        lbl_country.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JTextField txt_country = new JTextField(15);
        txt_country.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_countryMini.add(lbl_country, BorderLayout.NORTH);
        p_countryMini.add(txt_country, BorderLayout.SOUTH);
        p_country.add(p_countryMini);

        JPanel p_DobTitle = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        // p_DobTitle.setBorder(new EmptyBorder(0,150,0,0));
        JPanel p_DobTitleMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JPanel p_dobMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_dob = new JLabel("Date of Birth:");
        lbl_dob.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JTextField txt_dob = new JTextField(10);
        txt_dob.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_dobMini.add(lbl_dob, BorderLayout.NORTH);
        p_dobMini.add(txt_dob, BorderLayout.SOUTH);
        p_DobTitleMini.add(p_dobMini, BorderLayout.WEST);
        JPanel p_titleMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_title = new JLabel("Title:"){{setBackground(Color.WHITE);}};
        lbl_title.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JTextField txt_title = new JTextField(5);
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
        p_personalInfo2headerAndBody.setBorder(new LineBorder(Color.GREEN, 2));
        JPanel p_personalInfo2 = new JPanel(new GridLayout(1,2)){{setBackground(Color.WHITE);}};
        p_personalInfo1.setBorder(new LineBorder(Color.GREEN, 2));

        JLabel lbl_perosnalInfo2 = new JLabel("Security Info:");
        lbl_perosnalInfo2.setFont(new Font("MuseoSans-900", Font.BOLD, 32));

        JPanel p_username = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_usernameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_username = new JLabel("Userame:");
        lbl_username.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JTextField txt_username = new JTextField(15);
        txt_username.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        p_usernameMini.add(lbl_username, BorderLayout.NORTH);
        p_usernameMini.add(txt_username, BorderLayout.SOUTH);
        p_username.add(p_usernameMini);

        JPanel p_password = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_passwordMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_password = new JLabel("Password:");
        lbl_password.setFont(new Font("MuseoSans-900", Font.PLAIN, 20));
        JPasswordField txt_passwprd = new JPasswordField(15);
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
        JButton submitButton = new JButton("Create Account");
        submitButton.setFont(new Font("MuseoSans-900", Font.BOLD, 20));
        p_submitButton.add(submitButton);


        mainPanel.add(p_header);
        mainPanel.add(p_personalInfo1Parent);
        mainPanel.add(p_personalInfo2Parent);
        mainPanel.add(p_submitButton);



        add(mainPanel);

    }
    
}
