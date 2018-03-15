import javax.swing.*;
import java.awt.*;

/**
 * This class implements the graphic components in the philosophers problem
 */
public class PhiGr extends JPanel{
    private int DEF_PHIL_H = 30;
    private int DEF_PHIL_W = 30;

    private double rad;
    int philNum;
    Philosopher[] phils;
    Stick[] sticks;

    public PhiGr(int philNum, int rad, Philosopher[] phils, Stick[] sticks){
        super();
        this.rad = rad;
        this.phils = phils;
        this.sticks = sticks;
        this.philNum = philNum;
        setCoors();
    }

    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);
        for (Philosopher phil:phils) {
            graphics.setColor(phil.getColor());
            graphics.fillOval(phil.getX(),phil.getY(),DEF_PHIL_W,DEF_PHIL_H);
        }

        for (Stick st:sticks) {
            graphics.setColor(Color.BLACK);
            graphics.drawLine(st.getX(),st.getY(),st.getX()+DEF_PHIL_H,st.getY());
        }
    }

    /* This method set the coordinate for philosophers and sticks in the panel */
    private void setCoors(){
        int philsAndSticks = philNum * 2;
        double width = (rad * 2) + 50;
        double angle, x, y;

        for (int s=0,p=0,i=0; i<philsAndSticks; i++) {
            angle = ((double)i / (philsAndSticks / 2)) * Math.PI; // Calculate the angle at which the element will be placed.
            x = (rad * Math.cos(angle)) + (width / 2); // Calculate the x position of the element.
            y = (rad * Math.sin(angle)) + (width / 2); // Calculate the y position of the element.

            if (i % 2 == 0) {
                phils[p].setCoorFrame(x, y, this);
                p++;
            } else {
                sticks[s].setCoor(x, y);
                s++;
            }
        }
    }
}
