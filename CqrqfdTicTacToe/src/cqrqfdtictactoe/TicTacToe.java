package cqrqfdtictactoe;
/**
 * @author Carson Rottinghaus
 */
public interface TicTacToe {
    public int nextTurn(int[] userCoord);
    
    public void resetBoard();
    
    public int checkWin();
    
    public boolean checkTie();
    
}
