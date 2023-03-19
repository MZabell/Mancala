import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//This class is responsible for creating the board and provide some methods for future usage.
// Basic idea is that the board is represented as a list. The value for each list-index is representing the amount of stones that are currently in the the hole (index)
//In the classes Board, Game, AI_algorithm the first 7 indexes of the list represent the AI's holes as well as the so-called winning hole and the remaining indexes represent the holes of the human-player

public class Board {
    ArrayList<Integer>holes_list = new ArrayList<Integer>();


    public Board(ArrayList l){
        this.holes_list = l;
    }

    public static void main(String[] args) {
        double nInfiniteDouble = Double.NEGATIVE_INFINITY;
        AI_algorithm ai_algorithm = new AI_algorithm(-1, -1);

        System.out.println(ai_algorithm.getHoleToChooseMax());
    }

    //The "Score" in this case tries to measure the success of a move on the board from the point of the AI (maxPlayer).
    public int giveScore(){
        return holes_list.get(6) - holes_list.get(13);
    }


    public void update_hole(int index, int new_value){
        holes_list.remove(index);
        holes_list.add(index, new_value);
    }

    public void createBoard(){
        holes_list.add(0, 4);
        holes_list.add(1, 4);
        holes_list.add(2, 4);
        holes_list.add(3, 4);
        holes_list.add(4, 4);
        holes_list.add(5, 4);
        holes_list.add(6, 0);
        holes_list.add(7, 4);
        holes_list.add(8, 4);
        holes_list.add(9, 4);
        holes_list.add(10, 4);
        holes_list.add(11, 4);
        holes_list.add(12, 4);
        holes_list.add(13, 0);
    }

    public void createRandomBoard(){
        holes_list.add(0, 0);
        holes_list.add(1, 0);
        holes_list.add(2, 0);
        holes_list.add(3, 0);
        holes_list.add(4, 0);
        holes_list.add(5, 0);
        holes_list.add(6, 40);
        holes_list.add(7, 0);
        holes_list.add(8, 0);
        holes_list.add(9, 0);
        holes_list.add(10, 0);
        holes_list.add(11, 0);
        holes_list.add(12, 0);
        holes_list.add(13, 8);
    }
    public ArrayList getBoard(){
        return holes_list;
    }

    public int getBoardSum(){
        return holes_list.get(0) + holes_list.get(1) + holes_list.get(2) + holes_list.get(3) + holes_list.get(4) + holes_list.get(5) + holes_list.get(7) + holes_list.get(8) + holes_list.get(9) + holes_list.get(10) + holes_list.get(11) + holes_list.get(12);
    }

    public int getMyBoardSum(boolean isAI){
        if(isAI){
            return holes_list.get(0) + holes_list.get(1) + holes_list.get(2) + holes_list.get(3) + holes_list.get(4) + holes_list.get(5);
        } else {
            return holes_list.get(7) + holes_list.get(8) + holes_list.get(9) + holes_list.get(10) + holes_list.get(11) + holes_list.get(12);
        }
    }

    public int returnCellValue(int index){
        return holes_list.get(index);
    }

    public int giveCellWithMostStones(Board board, boolean isAI){ //One of our simplifications: If the player can choose his hole, he takes the one with the most available stones.
        if(board.getMyBoardSum(isAI) == 0){
            return -1;
        }
        int maxAmount = -1;
        int answer = -1;
        if(isAI) {
            for (int i = 0; i < 7; i++) {
                if (holes_list.get(i) > maxAmount && i != 6) {
                    maxAmount = holes_list.get(i);
                    answer = i;
                }
            }
        } else {
            for (int i = 7; i < holes_list.size(); i++) {
                if (holes_list.get(i) > maxAmount && i != 13) {
                    maxAmount = holes_list.get(i);
                    answer = i;
                }
            }
        }
        return answer;
    }


    //Get the holes we are able to start with
    public List<Integer> givePossibleHoles(boolean isAI){
        LinkedList list = new LinkedList();
        if(isAI){
            for(int j = 0; j < 7; j++){
                if(holes_list.get(j) > 0 && j != 6){
                    list.add(j);
                }
            }
        } else {
            for(int z = 7; z < holes_list.size(); z++){
                if(holes_list.get(z) > 0 && z != 13){
                    list.add(z);
                }
            }
        }
        return list;
    }

    public int giveAmountInWinningCell(){
        return holes_list.get(6);
    }

    public String winner(){ //
        int sumFinal = getBoardSum();
        if(sumFinal == 0){
            if(holes_list.get(6) - holes_list.get(13) > 0){
                return("AI has won!");
            } else if(holes_list.get(6) - holes_list.get(13) < 0){
                return("Human has won!");
            } else {
                return null;
            }
        }
        return null;
    }
}


