package UserInterface;

import java.awt.*;
import java.util.concurrent.Flow;

import javax.swing.*;
import javax.swing.border.*;

public class Payment extends JPanel{

    public Payment(){

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        JPanel p_passenger = new JPanel(new BorderLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.GREEN, 2));}};
        JPanel p_title = new JPanel(new BorderLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.RED, 2));}};
        JLabel lbl_title = new JLabel("<html> <h1 style='font-family: MuseoSans-900;font-size:28px;'>Personal Info</h1> </html>");
        // lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
        // lbl_title.setBorder(new EmptyBorder(0,500,0,0));
        p_title.add(lbl_title, BorderLayout.CENTER);


        JPanel p_contents = new JPanel(new BorderLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.RED, 2));}};
        p_contents.setPreferredSize(new Dimension(800, 160));
        // p_contents.setLayout(new BoxLayout(p_contents, BoxLayout.Y_AXIS));

        JPanel p_contentTop = new JPanel(new FlowLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.RED, 2));}};
        JRadioButton rad_MR = new JRadioButton("<html><h3 style='font-family: MuseoSans-900;font-size:16px;'>&nbsp;&nbsp;Mr.</h3></html>"){{setBackground(Color.WHITE);}};
        JRadioButton rad_MRs = new JRadioButton("<html><h3 style='font-family: MuseoSans-900;font-size:16px;'>&nbsp;&nbsp;Mrs.</h3></html>"){{setBackground(Color.WHITE);}};
        ButtonGroup rad_group = new ButtonGroup();
        rad_group.add(rad_MRs);
        rad_group.add(rad_MR);

        
        // JPanel p_names = new JPanel();
        // p_names.setLayout(new BoxLayout(p_names, BoxLayout.Y_AXIS));

        JPanel p_name = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_name = new JLabel("First Name:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_name = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_name.add(lbl_name, BorderLayout.NORTH);
        p_name.add(txt_name, BorderLayout.SOUTH);
        
        JPanel p_lasName = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_lasName = new JLabel("Last Name:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_lasName = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_lasName.add(lbl_lasName, BorderLayout.NORTH);
        p_lasName.add(txt_lasName, BorderLayout.SOUTH);

        // p_names.add(p_name);
        // p_names.add(p_lasName);

        p_contentTop.add(rad_MR);
        p_contentTop.add(rad_MRs);
        p_contentTop.add(p_name);
        p_contentTop.add(p_lasName);


        JPanel p_contentBottom = new JPanel(new FlowLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.RED, 2));}};

        JPanel p_dob = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_dobMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_dob = new JLabel("Date of Birth:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_dob = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_dobMini.add(lbl_dob, BorderLayout.NORTH);
        p_dobMini.add(txt_dob, BorderLayout.CENTER);
        p_dob.add(p_dobMini);

        JPanel p_number = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_numberMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_number = new JLabel("Phone Number:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_number = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_numberMini.add(lbl_number, BorderLayout.NORTH);
        p_numberMini.add(txt_number, BorderLayout.CENTER);
        p_number.add(p_numberMini);

        JCheckBox chk_turk = new JCheckBox("<html><h3 style='font-family: MuseoSans-900;'>&nbsp;&nbsp;Turkish Citizen</h3></html>"){{setBackground(Color.WHITE);}};
        
        p_contentBottom.add(p_number);
        p_contentBottom.add(p_dob);
        p_contentBottom.add(chk_turk);

        p_contents.add(p_contentTop, BorderLayout.CENTER);
        p_contents.add(p_contentBottom, BorderLayout.SOUTH);

        p_passenger.add(p_title, BorderLayout.NORTH);
        p_passenger.add(p_contents, BorderLayout.CENTER);










        JPanel p_paymentMain = new JPanel(new BorderLayout()){{setBorder(new LineBorder(Color.RED, 2));}};

        // JPanel p_paymentTitle = new JPanel();
        JPanel p_paymentTitle = new JPanel(new BorderLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.RED, 2));}};
        JLabel lbl_paymentTitle = new JLabel("<html> <h1 style='font-family: MuseoSans-900;font-size:28px;'>Card Info</h1> </html>");
        p_paymentTitle.add(lbl_paymentTitle, BorderLayout.CENTER);

        JPanel p_cardInfo = new JPanel(new FlowLayout());

        JPanel p_personalInfo = new JPanel();
        p_personalInfo.setLayout(new BoxLayout(p_personalInfo, BoxLayout.Y_AXIS));
        
        // JPanel p_frstName = new JPanel();
        JPanel p_frstName = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_frstNameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_frstName = new JLabel("First Name:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_frstName = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_frstNameMini.add(lbl_frstName, BorderLayout.NORTH);
        p_frstNameMini.add(txt_frstName, BorderLayout.CENTER);
        p_frstName.add(p_frstNameMini); 

        // JPanel p_lastName = new JPanel();
        JPanel p_lastName = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_lastNameMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_lastName = new JLabel("Last Name:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_lastName = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_lastNameMini.add(lbl_lastName, BorderLayout.NORTH);
        p_lastNameMini.add(txt_lastName, BorderLayout.CENTER);
        p_lastName.add(p_lastNameMini); 

        // JPanel p_email = new JPanel(); 
        JPanel p_email = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_emailMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_email = new JLabel("Email:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_email = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_emailMini.add(lbl_email, BorderLayout.NORTH);
        p_emailMini.add(txt_email, BorderLayout.CENTER);
        p_email.add(p_emailMini); 

        p_personalInfo.add(p_frstName);
        p_personalInfo.add(p_lastName);
        p_personalInfo.add(p_email);


        JPanel p_cardinfoMini = new JPanel(new BorderLayout());
        // JPanel p_cardNumber = new JPanel(); 
        JPanel p_cardNumber = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_cardNumberMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_cardNumber = new JLabel("Email:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_cardNumber = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_cardNumberMini.add(lbl_cardNumber, BorderLayout.NORTH);
        p_cardNumberMini.add(txt_cardNumber, BorderLayout.CENTER);
        p_cardNumber.add(p_cardNumberMini); 

        JPanel p_cardDetails = new JPanel(new FlowLayout()); 

        JPanel p_expY = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_expYMini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_expY = new JLabel("Exp Year:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_expY = new JTextField(5){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_expYMini.add(lbl_expY, BorderLayout.NORTH);
        p_expYMini.add(txt_expY, BorderLayout.CENTER);
        p_expY.add(p_expYMini); 

        JPanel p_expM = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_expMmini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_expM = new JLabel("Exp Month:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_expM = new JTextField(5){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_expMmini.add(lbl_expM, BorderLayout.NORTH);
        p_expMmini.add(txt_expM, BorderLayout.CENTER);
        p_expM.add(p_expMmini); 

        JPanel p_CVC = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JPanel p_CVCmini = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        JLabel lbl_CVC = new JLabel("CVC:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        JTextField txt_CVC = new JTextField(5){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        p_CVCmini.add(lbl_CVC, BorderLayout.NORTH);
        p_CVCmini.add(txt_CVC, BorderLayout.CENTER);
        p_CVC.add(p_CVCmini); 

        p_cardDetails.add(p_expY);
        p_cardDetails.add(p_expM);
        p_cardDetails.add(p_CVC);


        p_cardinfoMini.add(p_cardNumber, BorderLayout.NORTH);
        p_cardinfoMini.add(p_cardDetails, BorderLayout.CENTER);

        p_cardInfo.add(p_personalInfo);
        p_cardInfo.add(p_cardinfoMini);


        p_paymentMain.add(p_paymentTitle, BorderLayout.NORTH);
        p_paymentMain.add(p_cardInfo, BorderLayout.CENTER);


        JPanel p_button = new JPanel(new BorderLayout()){{setBorder(new LineBorder(Color.RED, 2)); setPreferredSize(new Dimension(p_paymentMain.getWidth(), 60));}};
        JButton btn_final = new JButton("Checkout"){{setPreferredSize(new Dimension(300, p_button.getHeight()));}};
        p_button.add(btn_final, BorderLayout.EAST);


        mainPanel.add(p_passenger);
        mainPanel.add(p_paymentMain);
        mainPanel.add(p_button);
        // mainPanel.add(p_contact);


        add(mainPanel);
    }
}
