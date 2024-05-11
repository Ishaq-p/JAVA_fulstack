package UserInterface;

import javax.swing.*;
import java.awt.*;

public class DottedLine extends JComponent {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the color and stroke for the dotted line
        g2d.setColor(Color.BLACK);
        float[] dashPattern = {5, 5}; // 5 pixels on, 5 pixels off
        BasicStroke dashStroke = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 0, dashPattern, 0);
        g2d.setStroke(dashStroke);

        // Draw the dotted line
        int y = getHeight() / 2;
        g2d.drawLine(0, y, getWidth(), y);

        g2d.dispose();
    }

}