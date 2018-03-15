/**
 * This class represent a stick
 */
public class  Stick {
    private boolean free;
    private int x;
    private int y;

    public Stick(){
        free = true;
    }

    /**
     * This method represent a philosopher trying to get the stick - if its free set the stick to be not free
     * else wait until the stick get free
     */
    public synchronized boolean getStick(boolean isFirstStick) {
        if (isFirstStick) {
            while (!free) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (free) {
                free = false;
                return true;
            }

        } else {        // this isn't the first stick the philosopher want to pick
            if (!free){
                return false;   // This for releasing the first stick
            }
            else {
                free = false;
                return true;
            }
        }
        return false;
    }

    /**
     * The method represent a philosopher returning stick to public
     */
    public synchronized void returnStick(){
        free = true;
        notifyAll();
    }

    /**
     * Get the stick coordinates from PhiGr
     */
    public void setCoor(double x, double y) {
        this.x = (int)x;
        this.y = (int)y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
