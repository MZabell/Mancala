import javax.swing.*;
import java.util.ArrayList;

public class Game {
    Board board;

    public Game(Board board1){
        this.board = board1;
    }

    public boolean isGameDone(){
        if(board.getBoardSum() == 0){
            return true;
        } else {
            return false;
        }
    }

    public String returnWinner(){
        if(board.returnCellValue(6) - board.returnCellValue(13) > 0){
            return("Winner: AI!");
        } else if(board.returnCellValue(6) - board.returnCellValue(13) < 0){
            return ("Winner:Player");
        } else {
            return("Draw!");
        }
    }

    public Board makeMove(int startingIndex, Board currentBoard, boolean isAI) {
        if(startingIndex == -1){
            return currentBoard;
        }
        if(currentBoard.getMyBoardSum(isAI) == 1 && (Integer)currentBoard.getBoard().get(startingIndex) == 1 && (startingIndex == 6 || startingIndex == 12)){
            if(isAI){
                currentBoard.update_hole(6, 0);
                currentBoard.update_hole(7, (Integer)currentBoard.getBoard().get(7) + 1);
                return currentBoard;
            } else {
                currentBoard.update_hole(12, 0);
                currentBoard.update_hole(13, (Integer)currentBoard.getBoard().get(13) + 1);
                return currentBoard;
            }
        } else {
            if(currentBoard.getMyBoardSum(isAI) == 0){
                return currentBoard;
            }
        }



        int temp_cell = startingIndex;
        int temp_stones = currentBoard.returnCellValue(startingIndex);
        boolean moveFinished = false;
        if(temp_stones == 0){
            moveFinished = true;
        }

        if (isAI) {
            while (!moveFinished) {
                currentBoard.update_hole(temp_cell, 0);
                while (temp_stones != 0) {
                    temp_cell++;
                    if (temp_cell == 13 || temp_cell == 14) {
                        temp_cell = 0;
                    }
                    currentBoard.update_hole(temp_cell, currentBoard.returnCellValue(temp_cell) + 1);
                    temp_stones--;
                }
                if (currentBoard.returnCellValue(temp_cell) == 1) {
                    moveFinished = true;
                    //break;
                } else {
                    //case when I'm at my winning hole
                    if (temp_cell == 6) {
                        temp_cell = currentBoard.giveCellWithMostStones(currentBoard, isAI);
                        if (temp_cell == -1) {
                            moveFinished = true;
                        }
                    }
                    if (temp_cell != -1) {
                        temp_stones = currentBoard.returnCellValue(temp_cell);
                    }
                }
            }
            return currentBoard;
        } else {
            while (!moveFinished) {
                currentBoard.update_hole(temp_cell, 0);
                while (temp_stones != 0) {
                    temp_cell++;
                    if (temp_cell == 6) {
                        temp_cell = 7;
                    } else if(temp_cell == 14){
                        temp_cell = 0;
                    }
                    currentBoard.update_hole(temp_cell, currentBoard.returnCellValue(temp_cell) + 1);
                    temp_stones--;
                }
                if (currentBoard.returnCellValue(temp_cell) == 1) {
                    moveFinished = true;
                } else {
                    //case when I'm at my winning hole
                    if (temp_cell == 13) {
                        temp_cell = currentBoard.giveCellWithMostStones(currentBoard, isAI);
                        if (temp_cell == -1) {
                            moveFinished = true;
                        }
                    }
                    if (temp_cell != -1) {
                        temp_stones = currentBoard.returnCellValue(temp_cell);
                    }
                }
            }
            return currentBoard;
        }
    }


    public static void main(String[]args) throws InterruptedException {

        /* //pop up window
        JFrame popUp = new JFrame();
        popUp.setLocationRelativeTo(null);
        PlayerName = JOptionPane.showInputDialog(popUp, "What is your name?", null);
        */


        JFrame frame = new CODEGUI();
        frame.setTitle("Mancala - Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setVisible(true);

        ArrayList<Integer>listOfHoles = new ArrayList<Integer>();
        Board main_board = new Board(listOfHoles);
        main_board.createBoard();
        //main_board.createRandomBoard();
        Game main_game = new Game(main_board);

        AI_algorithm alg = new AI_algorithm(-1, -1);

        int counterRounds = 1;
        //Start of play
        while(main_board.winner() == null){
            System.out.println("Round: " + counterRounds);

            if(counterRounds == 24){
                int hdhfhfh = 1;
            }


            alg.setHoleToChooseMax(-1);
            int acdef = alg.minimax(main_board, 7, true, main_game);

            int hole = alg.getHoleToChooseMax();
            System.out.println("AI's choice:");
            System.out.println(hole);
            //Thread.sleep(2000);
            System.out.println(main_game.makeMove(hole, main_board, true).getBoard());

            main_board = main_game.makeMove(hole, main_board, true);
            main_game = new Game(main_board);
            //Thread.sleep(2000);


            alg.setHoleToChooseMin(-1);
            alg.minimax(main_board, 2, false, main_game);
            int hole2 = alg.getHoleToChooseMin();
            System.out.println("Human's choice:");
            System.out.println(hole2);
            //Thread.sleep(2000);
            System.out.println(main_game.makeMove(hole2, main_board, false).getBoard());
            //Thread.sleep(2000);
            main_board = main_game.makeMove(hole2, main_board, true);
            main_game = new Game(main_board);
            counterRounds++;

/*
            int second = alg.minimax(main_board, 1, false, main_game);
            int hole2 = alg.getHoleToChoose();
            System.out.println(main_game.makeMove(hole2, main_board, false).getBoard());

            main_board = main_game.makeMove(hole2, main_board, false);
            main_game = new Game(main_board);

 */

        }
        System.out.println(main_board.winner());

        /*
        int firstRound = alg.minimax(main_board, 4, true, main_game); //I make a move + board changes
        int first = alg.getHoleToChoose();
        System.out.println(main_game.makeMove(first, main_board, true).getBoard());



        int secondRound = alg.minimax(main_game.makeMove(first, main_board, true), 4, true, main_game);// Make next move + board changes
        int second = alg.getHoleToChoose();
        System.out.println(main_game.makeMove(second, main_game.makeMove(first, main_board, true), true).getBoard());

        int thirdRound = alg.minimax(main_game.makeMove(second, main_game.makeMove(first, main_board, true), true), 4, true, main_game);
        int third = alg.getHoleToChoose();
        System.out.println(main_game.makeMove(third, main_game.makeMove(second, main_game.makeMove(first, main_board, true), true), true).getBoard());

        int fourthround = alg.minimax(main_game.makeMove(third, main_game.makeMove(second, main_game.makeMove(first, main_board, true), true), true),4, true, main_game);
        int fourth = alg.getHoleToChoose();
        System.out.println(main_game.makeMove(fourth, main_game.makeMove(third, main_game.makeMove(second, main_game.makeMove(first, main_board, true), true), true), true).getBoard());


         */


        //TIL MARCUS - her skal du lave selve if statementet og anden sidste linje der hvor den skriver vinder string ud
        //if (EndGame) {
        //   JFrame winner = new JFrame();
        //winner.setLocationRelativeTo(null);
        //JOptionPane.showMessageDialog(winner, "The Winner Is " + AI + " " + PlayerName); //pop-up window
        //System.exit(0); //Closes the program after the user presses "ok"
        //}
    }

}
