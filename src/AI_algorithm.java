import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class AI_algorithm {
    private int holeToChooseMax;
    private int holeToChooseMin;

    public AI_algorithm(int holeToChooseMax, int holeToChooseMin) {
        this.holeToChooseMax = holeToChooseMax;
        this.holeToChooseMin = holeToChooseMin;
    }


    public int minimax(Board currentBoard, int depth, boolean maxPlayer, Game game) {

        ArrayList<Integer> list2 = (ArrayList<Integer>) currentBoard.getBoard().stream().collect(Collectors.toList());
        Board current = new Board(list2);


        if (depth == 0 || current.winner() != null) {
            return current.giveScore();
        }
        if (maxPlayer) {
            double maxEval = Double.NEGATIVE_INFINITY;

            Game game1 = new Game(current);


            Iterator<Integer> iteratorMax = current.givePossibleHoles(true).iterator();
            while (iteratorMax.hasNext()) {
                int temp = iteratorMax.next();
                int evaluation = minimax(game.makeMove(temp, current, false), depth - 1, false, game1);
                maxEval = Math.max((int) maxEval, evaluation);
                if (maxEval == evaluation) {
                    //holeToChooseMin = temp;
                    this.setHoleToChooseMax(temp);
                    //setHoleToChooseMax(temp);
                }
            }
            return (int) maxEval;

        } else {
            double minEval = Double.POSITIVE_INFINITY;

            Game game2 = new Game(current);

            Iterator<Integer> iteratorMin = current.givePossibleHoles(false).iterator();
            while (iteratorMin.hasNext()) {
                int tempMin = iteratorMin.next();
                int evaluation = minimax(game.makeMove(tempMin, current, true), depth - 1, true, game2);
                minEval = Math.min((int) minEval, evaluation);
                if (minEval == evaluation) {
                    //holeToChooseMin = tempMin;
                    this.setHoleToChooseMin(tempMin);
                    //setHoleToChooseMin(tempMin);
                    //System.out.println(holeToChooseMin);

                }
            }
            return (int) minEval;
        }
    }
    public int getHoleToChooseMin() {
        return holeToChooseMin;
    }

    public int getHoleToChooseMax() {
        return holeToChooseMax;
    }

    public void setHoleToChooseMin(int holeToChooseMin) {
        this.holeToChooseMin = holeToChooseMin;
    }

    public void setHoleToChooseMax(int holeToChooseMax) {
        this.holeToChooseMax = holeToChooseMax;
    }

}
