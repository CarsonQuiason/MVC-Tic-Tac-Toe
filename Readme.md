
# UI Development: Project was created utilizing JavaFX.
1. MainMenuView.fxml
2. GameView.fxml
# Architecture:
1.	Model – PlayerModel.java, GameModel.java
2.	Views – MainMenuView.fxml, GameView.fxml
3.	Controllers – GameController.java, MainMenuController.java
# Elements
1. Object Oriented Elements  
	a. Classes  
  i.	GameModel.java [Singleton class]  
		ii.	PlayerModel.java  
		iii.	GameController.java  
		iv.	MainMenuController.java  
	b. Subclasses  
		i.	Human, subclass to PlayerModel.java  
		ii.	Bot, subclass to PlayerModel.java  
	c. Abstract Class  
		i.	userList.java, GameModel.java extends the abstract class  
	d. Interface  
		i. TicTacToe.java, GameModel.java implements the inferface  
		
2. Code elements utilized  
	a.	Collection Classes  
		i.	In GameModel.java I utilize an ArrayList <PlayerModel> which is manipulated in MainMenuController.java 
			(Usages can be found in on lines 118, 131, 132, etc.) functions loadArrayList(), printArrayListNames() (used for testing) all manipulate the ArrayList.  
	b.	Exception Handling  
		i.	Exception handling is used to check whether a file exists when loading and saving data.  This can be   
			found in MainMenuController.java on lines 292, 310, and 314.  
			
3. Clearly Defined Model  
	a.	Model Classes Used  
			i.	PlayerModel.java  
			ii.	GameModel.java  
			
3.	UI Utilizing multiple scenes  
	a.	Starting a tic-tac-toe game will switch the scene from MainMenuView.fxml to GameView.fxml. Found on line 165, 134, 126 in MainMenuController.java.  
		
4.	About Information  
	a.	About tab can be found by pressing the About button in the Menu.  
		
5.	Save and Load Data  
	a.	Saving and Loading data is utilized to maintain a player list. Saving can be found in MainMenuController.java. When the save button is pressed a function named saveArrayList()   
		(found on line 214) is called. Loading can also be found in MainMenuController.java. Loading is also fired by a button and calls loadArrayList() (fouind on line 241).  
		
