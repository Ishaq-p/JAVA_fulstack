package UserInterface;

import java.io.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Properties;
import java.sql.Date;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.DateComponentFormatter;



public class MainPage extends JPanel{

    String[] uniqueFromAirports = {"JFK", "IST", "LHR", "CDG", "FRA", "MUC", "BCN", "LAX", "SYD", "ANK", "ADA", "ESB", "DLM", "AYT", "VAS", "TZX", "SZF", "EZS"};
    String[] uniqueToAirports = {"IST", "JFK", "LHR", "CDG", "FRA", "MUC", "BCN", "LAX", "SYD", "ANK", "ADA", "ESB", "DLM", "AYT", "VAS", "TZX", "SZF", "EZS"};

    private Properties p = new Properties();
    private UtilDateModel model = new UtilDateModel();
    private UtilDateModel model1 = new UtilDateModel();

    private SpinnerNumberModel spinner = new SpinnerNumberModel(1,1,Integer.MAX_VALUE, 1);
    private JSpinner passengerNum = new JSpinner(spinner){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));setBackground(Color.white); setPreferredSize(new Dimension(70, 40));}};

    private ButtonGroup radGroup = new ButtonGroup();
    public JRadioButton radBtn_roundTrip = new JRadioButton("Round Trip"){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));setBackground(Color.white);}};
    private JRadioButton radBtn_oneWay = new JRadioButton("One Way"){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));setBackground(Color.white);}};

    private JComboBox<String> input_from = new JComboBox<>(uniqueFromAirports){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));setBackground(Color.white);}};
    private JComboBox<String> input_to = new JComboBox<>(uniqueToAirports){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));setBackground(Color.white);}};
    private JDatePickerImpl datePicker = new JDatePickerImpl(new JDatePanelImpl(this.model, p), new DateComponentFormatter()){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));setBackground(Color.white);}};
    private JDatePickerImpl datePicker1 = new JDatePickerImpl(new JDatePanelImpl(this.model1, p), new DateComponentFormatter()){{setFont(new Font("MuseoSans-900", Font.PLAIN, 20));setBackground(Color.white);}};
    
    public String from;
    public String to;
    public java.sql.Date departDate;
    public java.sql.Date arrivalDate;
    public int passengerFinalNum;
    public boolean oneWay;

    public long miliSeconds1, miliSeconds2;


    public JButton searchButton = new JButton("Search Flights"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20)); setBackground(new Color(199, 10, 15)); setForeground(Color.white);}};


    public MainPage(){
        // body 
        JPanel p_body = new JPanel(new GridLayout(2,1));  // 21
        // p_body.setPreferredSize(new Dimension(1920,900));

        JPanel p_bigImage = new JPanel(){{setBackground(Color.white);}};  // 22
        ImageIcon bigicon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/bigImage.png"); // Replace "path/to/your/image.jpg" with the path to your image file
        Image scaledBigImage = bigicon.getImage().getScaledInstance(1920, 549, Image.SCALE_SMOOTH); // Set the desired width and height
        ImageIcon bigicon_scaled = new ImageIcon(scaledBigImage);
        JLabel bigImgLabel = new JLabel(bigicon_scaled);
        p_bigImage.add(bigImgLabel);

        JPanel p_searchArea = new JPanel(new BorderLayout()){{setBackground(Color.white);}};  // 23

        JPanel p_searchTop = new JPanel(new FlowLayout()){{setBackground(Color.white);}};   // 231
        JPanel p_searchTopLeft = new JPanel(){{setBorder(new EmptyBorder(0, 5, 0, 700));setBackground(Color.white);}};
        p_searchTopLeft.add(radBtn_roundTrip);
        p_searchTopLeft.add(radBtn_oneWay);
        radGroup.add(radBtn_roundTrip);
        radGroup.add(radBtn_oneWay);
        JPanel p_searchTopRight = new JPanel(){{setBackground(Color.white);}};
        p_searchTopRight.add(new JLabel("Gift a ticket"));

        p_searchTop.add(p_searchTopLeft);
        p_searchTop.add(p_searchTopRight);

        


        JPanel p_searchBottom = new JPanel(){{setBackground(Color.white);}}; // 232
        p_searchBottom.setLayout(new FlowLayout());

        JPanel p_from = new JPanel(new FlowLayout()){{setBackground(Color.white);}};
        JPanel p_fromMini = new JPanel(new BorderLayout()){{setBackground(Color.white);}};
        JLabel lbl_from = new JLabel("From:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20));}};
        p_fromMini.add(lbl_from, BorderLayout.NORTH);
        p_fromMini.add(input_from, BorderLayout.SOUTH);
        p_from.add(p_fromMini);
        JPanel p_to = new JPanel(new FlowLayout()){{setBackground(Color.white);}};
        JPanel p_toMini = new JPanel(new BorderLayout()){{setBackground(Color.white);}};
        JLabel lbl_to = new JLabel("To:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20));}};
        p_toMini.add(lbl_to, BorderLayout.NORTH);
        p_toMini.add(input_to, BorderLayout.SOUTH);
        p_to.add(p_toMini);

        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JPanel p_fromDate = new JPanel(new FlowLayout()){{setBackground(Color.white);}};
        JPanel p_fromDateMini = new JPanel(new BorderLayout()){{setBackground(Color.white);}};
        JLabel lblFromDate = new JLabel("Departure Time:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20));}};
        p_fromDateMini.add(lblFromDate);
        p_fromDateMini.add(datePicker, BorderLayout.SOUTH);
        p_fromDate.add(p_fromDateMini);
        JPanel p_toDate = new JPanel(new FlowLayout()){{setBackground(Color.white);}};
        JPanel p_toDateMini = new JPanel(new BorderLayout()){{setBackground(Color.white);}};
        JLabel lblToDate = new JLabel("Arrival Time:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20));}};
        p_toDateMini.add(lblToDate);
        p_toDateMini.add(datePicker1, BorderLayout.SOUTH);
        p_toDate.add(p_toDateMini);

        JPanel p_passengerNum = new JPanel(new FlowLayout()){{setBackground(Color.white);}};
        JLabel lblPassengerNum = new JLabel("Number of Passengers:"){{setFont(new Font("MuseoSans-900", Font.BOLD, 20));}};
        p_passengerNum.add(lblPassengerNum, BorderLayout.NORTH);
        p_passengerNum.add(passengerNum, BorderLayout.SOUTH);

        // JButton searchButton = new JButton("Search Flights");
        this.searchButton.setPreferredSize(new Dimension(300,60));
        

        p_searchBottom.add(p_from);
        p_searchBottom.add(p_to);
        p_searchBottom.add(p_fromDate);
        
        

        // p_searchBottom.add(toDateButton);
        radBtn_roundTrip.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (radBtn_roundTrip.isSelected()){
                    p_searchBottom.remove(p_passengerNum);
                    p_searchBottom.remove(searchButton);
                    p_searchBottom.add(p_toDate);
                    p_searchBottom.add(p_passengerNum);
                    p_searchBottom.add(searchButton);
                    revalidate();
                    repaint();
                }
            }
        });
        radBtn_oneWay.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if (radBtn_oneWay.isSelected()){
                    p_searchBottom.remove(p_passengerNum);
                    p_searchBottom.remove(searchButton);
                    p_searchBottom.remove(p_toDate);
                    p_searchBottom.add(p_passengerNum);
                    p_searchBottom.add(searchButton);
                    revalidate();
                    repaint();
                }
            }
        });
        
        p_searchBottom.add(p_passengerNum);
        p_searchBottom.add(searchButton);

        p_searchArea.add(p_searchTop, BorderLayout.NORTH);
        p_searchArea.add(p_searchBottom, BorderLayout.CENTER);



        p_body.add(p_bigImage);
        p_body.add(p_searchArea);

        add(p_body);
    }


    public void storeMainPageValues(){
        this.from = (String) input_from.getSelectedItem();
        this.to = (String) input_to.getSelectedItem();
        
        int[] dateInt = {this.datePicker.getModel().getYear(), this.datePicker.getModel().getMonth(), this.datePicker.getModel().getDay()};
        Calendar calender = Calendar.getInstance();
        calender.set(dateInt[0], dateInt[1], dateInt[2]);
        this.miliSeconds1 = calender.getTimeInMillis();
        this.departDate = new java.sql.Date(this.miliSeconds1);
        System.out.println(this.departDate);

        int[] dateInt1 = {this.datePicker1.getModel().getYear(), this.datePicker1.getModel().getMonth(), this.datePicker1.getModel().getDay()};
        Calendar calender1 = Calendar.getInstance();
        calender1.set(dateInt1[0], dateInt1[1], dateInt1[2]);
        this.miliSeconds2 = calender1.getTimeInMillis();
        this.arrivalDate = new java.sql.Date(this.miliSeconds2);
        System.out.println(this.arrivalDate);
        
        this.passengerFinalNum = (int) passengerNum.getValue();
        if (radBtn_oneWay.isSelected()){
            this.oneWay = true;
        }
        else{
            this.oneWay=false;
        }
    }


    public void emptyMainPageFields(){
        this.input_from.setSelectedItem(-1);;
        this.input_to.setSelectedIndex(-1);
        this.datePicker.getModel().setValue(null);
        this.datePicker1.getModel().setValue(null);
        this.passengerNum.setValue(1);
        this.radGroup.clearSelection();
    }

    public boolean check4Null(){ 
        if (this.radGroup.getSelection()==null || 
            this.datePicker.getModel().getValue()==null ||
            // this.datePicker1.getModel().getValue()==null ||
            this.input_from.getSelectedItem()==null || 
            this.input_to.getSelectedItem()==null ){
            
            return false;
        }else{
            return true;
        }
        
    }

    // public String[] flightSearch(){
    //     return String[];
    // }

    // public static void main(String[] args){
    //     MainPage frame = new MainPage();
    //     frame.setTitle("main page");
	// 	frame.setSize(1920, 1080);
	// 	frame.setLocationRelativeTo(null);
	// 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);
    // }
}
