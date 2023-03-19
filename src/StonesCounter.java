import java.awt.*;

class StonesCounter {
    int x, y;
    private String count;

    public void setCount(String count) {
        this.count = count;
    }

    StonesCounter(String count, int x, int y) { //constructoren bliver kaldt den vi laver lin object
        this.count = count;
        this.x = x;
        this.y = y;
    }
    void draw(Graphics g) {
        g.drawString(count, x, y);
    }
}