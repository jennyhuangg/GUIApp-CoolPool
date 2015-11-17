package edu.andover.coolpool.model;

// Model class for a player. Stores the player's ball type and points
public class Player {
	int ballType; //0 if solid, 1 if stripe, -1 otherwise
	int points = 0;

	public Player() {
	    ballType = -1;
	}

	public boolean canPocketEightBall() { 
		return (points == 7);
	}
	
	public void setBallType(int ballType) { 
		this.ballType = ballType; 
	}
	
	public void addPoint() { 
		points += 1; 
	}
	
	public int getBallType(){ return ballType; }
	public int getPoints() { return points; }
	
}

