import java.awt.*;

public class Stone extends Figure {
    int r = 10;

    public Stone() {
    }

    @Override
    void draw(Graphics g, int x, int y) {
        g.setColor(Color.red);
        g.fillOval(x, y, r * 2, r * 2);
    }
}
