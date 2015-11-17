package edu.andover.coolpool.model;

import java.util.Observable;

public class EndScreenStatus extends Observable{

	private String gameOverMessage;
	
	public void setGameOverStatusSuccess(int currPlayerInd) {
    	int playerNum = currPlayerInd + 1;
		gameOverMessage = "Player " + playerNum + " successfully"
				+ "pocketed the eight ball. Congratulations,"
				+ "you win!";
    	setChanged();
    	notifyObservers(); 
	}
	
	public void setGameOverStatusFail(int currPlayerInd) {
    	int playerNum = currPlayerInd + 1;
    	int otherPlayerNum = ((playerNum%2)+1);
		gameOverMessage = "Oops! Player " + playerNum + 
				" prematurely pocketed the eight ball. "
				+ "Congratulations, Player " + otherPlayerNum +", you win!";
    	setChanged();
    	notifyObservers();
	}
	
	public String getGameOverMessage() { return gameOverMessage; }
}
