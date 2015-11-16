package edu.andover.coolpool.view;

import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PoolScreenView {
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
    
    @FXML
    public void setPlayerTurnText(int currPlayerInd, boolean streak, 
    		boolean canPocketEightBall){
    	int playerNum = currPlayerInd + 1;
    	String streakText = "";
    	if (streak) {
    		streakText = " again";
    	}
    	
    	String eightBallPrompt = "";
    	if (canPocketEightBall) eightBallPrompt = " You may now pocket the "
    			+ "eight ball to win.";
    	playerTurnText.setText("Player " + playerNum + ", "
    			+ "your turn" + streakText + "!" + eightBallPrompt);
    }
    
    @FXML
    public void setStatusPlayerFailed(int currPlayerInd){
    	int playerNum = currPlayerInd + 1;
    	statusText.setText("Player " + playerNum + " did not "
    			+ "pocket a ball.");
    }
    
    @FXML
    public void setStatusPlayerIllegalBreakNoPocketedBall(int currPlayerInd){
    	int playerNum = currPlayerInd + 1;
    	statusText.setText("Player " + playerNum + " did not "
    			+ "pocket a ball. Illegal break.");
    }
    
    @FXML
    public void setStatusPlayerIllegalBreak(int currPlayerInd){
    	int playerNum = currPlayerInd + 1;
    	statusText.setText("Player " + playerNum + " did not "
    			+ "pocket a ball or get four bumper collisions "
    			+ "- Illegal break.");
    }
    
    @FXML
    public void setStatusPlayerSucceeded(int currPlayerInd){
    	int playerNum = currPlayerInd + 1;
    	statusText.setText("Player " + playerNum + " has pocketed "
    			+ "a ball.");
    }
    
    @FXML
    public void setStatusPocketedCueBall(int currPlayerInd){
    	int playerNum = currPlayerInd + 1;
    	statusText.setText("Player " + playerNum + " pocketed "
    			+ "the cue ball. Scratch!");
    }
    
    @FXML
    public void setStatusPocketedOther(int currPlayerInd){
    	int playerNum = currPlayerInd + 1;
    	int otherPlayerNum = (currPlayerInd+1)%2 + 1;
    	statusText.setText("Player " + playerNum + " pocketed "
    			+ "Player " + otherPlayerNum + "'s ball. Lost turn!" );
    }	
}
