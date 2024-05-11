package UserInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;
import java.util.concurrent.Flow;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.UtilDateModel;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.DateComponentFormatter;



public class MainPage extends JPanel{

    private JRadioButton radBtn_roundTrip = new JRadioButton("Round Trip");
    private JRadioButton radBtn_oneWay = new JRadioButton("One Way");
    private JRadioButton radBtn_multiCity = new JRadioButton("Multy City");

    private JTextField input_from = new JTextField(15);
    private JTextField input_to = new JTextField(15);

    private UtilDateModel model = new UtilDateModel();
    private UtilDateModel model1 = new UtilDateModel();

    public JButton searchButton = new JButton("Search Flights");

    public MainPage(){
        // body 
        JPanel p_body = new JPanel(new GridLayout(2,1));  // 21
        // p_body.setPreferredSize(new Dimension(1920,900));

        JPanel p_bigImage = new JPanel();  // 22
        ImageIcon bigicon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/bigImage.png"); // Replace "path/to/your/image.jpg" with the path to your image file
        Image scaledBigImage = bigicon.getImage().getScaledInstance(1920, 549, Image.SCALE_SMOOTH); // Set the desired width and height
        ImageIcon bigicon_scaled = new ImageIcon(scaledBigImage);
        JLabel bigImgLabel = new JLabel(bigicon_scaled);
        p_bigImage.add(bigImgLabel);

        JPanel p_searchArea = new JPanel(new BorderLayout());  // 23

        JPanel p_searchTop = new JPanel(new BorderLayout());   // 231
        p_searchTop.setBorder(new EmptyBorder(0, 100, 0, 500)); // top, left, bottom, right
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
        p_searchBottom.setLayout(new FlowLayout());

        JPanel p_from = new JPanel(new FlowLayout());
        JPanel p_fromMini = new JPanel(new BorderLayout());
        p_fromMini.add(new JLabel("From:"), BorderLayout.NORTH);
        p_fromMini.add(input_from, BorderLayout.SOUTH);
        p_from.add(p_fromMini);
        JPanel p_to = new JPanel(new FlowLayout());
        JPanel p_toMini = new JPanel(new BorderLayout());
        p_toMini.add(new JLabel("To:"), BorderLayout.NORTH);
        p_toMini.add(input_to, BorderLayout.SOUTH);
        p_to.add(p_toMini);

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JPanel p_fromDate = new JPanel(new FlowLayout());
        JPanel p_fromDateMini = new JPanel(new BorderLayout());
        JLabel lblFromDate = new JLabel("Departure:");
        JDatePickerImpl datePicker = new JDatePickerImpl(new JDatePanelImpl(this.model, p), new DateComponentFormatter());
        p_fromDateMini.add(lblFromDate);
        p_fromDateMini.add(datePicker, BorderLayout.SOUTH);
        p_fromDate.add(p_fromDateMini);
        JPanel p_toDate = new JPanel(new FlowLayout());
        JPanel p_toDateMini = new JPanel(new BorderLayout());
        JLabel lblToDate = new JLabel("Arrival:");
        JDatePickerImpl datePicker1 = new JDatePickerImpl(new JDatePanelImpl(this.model1, p), new DateComponentFormatter());
        p_toDateMini.add(lblToDate);
        p_toDateMini.add(datePicker1, BorderLayout.SOUTH);
        p_toDate.add(p_toDateMini);

        JPanel p_passengerNum = new JPanel(new FlowLayout());
        JLabel lblPassengerNum = new JLabel("Number of Passengers:");
        SpinnerNumberModel spinner = new SpinnerNumberModel(1,1,Integer.MAX_VALUE, 1);
        JSpinner passenderNum = new JSpinner(spinner);
        p_passengerNum.add(lblPassengerNum, BorderLayout.NORTH);
        p_passengerNum.add(passenderNum, BorderLayout.SOUTH);

        // JButton searchButton = new JButton("Search Flights");
        this.searchButton.setPreferredSize(new Dimension(300,60));
        



        p_searchBottom.add(p_from);
        p_searchBottom.add(p_to);
        p_searchBottom.add(p_fromDate);
        p_searchBottom.add(p_toDate);
        p_searchBottom.add(p_passengerNum);
        p_searchBottom.add(searchButton);

        // p_searchBottom.add(toDateButton);

        p_searchArea.add(p_searchTop, BorderLayout.NORTH);
        p_searchArea.add(p_searchBottom, BorderLayout.CENTER);



        p_body.add(p_bigImage);
        p_body.add(p_searchArea);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                add(new FLightSelection());
                revalidate();
                repaint();
            }
        });


        add(p_body);

        // setTitle("MainPage");
        // setSize(1920,1080);
        // setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void showDatePicker(JTextField textField) {
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        datePicker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("dateSelected")) {
                    textField.setText(datePicker.getJFormattedTextField().getText());
                }
            }
        });
        JPopupMenu popupMenu = new JPopupMenu();
        popupMenu.add(datePicker);
        popupMenu.show(textField, 0, textField.getHeight());
    }

    // public static void main(String[] args){
    //     MainPage frame = new MainPage();
    //     frame.setTitle("main page");
	// 	frame.setSize(1920, 1080);
	// 	frame.setLocationRelativeTo(null);
	// 	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// frame.setVisible(true);
    // }
}
