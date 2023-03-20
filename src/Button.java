import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton implements ActionListener {
    int x, y, w, h;
    DrawPanel dp;

    Button(int x, int y, int w, int h, DrawPanel dp) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        setBounds(x, y, w, h);
        addActionListener(this);
        this.dp = dp;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        int counter = 1;
        for (Stone s : CODEGUI.holes.get(CODEGUI.listButtonsPL.indexOf(this))) {
            if (CODEGUI.listButtonsPL.indexOf(this) + counter >= 14) {
                counter = 1;
            }
                CODEGUI.holes.get(CODEGUI.listButtonsPL.indexOf(this) + counter).add(s);
            counter++;
        }
        CODEGUI.holes.get(CODEGUI.listButtonsPL.indexOf(this)).removeAll(CODEGUI.holes.get(CODEGUI.listButtonsPL.indexOf(this)));
        dp.checkScore();
        dp.repaint();
    }
}