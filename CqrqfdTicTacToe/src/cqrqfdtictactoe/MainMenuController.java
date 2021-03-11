package cqrqfdtictactoe;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Carson Rottinghaus
 */
public class MainMenuController implements Initializable {
    GameModel game = GameModel.getInstance();
    
     @FXML
     private Button newGameBtn;
     
     @FXML
     private Button loadGameBtn;
     
     @FXML
     private Button menuBackBtn;
     
     @FXML
     private AnchorPane mainMenu;
     
     @FXML
     private AnchorPane newGamePane;
     
     @FXML
     private AnchorPane nameSelection;
     
     @FXML
     private AnchorPane loadGameSelection;
     
     @FXML
     private TextField player1Name;
     
     @FXML
     private TextField player2Name;
     
     @FXML
     private Label header;
     
     @FXML
     private Label p2Label;
     
     @FXML
     private Button saveGame;
     
     @FXML
     private Label gameSaveMsg;
     
     @FXML
     private Button playAgain;
     
     @FXML
     private Button submitLoadName;
     
     @FXML
     private Button submitName;
     
     @FXML
     private AnchorPane selectP2NamePane;
     
     @FXML
     private Label P1NameLabel;
     
     @FXML
     private TextField selectP2Name;
     
     @FXML
     private AnchorPane statsPane;
     
     @FXML
     private Label winAmt;
     
     @FXML
     private Label lossAmt;
     
     @FXML
     private Label tieAmt;
     
     @FXML
     private Label winPerc;
     
     @FXML
     private Button viewStats;
     
     @FXML
     private Button aboutBtn;
     
     @FXML
     private AnchorPane aboutMePane;
     
     
     
     @FXML
     private void submitP2Name(ActionEvent event) throws Exception{
         boolean p2Exists;
         game.userArray = loadArrayList();
         String p2Name = selectP2Name.getText();
         PlayerModel p2;
         for(PlayerModel i:game.userArray){
             if(i.getName().equals(p2Name)){
                 p2 = i;
                 game.printArrayListNames();
                 game.init(game.p1,p2);
                 switchToScene("GameView.fxml",event);
                 return;
             }
         }
         p2 = new Human(p2Name);
         game.userArray.add(p2);
         game.printArrayListNames();
         game.init(game.p1,p2);
         switchToScene("GameView.fxml",event);
     }
     
     @FXML
     private void submitNewNames(ActionEvent event) throws Exception{
         Human p1 = new Human(player1Name.getText());
         game.userArray = loadArrayList();
         if(!game.isNameUnique(p1.getName())){
             gameSaveMsg.setText("Player 1's name is taken.");
             gameSaveMsg.setVisible(true);
            return;
         }
         game.userArray.add(p1);
         if(playHuman){
             Human p2 = new Human(player2Name.getText());
             if(!game.isNameUnique(p2.getName())){
                gameSaveMsg.setText("Player 2's name is taken.");
                gameSaveMsg.setVisible(true);
                return;
                }
             game.userArray.add(p2);
             game.init(p1,p2);
             game.hasBot = false;
         }
         else{
             Bot p2 = new Bot();
             game.init(p1,p2);
             game.hasBot = true;
         }
         game.gameLoaded = true;
         game.printArrayListNames();
        switchToScene("GameView.fxml",event);          
     }
     
     @FXML
     private void submitLoadName(ActionEvent event) throws Exception{
         PlayerModel p2 = new PlayerModel();
         game.userArray = loadArrayList();
         String p1Name = player1Name.getText();
         for(PlayerModel i : game.userArray){
             System.out.println(i.getName()+"compared to "+p1Name);
             if(i.getName().equals(p1Name)){
                 game.init(i, p2);
                 game.gameLoaded = true;
                 switchToScene("MainMenuView.fxml",event);
             }
         }
         gameSaveMsg.setVisible(true);
         gameSaveMsg.setText(p1Name+" not found");
     }
     
     @FXML
     private void startNewGame(ActionEvent event){
         newGamePane.setVisible(true);
         mainMenu.setVisible(false);
         gameSaveMsg.setVisible(false);
         menuBackBtn.setVisible(true);
         aboutBtn.setVisible(false);
         header.setText("Player Select");
         
     }
     
     
     
     @FXML
     private void playAgain(ActionEvent event) throws Exception{
         if(game.gameLoaded){
             mainMenu.setVisible(false);
             selectP2NamePane.setVisible(true);
             P1NameLabel.setText(game.p1.getName());
             menuBackBtn.setVisible(true);
             aboutBtn.setVisible(false);
             
         }
     }
     @FXML
     private void loadGame(ActionEvent event){
         gameSaveMsg.setVisible(false);
         mainMenu.setVisible(false);
         nameSelection.setVisible(true);
         p2Label.setVisible(false);
         player2Name.setVisible(false);
         submitLoadName.setVisible(true);
         aboutBtn.setVisible(false);
         menuBackBtn.setVisible(true);
     }
     
     @FXML
     private void checkStats(ActionEvent event){
         double calculatePerc = game.p1.getWin() / (game.p1.getLoss() + game.p1.getTie() + game.p1.getWin());
         String formatted = String.format("%.2f", calculatePerc);
         menuBackBtn.setVisible(true);
         aboutBtn.setVisible(false);
         mainMenu.setVisible(false);
         statsPane.setVisible(true);
         winAmt.setText("Win: "+game.p1.getWin());
         lossAmt.setText("Loss: "+game.p1.getLoss());
         tieAmt.setText("Tie: "+game.p1.getTie());
         winPerc.setText("Win%: "+formatted);
         
     }
     
     @FXML
     private void saveGame(ActionEvent event){
         saveArrayList();
     }
     
     boolean playHuman;
     @FXML
     private void playHuman(ActionEvent event){
         newGamePane.setVisible(false);
         nameSelection.setVisible(true);
         submitName.setVisible(true);
         if(game.gameLoaded){
             player1Name.setVisible(false);
         }
         playHuman = true;
         playBot = false;
     }
     
     boolean playBot;
     @FXML
     private void playBot(ActionEvent event) throws Exception{
         newGamePane.setVisible(false);
         nameSelection.setVisible(true);
         submitName.setVisible(true);
         p2Label.setVisible(false);
         player2Name.setVisible(false);
         playBot = true;
         playHuman = false;
         
     }
     
     @FXML
     private void goToAbout(ActionEvent event){
         mainMenu.setVisible(false);
         menuBackBtn.setVisible(true);
         aboutBtn.setVisible(false);
         aboutMePane.setVisible(true);
     }
     
     @FXML
     private void goBackToMenu(ActionEvent event) throws Exception{
         switchToScene("MainMenuView.fxml",event);
     }
         
    
    void saveArrayList(){
        try {
            FileOutputStream writeData = new FileOutputStream("userArray.ser");
            ObjectOutputStream writeStream = new ObjectOutputStream(writeData);
            game.printArrayListNames();
            writeStream.writeObject(game.userArray);
            writeStream.flush();
            writeStream.close();
            gameSaveMsg.setVisible(true);
            gameSaveMsg.setStyle("-fx-text-base-color: green;");
            gameSaveMsg.setText("Your game has been saved.");
        } catch (IOException i) {
           gameSaveMsg.setVisible(true);
           gameSaveMsg.setStyle("-fx-text-base-color: red;");
           gameSaveMsg.setText("An error has occured while trying to save.");
       }
    }
    
    private ArrayList loadArrayList(){
        ArrayList <PlayerModel> tempArray = new ArrayList <>();
            try {
             FileInputStream fileIn = new FileInputStream("userArray.ser");
             ObjectInputStream in = new ObjectInputStream(fileIn);
             tempArray = (ArrayList <PlayerModel>) in.readObject();
             in.close();
             fileIn.close();
             System.out.println("arrayList has been loaded.");
             } 
            catch (IOException i) {
             System.out.println("arrayList not found");
                return tempArray;
            } 
            catch (ClassNotFoundException c) {
                return tempArray;
            }
        return tempArray;
    }
    
    void switchToScene(String sceneFile, ActionEvent event) throws Exception{
         Parent root = FXMLLoader.load(getClass().getResource(sceneFile));
         Scene scene = new Scene(root);
         Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
         stage.setScene(scene);
         stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //game.userArray = loadArrayList();
        game.hasBot = false;
        game.printArrayListNames();
            if(game.gameLoaded){
                playAgain.setDisable(false);
                saveGame.setDisable(false);
                viewStats.setDisable(false);
                gameSaveMsg.setText("Signed in as: "+game.p1.getName());
                gameSaveMsg.setVisible(true);
            }
        }    
    }
