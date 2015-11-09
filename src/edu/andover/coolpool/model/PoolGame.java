package edu.andover.coolpool.model;

import java.util.ArrayList;

public class PoolGame {
	PoolBoard poolBoard;
	CueStick cueStick;
	Player[] players = new Player[2];
	int currPlayerInd = 0;
	boolean gameHasEnded = false;


	public PoolGame(){
		poolBoard = new PoolBoard();
		players[0] = new Player();
		players[1] = new Player();



	}

	public void run(){
		while (!gameHasEnded){
			cueStick.setCueBallVelocity(poolBoard.getBalls()[15]); //cue stick has a timer
			poolBoard.animate();
			ArrayList<Ball> pocketedBalls = poolBoard.pocketedBalls();
			updatePoints(pocketedBalls);
		}
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
}
