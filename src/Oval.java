import java.awt.*;

class Oval extends Figure {
    int r = 90;
    public Oval() { //constructoren bliver kaldt den vi laver lin object
    }

    void draw(Graphics g, int x, int y) {
        g.fillOval(x, y, r, r * 2);
    }
}