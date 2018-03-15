import java.awt.*;
import java.util.Random;

/**
 * This class represent a philosopher
 */
public class Philosopher extends Thread{
    private static final int MAX_EATING_TIME = 800;
    private static final int MAX_THINKING_TIME = 1500;

    private int x;
    private int y;
    private boolean isEating;
    Random rnd;
    Stick firstStick;
    Stick secondStick;
    PhiGr frame;

    public Philosopher(Stick f, Stick s){
        isEating = false;
        firstStick = f;
        secondStick = s;
        rnd = new Random();
    }

    @Override
    public void run() {

        while (true) {
            firstStick.getStick(true);
            if(!secondStick.getStick(false)){
                firstStick.returnStick();      // can't pick the second stick - return the first one to prevent deadlock
                continue;
            }
            isEating = true;
            frame.repaint();

            wasteTime(MAX_EATING_TIME);
            isEating = false;
            frame.repaint();

            firstStick.returnStick();
            secondStick.returnStick();
            wasteTime(MAX_THINKING_TIME);
        }
    }

    /**
     * Get the philosopher coordinates from PhiGr
     */
    public void setCoorFrame(double x, double y, PhiGr frame) {
        this.x = (int)x;
        this.y = (int)y;
        this.frame = frame;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Returns the color red if eating otherwise black
     */
    public Color getColor() {
        if (isEating){
            return Color.RED;
        }
        return Color.BLACK;
    }

    /* This method makes the philosopher eat/think for random time */
    private void wasteTime(int limit){
        try {
            sleep(rnd.nextInt(limit));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
