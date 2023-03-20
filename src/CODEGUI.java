import javax.swing.*;
import java.util.ArrayList;

public class CODEGUI extends JFrame {
    public static String PlayerName;
    public static String AI = "AI";
    public static ArrayList<ArrayList<Stone>> holes; //2D Array of all cups and pits'


    public static ArrayList<Circle> allCups;


    public static ArrayList<Figure> circle1PL; //Circle
    public static ArrayList<Figure> circle2PL; //Circle
    public static ArrayList<Figure> circle3PL; //Circle
    public static ArrayList<Figure> circle4PL; //Circle
    public static ArrayList<Figure> circle5PL; //Circle
    public static ArrayList<Figure> circle6PL; //Circle
    public static ArrayList<Stone> pitPL; //Player pit
    public static ArrayList<Figure> listPlayer; //Circles
    public static ArrayList<Figure> listPLStones; //Stones
    public static ArrayList<JButton> listButtonsPL; //Player Buttons
    public static ArrayList<StonesCounter> countersPL;
    public static ArrayList<Figure> listAI; //Circles
    public static ArrayList<JButton> listButtonsAI; //AI Buttons
    public static ArrayList<Figure> listAIStones; //AI Stones
    public static ArrayList<StonesCounter> countersAI; //AI hole counter
    public static ArrayList<Figure> circle1AI; //Circle1
    public static ArrayList<Figure> circle2AI; //Circle2
    public static ArrayList<Figure> circle3AI; //Circle3
    public static ArrayList<Figure> circle4AI; //Circle4
    public static ArrayList<Figure> circle5AI; //Circle5
    public static ArrayList<Figure> circle6AI; //Circle6
    public static ArrayList<Stone> pitAI; //AI pit

    DrawPanel dp;

    public CODEGUI() {
        allCups = new ArrayList<>();
        holes = new ArrayList<>();
        listPlayer = new ArrayList<>();
        listButtonsPL = new ArrayList<>();
        listPLStones = new ArrayList<>();
        countersPL = new ArrayList<>();
        circle1PL = new ArrayList<>();
        circle2PL = new ArrayList<>();
        circle3PL = new ArrayList<>();
        circle4PL = new ArrayList<>();
        circle5PL = new ArrayList<>();
        circle6PL = new ArrayList<>();
        pitPL = new ArrayList<>();


        listAIStones = new ArrayList<>();
        listAI = new ArrayList<>();
        listButtonsAI = new ArrayList<>();
        countersAI = new ArrayList<>();
        circle1AI = new ArrayList<>();
        circle2AI = new ArrayList<>();
        circle3AI = new ArrayList<>();
        circle4AI = new ArrayList<>();
        circle5AI = new ArrayList<>();
        circle6AI = new ArrayList<>();
        pitAI = new ArrayList<>();

        dp = new DrawPanel();
        add(dp); //Add drawpanel (dp)
        pack();
        int x;

        //Circles
        for (int i = 0; i < 6; i++) { //player circles
            listPlayer.add(new Circle());
        }
        //listPlayer.add(new Oval());



        x = 0;
        for (int i = 0; i < 6; i++) { //small counters in the circles
            countersPL.add(new StonesCounter("", 135 + x, 486)); //Player Counters
            x += 200;
        }

        for (int i = 0; i < 6; i++) {
            allCups.add((Circle) listPlayer.get(i));
        }

        x = 0;
        for (int i = 0; i < 6; i++) { //Player Buttons
            listButtonsPL.add(new Button(105 + x, 475, 90, 90, dp));
            listButtonsPL.get(i).setOpaque(false);
            listButtonsPL.get(i).setContentAreaFilled(false);
            listButtonsPL.get(i).setBorderPainted(false);
            dp.add(listButtonsPL.get(i));
            x += 200;
        }


        //***********************AI Part**********************//


        for (int j = 0; j < 6; j++) { //AI Circles
            listAI.add(new Circle());
        }
        //listAI.add(new Oval());

        x = 0;
        for (int i = 0; i < 6; i++) { //AI Buttons
            listButtonsAI.add(new Button(105 + x, 115, 90, 90, dp));
            listButtonsAI.get(i).setOpaque(false);
            listButtonsAI.get(i).setContentAreaFilled(false);
            listButtonsAI.get(i).setBorderPainted(false);
            dp.add(listButtonsAI.get(i));
            x += 200;
        }


        x = 0;
        for (int i = 0; i < 6; i++) {
            countersAI.add(new StonesCounter("", 135 + x, 125));//AI Counters
            x += 200;
        }

        for (int i = 0; i < 12; i++) { //adder arraylist to holes
            if (i == 6) {
                holes.add(pitPL);
            }
            holes.add(new ArrayList<>());
        }
        holes.add(pitAI);

        int k = 0;
        for (ArrayList<Stone> al : holes) { //adder stones to arraylist start psition
            if (al != pitPL && al != pitAI) {
                for (int i = 0; i < 4; i++) {
                    al.add(new Stone());
                }
            }
            k++;
        }

        for (int i = 0; i < 6; i++) {
            allCups.add((Circle) listAI.get(i));
        }


        //Prints out the different arraylist and show the content
        for (int i = 0; i < holes.size(); i++) {
            for (int j = 0; j < holes.get(i).size(); j++) {
                System.out.print(holes.get(i).get(j) + " ");
            }
            System.out.println();
        }

       /* new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            dp.repaint();
        }).start();*/
    }
    public static void main(String[] args) {
    }
}