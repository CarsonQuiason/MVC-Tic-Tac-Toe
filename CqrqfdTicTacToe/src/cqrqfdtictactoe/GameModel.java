package cqrqfdtictactoe;

import java.util.ArrayList;

/**
 * @author Carson Rottinghaus
 * Utilizes singleton class architecture
 * https://www.tutorialspoint.com/java/java_using_singleton.htm
 */

public class GameModel extends userList implements TicTacToe{
    
    static int[][] board = {{0,0,0},{0,0,0},{0,0,0}};
    ArrayList <PlayerModel> userArray = new ArrayList <>();
    boolean p1Turn = true;
    boolean hasBot;
    boolean gameLoaded;
    PlayerModel p1,p2;
    
    private static GameModel gameModel = null;
    
    private GameModel(){       
    }

    public GameModel init(PlayerModel p1, PlayerModel p2){
        this.p1 = p1;
        this.p2 = p2;
        return gameModel;
    }

    @Override
    public int nextTurn(int[] userCoord) {
        try{    
            if(board[userCoord[0]][userCoord[1]] != 1 && board[userCoord[0]][userCoord[1]] != -1){
                if(p1Turn){
                    board[userCoord[0]][userCoord[1]] = 1;
                }
                else{
                    board[userCoord[0]][userCoord[1]] = -1;
                }
            }
            else{
                return -1;
            }
        }
        catch(ArrayIndexOutOfBoundsException e){
                return -2;
        }
        return 0;
    }

    @Override
    public void resetBoard() {
        for (int[] board1 : board) {
            for (int j = 0; j < board1.length; j++) {
                board1[j] = 0;
            }
        }
    }

    @Override
    public int checkWin() {
        
        //Sums Rows
        for(int[] board1 : board){
            int rowSum = 0;
            for(int j = 0; j < board1.length; j++){
                rowSum += board1[j];
            }
            if(rowSum == 3){
                return 1;
            }
            else if(rowSum == -3){
                return -1;
            }
        }
        
        //Sums Columns
        int column0Sum = 0;
        int column1Sum = 0;
        int column2Sum = 0;
        for(int i = 0; i < board.length; i++){
            column0Sum += board[i][0];
            column1Sum += board[i][1];
            column2Sum += board[i][2];
        }
        if(column0Sum == 3|| column1Sum == 3|| column2Sum == 3){
            return 1;
        }
        else if(column0Sum == -3|| column1Sum == -3|| column2Sum == -3){
            return -1;
        }
        
        //Sums Diagonal
        if(board[0][0] + board[1][1] + board[2][2] == 3 || board[0][2] + board[1][1] + board[2][0] == 3){
            return 1;
        }
        else if(board[0][0] + board[1][1] + board[2][2] == -3 || board[0][2] + board[1][1] + board[2][0] == -3){
            return -1;
        }
        return 0;
    }

    @Override
    public boolean checkTie() {
        for (int[] board1 : board) {
            for (int j = 0; j < board1.length; j++) {
                if(board1[j] == 0){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static GameModel getInstance(){
        if(gameModel == null){
            gameModel = new GameModel();
        }
        return gameModel;
    }
    
    public boolean isNameUnique(String name){
        for(PlayerModel i:this.userArray){
            if(i.getName().equals(name)){
                return false;
            }
        }
        return true;
    } 
    
    
//    For testing
//    public void printBoard(){
//        for (int[] board1 : board) {
//            for (int j = 0; j < board1.length; j++) {
//                System.out.print(board1[j]);
//            }
//            System.out.print("\n");
//        }
//    }
    
    public void printArrayListNames(){
        System.out.println("Players:");
        for(PlayerModel i : this.userArray){ 
            System.out.println(i.getName());
        }
    }
    
    
}

