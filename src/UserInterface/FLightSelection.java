package UserInterface;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;

public class FLightSelection extends JPanel{

    private String lbl_fromTo  = "<html><h2>02:00</h2><h3 style='color: gray;font-family: MuseoSans-900;'>IST</h3><h5 style='color: gray;font-family: MuseoSans-900;'>Istanbul</h5></html>";
    

    public FLightSelection(int flightNum){

        if (flightNum>5){
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            add(scrollPane);

            JPanel p_window = new JPanel();
            p_window.setLayout(new BoxLayout(p_window, BoxLayout.Y_AXIS));

            for (int i=0;i<flightNum;i++){
                p_window.add(eachFlight());
            }
            scrollPane.setViewportView(p_window);
        }else{
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            for (int i=0;i<flightNum;i++){
                panel.add(eachFlight());
            }
            add(panel);
        }

        

    }

    public JPanel eachFlight(){
        JPanel p_entireWindow = new JPanel(new GridLayout()){{setBackground(Color.WHITE);}};
        p_entireWindow.setBorder(new LineBorder(Color.GREEN, 2));

        JPanel p_eachFlight = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};
        p_eachFlight.setBorder(new LineBorder(Color.RED, 2));


        JPanel p_flightDetials = new JPanel(new BorderLayout()){{setBackground(Color.WHITE);}};
        p_flightDetials.setBorder(new LineBorder(Color.RED, 2));

        JPanel p_fromTo = new JPanel(new FlowLayout()){{setBackground(Color.WHITE);}};;
        p_fromTo.setBorder(new LineBorder(Color.RED, 2));
        JPanel p_from = new JPanel(){{setBackground(Color.WHITE);}};;
        JLabel lbl_from = new JLabel(lbl_fromTo);
        p_from.add(lbl_from);

        JPanel p_fromToImg = new JPanel(){{setBackground(Color.WHITE);}};;
        ImageIcon icon = new ImageIcon("/home/ishaq/Documents/books/6th_semes/visualBasedProg/week10/VB_mainProj/fromTo.png"); // Replace "path/to/your/image.jpg" with the path to your image file
        JLabel lbl_img  = new JLabel(icon);
        p_fromToImg.add(lbl_img);

        JPanel p_to = new JPanel(){{setBackground(Color.WHITE);}};;
        JLabel lbl_to = new JLabel(lbl_fromTo);
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
        JLabel lbl_duration = new JLabel("Flight duration: 1hr 30m");
        p_duration.add(lbl_duration);
        JPanel p_aircraft = new JPanel();
        p_aircraft.setBackground(Color.WHITE);
        JLabel lbl_aircraft = new JLabel("Aircraft type: A-10;");
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
        JRadioButton rad_economy = new JRadioButton("<html><h5>Per passenger</h5> <h2>TRY 3.249</h2> </html>"){{setBackground(Color.WHITE);}};
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
        JRadioButton rad_buisness = new JRadioButton("<html><h5>Per passenger</h5> <h2>TRY 3.249</h2> </html>"){{setBackground(Color.WHITE);}};
        p_selectionBuis.add(rad_buisness);

        p_buisness.add(p_lblBuis, BorderLayout.NORTH);
        p_buisness.add(p_selectionBuis, BorderLayout.CENTER);

        ButtonGroup radGroup = new ButtonGroup();
        radGroup.add(rad_buisness);
        radGroup.add(rad_economy);

        p_flightType.add(p_economy);
        p_flightType.add(p_buisness);


        p_eachFlight.add(p_flightDetials);
        p_eachFlight.add(p_flightType);


        p_entireWindow.add(p_eachFlight);

        return p_entireWindow;
        // add(p_entireWindow);
        // pack();
        // setLayout(new FlowLayout());
        // setTitle("Flights to show");
        // setLocationRelativeTo(null);
        // setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        // setVisible(true);

        

    }

    
}

