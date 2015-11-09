package edu.andover.coolpool.model;

import java.util.ArrayList;

import edu.andover.coolpool.controller.CueStickController;
import javafx.animation.AnimationTimer;

public class PoolGame {
	PoolBoard poolBoard;
	CueStick cueStick;
	Player[] players = new Player[2];
	int currPlayerInd = 0;
	boolean gameHasEnded = false;
	
	AnimationTimer timer;
	private CueStickController cueStickController;


	public PoolGame(){
		poolBoard = new PoolBoard();
		setUpCueStick();
		
		players[0] = new Player();
		players[1] = new Player();
		
		timer = new AnimationTimer() {
			@Override
			public void handle(long timestamp) {
				poolBoard.update(this);
			}
		};
	}

	public void run(){
		timer.start();

	}

	public void updatePoints(ArrayList<Ball> pocketedBalls){
		int size = pocketedBalls.size();
		if (size > 0){
			for (int i = 0; i < size; i ++){
				int ballId = pocketedBalls.get(i).getId();
				if (ballId == 0 || ballId == 1){
					if (players[currPlayerInd].getBallType() == -1){
						players[currPlayerInd].setBallType(ballId);
						players[(currPlayerInd+1)%2].setBallType((ballId + 1)%2);
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
}