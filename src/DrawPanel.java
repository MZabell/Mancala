import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

class DrawPanel extends JPanel {
    private Image backgroundImage;
    final Dimension screenSize;
    public static int AIScore = 0;
    public int PLScore = 0;



    public DrawPanel() {
        screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setPreferredSize(screenSize);
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try {
            backgroundImage = ImageIO.read(Objects.requireNonNull(classLoader.getResource("./Wood.jpg")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setLayout(null);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage.getScaledInstance((int) screenSize.getWidth(), (int) screenSize.getHeight(), Image.SCALE_SMOOTH), 0, 0, null);



        int x = 0;
        for (Figure s : CODEGUI.listPlayer) { //draw Circles
            s.draw(g, 150 + x, 520);
            x += 200;
        }
        new Oval().draw(g, 1150, 255); //Player Pit

        x = 0;
        for (Figure s : CODEGUI.listAI) { //draw Circles
            s.draw(g, 150 + x, 160);
            x += 200;
        }
        new Oval().draw(g, 30, 255); //AI Pit

        g.setFont(new Font("Times New Roman", Font.BOLD, 25));
        g.drawString(CODEGUI.PlayerName + "-PIT", 1130, 245);
        g.drawString("AI-PIT", 35, 245);

        g.setFont(new Font("Times New Roman", Font.BOLD, 78));
        g.drawString("MANCALA", 450, 70);
        g.setColor(Color.orange);
        g.drawString("" + AIScore, 55, 370);
        g.drawString("" + checkScore(), 1175, 370);

        /*int count = 0;
        int count2;
        int y;

        for (ArrayList<Stone> al : CODEGUI.holes) { //draws stones for all cups
            count2 = 0;
            x = 0;
            y = 0;
            for (Stone s : al) {
                if (count2 < 4) {
                    if (count2 == 2) {
                        y = 30;
                        x = -10;
                    }
                    s.draw(g, CODEGUI.allCups.get(count).getX() - x, CODEGUI.allCups.get(count).getY() - y);
                    x += 40;
                    count2++;
                } else if (count < 6){
                    CODEGUI.countersPL.get(count).setCount("+" + (al.size() - 4));
                } else {
                    CODEGUI.countersAI.get(count % 6).setCount("+" + (al.size() - 4));
                }
            }
            count++;
        }*/
        drawStones(g);

        g.setFont(new Font("Times New Roman", Font.BOLD, 20));
        for (StonesCounter s : CODEGUI.countersPL) {
            s.draw(g);
        }

        for (StonesCounter s : CODEGUI.countersAI) {
            s.draw(g);
        }

    }

    void drawStones(Graphics g) {
        int count = 0;
        int count2;
        int y, x;

        for (ArrayList<Stone> al : CODEGUI.holes) { //draws stones for all cups
            count2 = 0;
            x = 0;
            y = 0;
            if (al.isEmpty() && count < 6) {
                CODEGUI.countersPL.get(count).setCount("");
            }
            if ( count < 12) {
                for (Stone s : al) {
                    if (count2 < 4) {
                        if (count2 == 2) {
                            y = 30;
                            x = -10;
                        }
                        s.draw(g, CODEGUI.allCups.get(count).getX() - x, CODEGUI.allCups.get(count).getY() - y);
                        x += 40;
                        count2++;
                    } else if (count < 6) {
                        CODEGUI.countersPL.get(count).setCount("+" + (al.size() - 4));
                    } else {
                        CODEGUI.countersAI.get(CODEGUI.countersAI.size() - count % 6 - 1).setCount("+" + (al.size() - 4));
                    }
                }
            }
                count++;
        }
    }

    int checkScore() {
            return CODEGUI.holes.get(6).size();
            //System.out.println(PLScore);

            //PLScore = CODEGUI.holes.get(11).size();
    }
}