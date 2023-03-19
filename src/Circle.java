import java.awt.*;

class Circle extends Figure {
    int x, y, r = 50;

    int counter = 0;

    public Circle() { //constructoren bliver kaldt den vi laver lin object
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    void draw(Graphics g, int x, int y) {
        this.x = x;
        this.y = y;
        g.fillOval(x - r, y - r, r * 2, r * 2);
    }
}