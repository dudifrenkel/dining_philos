import javax.swing.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class implements the philosophers problem
 */
public class PhiProb {

    private static final int DEFAULT_H_FRAME_SIZE = 200;
    private static final int DEFAULT_W_FRAME_SIZE = 200;
    private static final int RADIOS_NORM = 20;
    private static final String WELCOME_MSG = "          Welcome To The\n     Philosophers Problem!" +
                                             "\nPlease Insert The Philosophers number";

    public PhiProb(int philNum){
        JFrame app = new JFrame();
        int rad = philNum* RADIOS_NORM;
        Stick[] stickArr = createSticks(philNum);
        Philosopher[] phisArr = createPhis(philNum,stickArr);

        PhiGr frame = new PhiGr(philNum,rad,phisArr,stickArr);
        ExecutorService exe = Executors.newCachedThreadPool();

        for (Philosopher p:phisArr) {
            exe.execute(p);
        }
        app.add(frame);
        frame.repaint();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setSize(rad*2+DEFAULT_W_FRAME_SIZE, rad*2+DEFAULT_H_FRAME_SIZE);
        app.setVisible(true);
    }

    /* Create the philosophers and insert to each philosopher the relevant sticks */
    private Philosopher[] createPhis(int numPhis,Stick[] sticks){
        Philosopher[] phisArr = new Philosopher[numPhis];

        phisArr[0] = new Philosopher(sticks[numPhis - 1], sticks[0]); // set the relevant sticks for the first phil.
        for (int i = 1; i < numPhis ; i++) {
            phisArr[i] = new Philosopher(sticks[i - 1], sticks[i]);
        }
        return phisArr;
    }

    /* Create the Sticks */
    private Stick[] createSticks(int numPhis){
        Stick[] stickArr = new Stick[numPhis];
        for (int i=0;i<numPhis;i++){
            stickArr[i] = new Stick();
        }
        return stickArr;
    }

    public static void main(String[]args){
        int philNum = Integer.parseInt(JOptionPane.showInputDialog(WELCOME_MSG));
        if (philNum>1) {
            new PhiProb(philNum);
        }
    }
}
