package edu.andover.coolpool.model;

import java.util.ArrayList;

import edu.andover.coolpool.GameManager;
import edu.andover.coolpool.controller.CueBallController;
import edu.andover.coolpool.controller.CueStickController;
import edu.andover.coolpool.view.PoolScreenView;
import javafx.animation.AnimationTimer;

public class PoolGame {
	//TODO: Add Comments

	// Create a reference to game manager here
	private GameManager gameManager;
	private PoolBoard poolBoard = new PoolBoard();
	private CueStick cueStick;
	private Player[] players = new Player[2];
	int currPlayerInd = 0;
	boolean gameHasEnded = false;
	boolean sidesAreSet = false;

	AnimationTimer timer;
	private CueStickController cueStickController;
	private CueBallController cueBallController;
	private PoolScreenView poolScreenView;
	boolean streak = false;

	public PoolGame(PoolScreenView poolScreenView) {
		gameManager = GameManager.getInstance();

		setUpCueStick(); //initialize cueStick
		setUpControllers();
		setCueStickMouseHandler();

		players[0] = new Player();
		players[1] = new Player();

		timer = new AnimationTimer() {
			@Override
			public void handle(long timestamp) {
				poolBoard.update();
				if (poolBoard.stable()) { 
					this.stop();
					updatePoints(poolBoard.pocketedBalls());
					poolBoard.resetPocketedBalls();
					cueStick.setCanMove(true);
					cueStick.setCanReset(true);
				}
			}
		};

		this.poolScreenView = poolScreenView;
	}

	public void turn() {
		timer.start();
	}

	public void setSides(int ballId) {
		players[currPlayerInd].setBallType(ballId);
		players[(currPlayerInd+1)%2].setBallType((ballId + 1)%2);
		poolScreenView.setBallColorText(currPlayerInd, ballId);
		sidesAreSet = true;
	}

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

	public void switchPlayer() {
		currPlayerInd = (currPlayerInd + 1)%2;
		streak = false;
		poolScreenView.setPlayerTurnText(currPlayerInd, streak,
				players[currPlayerInd].canPocketEightBall());
	}

	public boolean pocketedCueBall(ArrayList<Ball> pocketedBalls) {
		for (Ball b: pocketedBalls){
			if ( b.getId() == 2) return true;
		}
		return false;
	}

	public boolean pocketedEightBall(ArrayList<Ball> pocketedBalls) {
		for (Ball b: pocketedBalls){
			if ( b.getId() == 3) return true;
		}
		return false;
	}

	public void continuePlayer() {
		streak = true;
		poolScreenView.setPlayerTurnText(currPlayerInd, streak, 
				players[currPlayerInd].canPocketEightBall());
	}

	public void updatePoints(ArrayList<Ball> pocketedBalls) {
		int size = pocketedBalls.size();
		if (size == 0){
			poolScreenView.setStatusPlayerFailed(currPlayerInd);
			switchPlayer();
		}
		else{
			for (int i = 0; i < size; i ++) {
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

			poolScreenView.setPointsText(players[0].getPoints(), 
					players[1].getPoints());

			if (pocketedEightBall(pocketedBalls)) {
				gameHasEnded = true;
				gameManager.initEndScreen();
			}

			else if (pocketedCueBall(pocketedBalls)) {
				poolScreenView.setStatusPocketedCueBall(currPlayerInd);
				poolBoard.resetCueBall();
				setCueBallMouseHandler();
				cueStick.setCueBall(poolBoard.getBalls()[15]);
				switchPlayer();
			}		
			else if (pocketedOther(pocketedBalls)) {
				poolScreenView.setStatusPocketedOther(currPlayerInd);
				switchPlayer();
			}
			else{
				poolScreenView.setStatusPlayerSucceeded(currPlayerInd);
				continuePlayer();
			}
		}
	}

	public PoolBoard getPoolBoard() {
		return poolBoard;
	}

	private void setCueStickMouseHandler() {
		cueStickController.addMouseHoverEH(poolBoard.getView(), cueStick);
		cueStickController.addMousePressedEH(poolBoard.getView(), cueStick);
		cueStickController.addMouseReleasedEH(poolBoard.getView(), cueStick);
		cueStickController.addMouseDraggedEH(poolBoard.getView(), cueStick);
	}
	
	private void setCueBallMouseHandler() {
		cueBallController.addMouseHoverEventHandler(poolBoard.getView(), 
				poolBoard.getBalls()[15]);
	}
	
	private void setUpCueStick() {
		cueStick = new CueStick(poolBoard.getBalls()[15], this);
		poolBoard.getView().getPane().getChildren().add(cueStick.getView());
	}
	
	private void setUpControllers() {
		cueStickController = new CueStickController();
		cueBallController = new CueBallController();
	}

	public Player[] getPlayers() { return players; }
}