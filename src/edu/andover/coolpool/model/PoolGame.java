package edu.andover.coolpool.model;

import java.util.ArrayList;

import edu.andover.coolpool.controller.CueStickController;
import edu.andover.coolpool.controller.PoolScreenController;
import javafx.animation.AnimationTimer;

public class PoolGame {
	PoolBoard poolBoard;
	CueStick cueStick;
	Player[] players = new Player[2];
	int currPlayerInd = 0;
	boolean gameHasEnded = false;
	boolean sidesAreSet = false;
	
	AnimationTimer timer;
	private CueStickController cueStickController;
	private PoolScreenController poolScreenController;


	public PoolGame(PoolScreenController poolScreenController){
		poolBoard = new PoolBoard();
		setUpCueStick();
		
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
					currPlayerInd = (currPlayerInd + 1)%2;
					poolScreenController.setPlayerTurnText(currPlayerInd);
				}
			}
		};
		
		this.poolScreenController = poolScreenController;
	}

	public void turn(){
		timer.start();
	}
	
	public void setSides(int ballId){
		players[currPlayerInd].setBallType(ballId);
		players[(currPlayerInd+1)%2].setBallType((ballId + 1)%2);
		poolScreenController.setBallColorText(currPlayerInd, ballId);
		sidesAreSet = true;
	}

	public void updatePoints(ArrayList<Ball> pocketedBalls){
		int size = pocketedBalls.size();
		if (size > 0){
			for (int i = 0; i < size; i ++){
				int ballId = pocketedBalls.get(i).getId();
				if (ballId == 0 || ballId == 1){
					if (!sidesAreSet){
						setSides(ballId);
					}
					players[ballId].addPoint();
				}

				if (ballId == 2) { 
					poolBoard.resetCueBall(); 
				}

				if (ballId == 3) {
					gameHasEnded = true;
				}
			}
		}
		
		poolScreenController.setPointsText(players[0].getPoints(), 
				players[1].getPoints());
	}

	public PoolBoard getPoolBoard(){
		return poolBoard;
	}
	
	private void setUpCueStick() {
		cueStickController = new CueStickController();
		cueStick = new CueStick(poolBoard.getBalls()[15], this);
		cueStickController.addMouseHoverEventHandler(poolBoard.getView(), cueStick);
		cueStickController.addMousePressedEventHandler(poolBoard.getView(), cueStick);
		cueStickController.addMouseReleasedEventHandler(poolBoard.getView(), cueStick);
		cueStickController.addMouseDraggedEventHandler(poolBoard.getView(), cueStick);
		poolBoard.getView().getPane().getChildren().add(cueStick.getView());
	}
	
	public Player[] getPlayers(){ return players; }
}