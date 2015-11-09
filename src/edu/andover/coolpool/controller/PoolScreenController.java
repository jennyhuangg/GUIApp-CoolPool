package edu.andover.coolpool.controller;

import edu.andover.coolpool.model.Player;
import edu.andover.coolpool.model.PoolGame;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PoolScreenController {

    @FXML
    private Text player1Points;

    @FXML
    private Text player2Points;
    
    @FXML
    private Text player1BallColorText;
    
    @FXML
    private Text player2BallColorText;
    
    @FXML
    private Text playerTurnText;
    
    @FXML
    private Text statusText;
    
    @FXML
    public void setPointsText(int points1, int points2){
    	player1Points.setText(points1 + "");
    	player2Points.setText(points2 + "");
    }
    
    @FXML
    public void setBallColorText(int currPlayerInd, int player1ID){
    	//0 = red
    	//1 = blue
    	if ((player1ID + currPlayerInd)%2 == 0 ){
    		player1BallColorText.setText("Ball color: RED");
    		player2BallColorText.setText("Ball color: BLUE");
    	}
    	else{
    		player1BallColorText.setText("Ball color: BLUE");
    		player2BallColorText.setText("Ball color: RED");
    	}
    }
			

}
