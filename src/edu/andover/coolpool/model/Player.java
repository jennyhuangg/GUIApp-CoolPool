package edu.andover.coolpool.model;

public class Player {
	int ballType; //0 if solid, 1 if stripe, -1 otherwise
	int points = 0;


	public Player() {
	    ballType = -1;
	}
	
	public int getBallType(){ 
		return ballType; 
	}

	public boolean canPocketEightBall() { 
		return (points == 7);
	}

	public void setBallType(int ballType) { 
		this.ballType = ballType; 
	}
	
	public void addPoint() { points += 1; }
	
	public int getPoints() { return points; }
	
}

