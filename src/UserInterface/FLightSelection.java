package UserInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FLightSelection extends JPanel{
    public String flightSelected;
    public boolean isEco;
    private Map<JRadioButton, String> radioButtonFlightIdMap = new HashMap<>();

    private ButtonGroup radGroup = new ButtonGroup();
    public JButton btn_flightFinal = new JButton("Next"){{setPreferredSize(new Dimension(300, 80));}};

    private String flightID, from, to, departDateTime, departTime, arrivalTime, ecoPrice, buisPrice, flightDuration, planID, flightType, duration;
    // private int duration;

    // private FlightChecker flightChecker = new FLightSelection();

    public FLightSelection(){}

    public FLightSelection(List<String[]> flightDetails){

        // the button is assign above in the globle space
        JPanel p_flightCheckout = new JPanel();
        p_flightCheckout.add(btn_flightFinal, BorderLayout.EAST);

        if (flightDetails.size()>5){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);

            JPanel p_window = new JPanel();
            p_window.setLayout(new BoxLayout(p_window, BoxLayout.Y_AXIS));

            for (String[] details : flightDetails){
                assignGlobleVars(details);
                p_window.add(eachFlight());
            }
            scrollPane.setViewportView(p_window);
            p_flightCheckout.setBorder(new EmptyBorder(0,0,30,0));
            add(p_flightCheckout);

        }else if (flightDetails.size()>0 && flightDetails.size()<=5){
            setLayout(new BorderLayout());
            JPanel p_main = new JPanel();
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            for (String[] details : flightDetails){
                assignGlobleVars(details);
                panel.add(eachFlight());
            }
            p_main.add(panel);
            
            add(p_main, BorderLayout.CENTER);
            add(p_flightCheckout, BorderLayout.SOUTH);
        }else{
            JPanel panel = new JPanel(){{setBackground(Color.WHITE);}};
            JLabel lbl = new JLabel("No Flights, please try choosing different destinations!"){{setBackground(Color.WHITE);setFont(new Font("MuseoSans-900", Font.BOLD, 32));}};
            panel.add(lbl);

            add(panel);
        }

        // this.btn_flightFinal.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e){
        //         this.radGroup.Se
        //     }
        // });

        

    }

    public JPanel eachFlight(){
        String lbl_From  = String.format("<html><h2> %s </h2><h3 style='color: gray;font-family: MuseoSans-900;'> %s </h3><h5 style='color: gray;font-family: MuseoSans-900;'> %s </h5></html>", this.departTime, this.from, this.flightType);
        String lbl_To  = String.format("<html><h2> %s </h2><h3 style='color: gray;font-family: MuseoSans-900;'> %s </h3><h5 style='color: gray;font-family: MuseoSans-900;'> %s </h5></html>", this.arrivalTime, this.to, this.flightType);

        JPanel p_entireWindow = new JPanel(new GridLayout()){{setBackground(Color.WHITE);}};
        p_entireWindow.setBorder(new LineBorder(Color.GREEN, 2));

        JPanel p_eachFlight = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        p_eachFlight.setBorder(new LineBorder(Color.RED, 2));


        JPanel p_flightDetials = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        p_flightDetials.setBorder(new LineBorder(Color.RED, 2));

        JPanel p_fromTo = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};;
        p_fromTo.setBorder(new LineBorder(Color.RED, 2));
        JPanel p_from = new JPanel(){{setBackground(Color.WHITE);}};;
        JLabel lbl_from = new JLabel(lbl_From);
        p_from.add(lbl_from);

        JPanel p_fromToImg = new JPanel(){{setBackground(Color.WHITE);}};;
        ImageIcon icon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/fromTo.png"); // Replace "path/to/your/image.jpg" with the path to your image file
        JLabel lbl_img  = new JLabel(icon);
        p_fromToImg.add(lbl_img);

        JPanel p_to = new JPanel(){{setBackground(Color.WHITE);}};;
        JLabel lbl_to = new JLabel(lbl_To);
        p_to.add(lbl_to);

        p_fromTo.add(p_from);
        p_fromTo.add(p_fromToImg);
        p_fromTo.add(p_to);


        JPanel p_subFlightDetails = new JPanel(new FlowLayout()){{setPreferredSize(new Dimension(p_fromTo.getWidth(), 30));
                                                                setBackground(Color.WHITE);
                                                                setBackground(Color.WHITE);
                                                                setBorder(new LineBorder(Color.RED, 2));}};
        JPanel p_duration = new JPanel();
        p_duration.setBackground(Color.WHITE);
        JLabel lbl_duration = new JLabel(String.format("Flight duration: %s", this.duration));
        p_duration.add(lbl_duration);
        JPanel p_aircraft = new JPanel();
        p_aircraft.setBackground(Color.WHITE);
        JLabel lbl_aircraft = new JLabel(String.format("Aircraft type: %s;", this.planID));
        p_aircraft.add(lbl_aircraft);


        p_subFlightDetails.add(p_duration);
        p_subFlightDetails.add(p_aircraft);

        // DottedLine dottedLine = new DottedLine();
        p_flightDetials.add(p_fromTo, BorderLayout.CENTER);
        // p_subFlightDetails.add(dottedLine);
        p_flightDetials.add(p_subFlightDetails, BorderLayout.SOUTH);




        JPanel p_flightType = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);setBorder(new LineBorder(Color.RED, 2));}};
        // p_flightType.setBorder(new LineBorder(Color.RED, 2));
        
        JPanel p_economy = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);
                                                            setBorder(new LineBorder(Color.RED, 2));}};
        p_economy.setBackground(Color.WHITE);
        
        JPanel p_lblEco = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);
                                        // setBorder(new LineBorder(Color.RED, 2));
                                        setPreferredSize(new Dimension(p_economy.getWidth(), 31));
                                        setBorder(new EmptyBorder(0,0,0,0));
                                        setBackground(new Color(0xDFE4ED));}};

        JLabel lbl_economy = new JLabel("<html><h3>Economy</h3></html>");
        p_lblEco.add(lbl_economy, BorderLayout.NORTH);

        JPanel p_selectionEco = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JRadioButton rad_economy = new JRadioButton(String.format("<html><h5>Per passenger</h5> <h2>TRY %s</h2> </html>", this.ecoPrice)){{setBackground(Color.WHITE);}};
        p_selectionEco.add(rad_economy);

        p_economy.add(p_lblEco, BorderLayout.NORTH);
        p_economy.add(p_selectionEco, BorderLayout.CENTER);


        JPanel p_buisness = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);setBorder(new LineBorder(Color.RED, 2));}};
        JPanel p_lblBuis = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);
                                                            // setBorder(new LineBorder(Color.RED, 2));
                                                            setPreferredSize(new Dimension(p_buisness.getWidth(), 31));
                                                            setBorder(new EmptyBorder(0,0,0,0));
                                                            setBackground(new Color(0xF7EAE4));}};
        JLabel lbl_buisness = new JLabel("<html><h3>Buisness</h3></html>");
        p_lblBuis.add(lbl_buisness);

        JPanel p_selectionBuis = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        JRadioButton rad_buisness = new JRadioButton(String.format("<html><h5>Per passenger</h5> <h2>TRY %s</h2> </html>", this.buisPrice)){{setBackground(Color.WHITE);}};
        p_selectionBuis.add(rad_buisness);

        p_buisness.add(p_lblBuis, BorderLayout.NORTH);
        p_buisness.add(p_selectionBuis, BorderLayout.CENTER);

        radGroup.add(rad_buisness);
        radGroup.add(rad_economy);

        p_flightType.add(p_economy);
        p_flightType.add(p_buisness);


        p_eachFlight.add(p_flightDetials);
        p_eachFlight.add(p_flightType);

        p_entireWindow.add(p_eachFlight);

        radioButtonFlightIdMap.put(rad_economy, this.flightID);
        radioButtonFlightIdMap.put(rad_buisness, this.flightID);

        rad_economy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                assignSelectedFlight(rad_economy,true);
            }
        });
        rad_buisness.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                assignSelectedFlight(rad_buisness, false);
            }
        });

        return p_entireWindow;
    }

    public void assignGlobleVars(String[] flightDetails){
        // for(int i=0; i<flightDetails.length; i++){
        //     System.out.println(i+": "+ flightDetails[i]);
        // }
        this.flightID = flightDetails[0];
        this.from = flightDetails[1];
        this.to = flightDetails[2];
        this.planID = flightDetails[3];

        this.departDateTime = flightDetails[4];
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(this.departDateTime, formatter);
            this.departTime = dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

            int durationInt = Integer.parseInt(flightDetails[5]);
            LocalDateTime newDateTime = dateTime.plusMinutes(durationInt);
            this.arrivalTime = newDateTime.format(DateTimeFormatter.ofPattern("HH:mm"));

            this.duration = durationInt/60+"hr "+durationInt%60+"m";
    
        } catch (Exception e) {
            e.printStackTrace();
        }

        // this.passengeNumEco = flightDetails[6];
        // this.passengeNumBuis = flightDetails[7];
        this.flightType = flightDetails[8];
        this.ecoPrice = flightDetails[9];
        this.buisPrice = flightDetails[10];
    }    

    public void assignSelectedFlight(JRadioButton selectedRadioButton, boolean isEco) {
        // Get the flight ID associated with the selected radio button
        String flightId = radioButtonFlightIdMap.get(selectedRadioButton);
        this.flightSelected = flightId;
        this.isEco = isEco;
        // System.out.println(this.flightSelected+" "+this.flightClass);
    }
}

