package edu.andover.coolpool.model;

import java.util.Observable;

// Model class that stores the status of the pool game after each turn.
public class PoolGameStatus extends Observable {
	
	// Stores number of points for each player.
	private String player1PointsStatus = "0";
	private String player2PointsStatus = "0";
	
	// Stores the ball type for each player.
    private String player1BallColorStatus = "N/A";
    private String player2BallColorStatus = "N/A";
    
    // Stores which player's turn it is.
    private String turnStatus;
    
    //Stores message from previous turn.
    private String lastTurnStatus;
    
    // Stores message about cueball scratching.
    private String cueBallStatus;
    
    public PoolGameStatus() {
    	turnStatus = "Player 1, your turn!";
    	lastTurnStatus = "Click and drag the cue stick to take a shot"
    			+ " at the cue ball.";
    }
    
    public void setPoints(int player1Points, int player2Points) {
    	this.player1PointsStatus = player1Points + "";
    	this.player2PointsStatus = player2Points + "";
    	
    	setChanged();
    	notifyObservers();
    }
    
    public void setBallColors(int currPlayerInd, int player1ID) {
    	// 0 = red
    	// 1 = blue
    	if ((player1ID + currPlayerInd)%2 == 0 ) {
    		player1BallColorStatus = "Ball color: RED";
    		player2BallColorStatus = "Ball color: BLUE";
    	}
    	else{
    		player1BallColorStatus = "Ball color: BLUE";
    		player2BallColorStatus = "Ball color: RED";
    	}
    	
    	setChanged();
    	notifyObservers();
    }
    
    // Sets the turn status for which player gets next turn.
    public void setTurnStatus(int currPlayerInd, boolean streak, 
    		boolean canPocketEightBall) {
    	
    	int playerNum = currPlayerInd + 1;
   
    	// Additional streak text if player is on a streak.
    	String streakText = "";
    	if (streak) {
    		streakText = " again";
    	}
    	
    	// Additional eight ball prompt if player can pocket eight ball.
    	String eightBallPrompt = "";
    	if (canPocketEightBall) eightBallPrompt = " You may now pocket the "
    			+ "eight ball to win.";
    	
    	turnStatus = "Player " + playerNum + ", "
    			+ "your turn" + streakText + "!" + eightBallPrompt;
    	
    	setChanged();
    	notifyObservers();
    }
    
    // Sets last turn status if player did not pocket a ball.
    public void setLastTurnStatusPlayerFailed(int currPlayerInd) {
    	int playerNum = currPlayerInd + 1;
    	lastTurnStatus = "Player " + playerNum + " did not "
    			+ "pocket a ball.";
    	setChanged();
    	notifyObservers();
    }
    
    
    // Sets last turn status if player made an illegal break.
    public void setLastTurnStatusPlayerIllegalBreak(int currPlayerInd) {
    	int playerNum = currPlayerInd + 1;
    	lastTurnStatus = "Player " + playerNum + " did not "
    			+ "get four bumper collisions "
    			+ "- Illegal break.";
    	
    	setChanged();
    	notifyObservers();
    }
    
    // Sets last turn status if player pocketed a ball and did not
    // pocket other player's balls.
    public void setLastTurnStatusPlayerSucceeded(int currPlayerInd) {
    	int playerNum = currPlayerInd + 1;
    	lastTurnStatus = "Player " + playerNum + " has pocketed "
    			+ "a ball.";
    	
    	setChanged();
    	notifyObservers();
    }
    
    // Sets last turn status if player pocketed the cue ball.
    public void setLastTurnStatusPocketedCueBall(int currPlayerInd) {
    	int playerNum = currPlayerInd + 1;
    	lastTurnStatus = "Player " + playerNum + " pocketed "
    			+ "the cue ball. Scratch!";
    	setChanged();
    	notifyObservers();
    }
    
    // Sets last turn status if player pocketed other player's balls.
    public void setLastTurnStatusPocketedOther(int currPlayerInd) {
    	int playerNum = currPlayerInd + 1;
    	int otherPlayerNum = (currPlayerInd+1)%2 + 1;
    	lastTurnStatus = "Player " + playerNum + " pocketed "
    			+ "Player " + otherPlayerNum + "'s ball. Lost turn!";
    	setChanged();
    	notifyObservers();
    }
    
    // Sets cue ball status to instruct player to place ball on scratch.
    public void setCueBallStatusForScratch() {
    	cueBallStatus = "Place the cue ball inside the orange region.";
    	setChanged();
    	notifyObservers();
    }
    
    // Unsets cue ball status to empty string
    public void unsetCueBallStatus() {
    	cueBallStatus = "";
    	setChanged();
    	notifyObservers();
    }
    
    public String getPlayer1PointsStatus() { return player1PointsStatus; }
    public String getPlayer2PointsStatus() { return player2PointsStatus; }
    public String getPlayer1BallColorStatus() { return player1BallColorStatus; }
    public String getPlayer2BallColorStatus() { return player2BallColorStatus; }
    public String getTurnStatus() { return turnStatus; }
    public String getLastTurnStatus() { return lastTurnStatus; }
    public String getCueBallStatus() { return cueBallStatus; }
}
