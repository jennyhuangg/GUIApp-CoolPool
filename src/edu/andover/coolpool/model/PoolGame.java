package edu.andover.coolpool.model;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import edu.andover.coolpool.GameManager;
import javafx.animation.AnimationTimer;

// Model class that runs the game play. Brings together a PoolBoard and Players
// Updates status of the game at each step of play
public class PoolGame implements Observer {
	// Create a reference to game manager here
	private GameManager gameManager = GameManager.getInstance();
	
	private PoolBoard poolBoard = new PoolBoard();
	private Player[] players = {new Player(), new Player()};
	private PoolGameStatus poolGameStatus = new PoolGameStatus();
	private EndScreenStatus endScreenStatus = new EndScreenStatus();
	private AnimationTimer timer;
	
	private int currPlayerInd = 0; // 0 = player 1, 1 = player 2
	private boolean sidesAreSet = false;
	private boolean streak = false;

	public PoolGame() {
		poolBoard.getCueStick().addObserver(this);
		setAnimationTimer();
	}
	
	// Defines the timer, makes timer stop and update the game status when
	// all balls are at rest
	private void setAnimationTimer(){
		timer = new AnimationTimer() {
			@Override
			public void handle(long timestamp) {
				poolBoard.update();
				if (poolBoard.stable()) { 
					this.stop();
					updateStatus(poolBoard.pocketedBalls());
					poolBoard.resetPocketedBalls();
					poolBoard.getCueStick().setCanMove(true);
					poolBoard.getCueStick().setCanReset(true);
				}
			}
		};
	}

	// Sets the ball type for each player once a player pockets a ball
	private void setSides(int ballId) {
		players[currPlayerInd].setBallType(ballId);
		players[(currPlayerInd+1)%2].setBallType((ballId + 1)%2);
		poolGameStatus.setBallColors(currPlayerInd, ballId);
		sidesAreSet = true;
	}

	// Returns true if current player pocketed other player's balls
	private boolean pocketedOther(ArrayList<Ball> pocketedBalls){
		int playerBallType = players[currPlayerInd].getBallType();
		for (Ball b: pocketedBalls){
			if (playerBallType != -1 && playerBallType != b.getId()
					&& (b.getId() == 0 || b.getId() == 1)){
				return true;
			}
		}
		return false;
	}
	
	// Returns true if current player pocketed the cue ball
	private boolean pocketedCueBall(ArrayList<Ball> pocketedBalls) {
		for (Ball b: pocketedBalls){
			if ( b.getId() == 2) return true;
		}
		return false;
	}

	// Returns true if current player pocketed the eight ball
	private boolean pocketedEightBall(ArrayList<Ball> pocketedBalls) {
		for (Ball b: pocketedBalls){
			if ( b.getId() == 3) return true;
		}
		return false;
	}

	// Switches to other player at end of turn. Does this if they do not
	// pocket a ball, if they pocket other player's balls, or if they pocket
	// cue ball.
	private void switchPlayer() {
		currPlayerInd = (currPlayerInd + 1)%2;
		streak = false;
		poolGameStatus.setTurnStatus(currPlayerInd, streak,
				players[currPlayerInd].canPocketEightBall());
	}
	
	// Allows current player to continue playing if they successfully
	// pocketed a ball
	private void continuePlayer() {
		streak = true;
		poolGameStatus.setTurnStatus(currPlayerInd, streak, 
				players[currPlayerInd].canPocketEightBall());
	}
	
	// Updates points for players based on which balls were hit in
	private void updatePoints(ArrayList<Ball> pocketedBalls){
		for (int i = 0; i < pocketedBalls.size(); i ++) {
			int ballId = pocketedBalls.get(i).getId();
			if (ballId == 0 || ballId == 1){
				if (!sidesAreSet){
					setSides(ballId);
				}
				if (players[currPlayerInd].getBallType() == ballId){
					players[currPlayerInd].addPoint();
				}
				else{
					players[(currPlayerInd+1)%2].addPoint();
				}
			}
		}
		poolGameStatus.setPoints(players[0].getPoints(), 
				players[1].getPoints());
	}

	// Updates status of game at the end of a turn. Updates points, 
	// next player to go, and notification of last turn
	public void updateStatus(ArrayList<Ball> pocketedBalls) {
		int size = pocketedBalls.size();
		// Call an illegal break if current player does not get balls to hit 
		// sides of pool board at least 3 times and gets no pocketed balls.
		if (poolBoard.getNumBumperCollisions() < 4 && size == 0) {
			poolBoard.rackBalls(poolBoard.getBalls());
			poolGameStatus.setLastTurnStatusPlayerIllegalBreak(currPlayerInd);
			switchPlayer();
		}
		// If no balls are hit in, current player turn to other player
		else if (size == 0){
			poolGameStatus.setLastTurnStatusPlayerFailed(currPlayerInd);
			switchPlayer();
		}
		// Some balls are pocketed
		else {
			// Update next player to play and status of last turn
			updatePoints(pocketedBalls);	
			// End game if pocketed eight ball
			if (pocketedEightBall(pocketedBalls)) {
		    	// If eight ball is pocketed correctly.
		    	if (players[currPlayerInd].canPocketEightBall()) {
					endScreenStatus.setGameOverStatusSuccess(currPlayerInd);
					gameManager.initEndScreen();
				}
		    	// If eight ball is pocketed prematurely. 
				else {
					endScreenStatus.setGameOverStatusFail(currPlayerInd);
					gameManager.initEndScreen();
				}
			}
			else if (pocketedCueBall(pocketedBalls)) {
				// Handle scratch if pocketed cue ball
				poolGameStatus.setLastTurnStatusPocketedCueBall(currPlayerInd);
				poolBoard.resetCueBall();
				switchPlayer();
			}		
			else if (pocketedOther(pocketedBalls)) {
				// Lose turn if pocketed other player's ball
				poolGameStatus.setLastTurnStatusPocketedOther(currPlayerInd);
				switchPlayer();
			}
			else{
				// Continue turn if pocketed own ball
				poolGameStatus.setLastTurnStatusPlayerSucceeded(currPlayerInd);
				continuePlayer();
			}
		}
	}

	// Starts the animation when cue stick sets the cue ball velocity
	public void update(Observable o, Object arg) {
		if (o == poolBoard.getCueStick()){
			if (poolBoard.getCueStick().hasHit()){
				timer.start();
			}
		}
	}
	
	public PoolBoard getPoolBoard() { return poolBoard; }
	public Player[] getPlayers() { return players; }
	public PoolGameStatus getPoolGameStatus(){ return poolGameStatus; }
	public EndScreenStatus getEndScreenStatus() { return endScreenStatus; }
}