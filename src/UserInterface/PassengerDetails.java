package UserInterface;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PassengerDetails extends JPanel{

    public JButton btn_passengerFinal = new JButton("Next"){{setPreferredSize(new Dimension(300, 80));}};


    public PassengerDetails(int passengerNum){

        // the button is assign above in the globle space
        JPanel p_passengerCheckout = new JPanel();
        p_passengerCheckout.add(btn_passengerFinal, BorderLayout.EAST);



        // JPanel p_contact = new JPanel(new BorderLayout());
        // JPanel p_contactTitle = new JPanel(new BorderLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.RED, 2));}};
        // JLabel lbl_contactTitle = new JLabel("<html> <h1 style='font-family: MuseoSans-900;font-size:28px;'>Contact Info</h1> </html>");
        // lbl_contactTitle.setBorder(new EmptyBorder(0,500,0,0));
        // p_contactTitle.add(lbl_contactTitle, BorderLayout.CENTER);

        // JPanel p_contactContents = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        // JPanel p_email = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        // JLabel lbl_email = new JLabel("Email:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        // JTextField txt_email = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        // p_email.add(lbl_email, BorderLayout.NORTH);
        // p_email.add(txt_email, BorderLayout.SOUTH);
        // p_contactContents.add(p_email);
        // JPanel p_number = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        // JLabel lbl_number = new JLabel("Phone Number:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        // JTextField txt_number = new JTextField(15){{setFont(new Font("MuseoSans-900", Font.BOLD, 16));}};
        // p_number.add(lbl_number, BorderLayout.NORTH);
        // p_number.add(txt_number, BorderLayout.SOUTH);
        // p_contactContents.add(p_number);

        // p_contact.add(p_contactTitle, BorderLayout.NORTH);
        // p_contact.add(p_contactContents, BorderLayout.CENTER);

        if (passengerNum>4){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);

            JPanel p_window = new JPanel();
            p_window.setLayout(new BoxLayout(p_window, BoxLayout.Y_AXIS));

            for (int i=0;i<passengerNum;i++){
                p_window.add(eachPassengerDetails());
            }
            // p_window.add(p_contact);
            scrollPane.setViewportView(p_window);
            p_passengerCheckout.setBorder(new EmptyBorder(0,0,30,0));
            add(p_passengerCheckout);
        }else{
            setLayout(new BorderLayout());
            JPanel p_main = new JPanel();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            for (int i=0;i<passengerNum;i++){
                panel.add(eachPassengerDetails());
            }
            p_main.add(panel);
            
            add(p_main, BorderLayout.CENTER);
            add(p_passengerCheckout, BorderLayout.SOUTH);
            // add(p_contact);
        }
        
    }


    public JPanel eachPassengerDetails(){

        JPanel p_passenger = new JPanel(new BorderLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.GREEN, 2));}};
        JPanel p_title = new JPanel(new BorderLayout()){{setBackground(Color.WHITE); setBorder(new LineBorder(Color.RED, 2));}};
        JLabel lbl_title = new JLabel("<html> <h1 style='font-family: MuseoSans-900;font-size:28px;'>1. Passenger Info</h1> </html>");
        // lbl_title.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_title.setBorder(new EmptyBorder(0,500,0,0));
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

        JCheckBox chk_turk = new JCheckBox("<html><h3 style='font-family: MuseoSans-900;'>&nbsp;&nbsp;Turkish Citizen</h3></html>"){{setBackground(Color.WHITE);}};
        
        p_contentBottom.add(p_dob);
        p_contentBottom.add(chk_turk);

        p_contents.add(p_contentTop, BorderLayout.CENTER);
        p_contents.add(p_contentBottom, BorderLayout.SOUTH);



        p_passenger.add(p_title, BorderLayout.NORTH);
        p_passenger.add(p_contents, BorderLayout.CENTER);

        return p_passenger;
        // add(p_passenger);

    }


    
}
