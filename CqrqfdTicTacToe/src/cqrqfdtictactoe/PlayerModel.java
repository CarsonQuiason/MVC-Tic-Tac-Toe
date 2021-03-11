package cqrqfdtictactoe; 
import java.io.Serializable;

/**
 *
 * @author Carson Rottinghaus
 */
class PlayerModel implements Serializable{
    int win = 0;
    int loss = 0;
    int tie = 0;
    String name;
    
    void incrememntWin(){
        this.win += 1;
    }
    
    void incrementLoss(){
        this.loss += 1;
    }
    
    void incrementTie(){
        this.tie += 1;
    }
    
    String getName(){
        return this.name;
    }
    
    int getWin(){
        return this.win;
    }
    
    int getLoss(){
        return this.loss;
    }
    
    int getTie(){
        return this.tie;
    }
}

class Human extends PlayerModel{
    
    public Human(String name) {
        this.name = name;
    }
}

class Bot extends PlayerModel{
    
    public Bot(){
        this.name = "BOT";
    }
    

    
}