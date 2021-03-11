
package cqrqfdtictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.util.Random;

/**
 * @author Carson Rottinghaus
 */


public class GameController implements Initializable {
    
     @FXML
     private Button btn1;

     @FXML
     private Button btn2;

     @FXML
     private Button btn3;
 
     @FXML
     private Button btn4;

     @FXML
     private Button btn5;

     @FXML
     private Button btn6;

     @FXML
     private Button btn7;

     @FXML
     private Button btn8;

     @FXML
     private Button btn9;
    
     @FXML
     private Label playerTurnLabel;
     
     @FXML
     private Label errorMsg;
     
     @FXML
     private Button goToMenuBtn;
     
     @FXML
     private Button playAgainBtn;
     
     @FXML
     private Label p1wl;
     
     @FXML
     private Label p2wl;
     
     @FXML
     private Label p1name;
     
     @FXML
     private Label p2name;   
     
     int turnCount = 0;
     GameModel game = GameModel.getInstance();
     int[] userCoord = {0,0};
     int valid;
    
    @FXML
    void button1Pressed(ActionEvent event) {
        userCoord[0] = 0;
        userCoord[1] = 0;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn1);

    }
    @FXML
    void button2Pressed(ActionEvent event) {
        userCoord[0] = 0;
        userCoord[1] = 1;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn2);
    }
    
    @FXML
    void button3Pressed(ActionEvent event) {
        userCoord[0] = 0;
        userCoord[1] = 2;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn3);
    }
    
    @FXML
    void button4Pressed(ActionEvent event) {
        userCoord[0] = 1;
        userCoord[1] = 0;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn4);
    }
    @FXML
    void button5Pressed(ActionEvent event) {
        userCoord[0] = 1;
        userCoord[1] = 1;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn5);
    }
    @FXML
    void button6Pressed(ActionEvent event) {
        userCoord[0] = 1;
        userCoord[1] = 2;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn6);
    }
    @FXML
    void button7Pressed(ActionEvent event) {
        userCoord[0] = 2;
        userCoord[1] = 0;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn7);
    }
    @FXML
    void button8Pressed(ActionEvent event) {
        userCoord[0] = 2;
        userCoord[1] = 1;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn8);
    }
    @FXML
    void button9Pressed(ActionEvent event) {
        userCoord[0] = 2;
        userCoord[1] = 2;
        valid = game.nextTurn(userCoord);
        updateBoard(valid,btn9);
    }
    
    @FXML
    void goToMenu(ActionEvent event) throws Exception{
        switchToScene("MainMenuView.fxml",event);
    }
    
    @FXML
    void playAgain(ActionEvent event) throws Exception{
        switchToScene("GameView.fxml",event);
    }
    
    
    Random r= new Random();
    int[] coords = {0,0};
    private void updateBoard(int valid, Button btn) {
        turnCount +=1;
        if(turnCount == 9){
            boolean result = checkTie();
            if(result){
                return;
            }
        }
         switch (valid) {   
             case 0:
                 errorMsg.setVisible(false);
                 if(game.p1Turn){
                     btn.setText("X");
                     playerTurnLabel.setText(game.p2.getName()+"'s Turn");
                     game.p1Turn = false;
                 }
                 else{
                    btn.setText("O");
                    playerTurnLabel.setText(game.p1.getName()+"'s Turn");
                    game.p1Turn = true;
                 }
                 //game.printBoard(); //for testing
                 checkWin();
                 break;
             case -1:
                 errorMsg.setText("Error: Please select an unselected tile.");
                 errorMsg.setVisible(true);
                 break;
             case -2:
                 errorMsg.setText("Error: Index out of bounds.");
                 errorMsg.setVisible(true);
                 break;
             default:
                 break;
         }
         if(game.hasBot && game.p1Turn == false){
             coords[0] = r.nextInt(3);
             coords[1] = r.nextInt(3);
             System.out.println(coords[0]+""+coords[1]);
             //int[] coords = game.p2.getCoords();
             int isValid = game.nextTurn(coords);
             while(isValid < 0){
                 coords[0] = r.nextInt(3);
                 coords[1] = r.nextInt(3);
                 isValid = game.nextTurn(coords);
             }
             Button button = getCorrespondingButton(coords);
             updateBoard(isValid,button);
            }
         }
    
    private Button getCorrespondingButton(int[] coord){
        if(coord[0] == 0 && coord[1] == 0){
            return btn1;
        }
        if(coord[0] == 0 && coord[1] == 1){
            return btn2;
        }
        if(coord[0] == 0 && coord[1] == 2){
            return btn3;
        }
        if(coord[0] == 1 && coord[1] == 0){
            return btn4;
        }
        if(coord[0] == 1 && coord[1] == 1){
            return btn5;
        }
        if(coord[0] == 1 && coord[1] == 2){
            return btn6;
        }
        if(coord[0] == 2 && coord[1] == 0){
            return btn7;
        }
        if(coord[0] == 2 && coord[1] == 1){
            return btn8;
        }
        if(coord[0] == 2 && coord[1] == 2){
            return btn9;
        }
        return btn1;
    }
    
    private void checkWin(){
        int result = game.checkWin();
         switch (result) {
             case 1:
                 game.p1.incrememntWin();
                 game.p2.incrementLoss();
                 playerTurnLabel.setText(game.p1.getName()+" wins!");
                 endGame();
                 break;
             case -1:
                 game.p2.incrememntWin();
                 game.p1.incrementLoss();
                 playerTurnLabel.setText(game.p2.getName()+" wins!");
                 endGame();
                 break;
             default:
                 break;
         }
    }
    
    private boolean checkTie(){
        boolean tieResult = game.checkTie();
        int result = game.checkWin();
        if(tieResult && (result != -1 || result != 1)){
             game.p2.incrementTie();
             game.p1.incrementTie();
             playerTurnLabel.setText("Draw.");
             endGame();
             return true;
        }
        else{
            return false;
        }
    }
    
    private void endGame(){
        btn1.setDisable(true);
        btn2.setDisable(true);
        btn3.setDisable(true);
        btn4.setDisable(true);
        btn5.setDisable(true);
        btn6.setDisable(true);
        btn7.setDisable(true);
        btn8.setDisable(true);
        btn9.setDisable(true);
        playAgainBtn.setVisible(true);
        goToMenuBtn.setVisible(true);   
        game.resetBoard();
    }
    
    
    void switchToScene(String sceneFile, ActionEvent event) throws Exception{
         Parent root = FXMLLoader.load(getClass().getResource(sceneFile));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p1name.setText(game.p1.getName());
        p2name.setText(game.p2.getName());
        p1wl.setText(game.p1.getWin()+"-"+game.p1.getTie()+"-"+game.p1.getLoss());
        p2wl.setText(game.p2.getWin()+"-"+game.p2.getTie()+"-"+game.p2.getLoss());
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn3.setDisable(false);
        btn4.setDisable(false);
        btn5.setDisable(false);
        btn6.setDisable(false);
        btn7.setDisable(false);
        btn8.setDisable(false);
        btn9.setDisable(false);
        if(game.p1Turn){
            playerTurnLabel.setText(game.p1.getName()+"'s Turn");
        }
        else{
            playerTurnLabel.setText(game.p2.getName()+"'s Turn");
        }
    }    
    
}
