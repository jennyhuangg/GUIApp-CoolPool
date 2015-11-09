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
    public void setPointsText(int points1, int points2){
    	player1Points.setText(points1 + "");
    	player2Points.setText(points2 + "");
    }
			

}
