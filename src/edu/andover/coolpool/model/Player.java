package edu.andover.coolpool.model;

//This class is not used in Version 0, but will be implemented in the final.
import java.util.ArrayList;

public class Player {
	int ballType; //1 if striped, 0 if solid, null otherwise

	ArrayList<Ball> pocketedBalls; 

	boolean canPocketEightBall;


	public Player() {
	    pocketedBalls = new ArrayList<Ball>();
	    ballType = -1;
	    canPocketEightBall = false;
	}

	public ArrayList<Ball> getPocketedBalls(){
		return pocketedBalls; 
	}
	
	public int getBallType(){ 
		return ballType; 
	}

	public boolean canPocketEightBall() { 
		return canPocketEightBall; 
	}

	public void setBallType(int ballType) { 
		this.ballType = ballType; 
	}

	public void addToPocketedBalls(Ball ball) { 
		pocketedBalls.add(ball); 
	}

	public void setCanPocketEightBall(){ 
		canPocketEightBall = true; 
	}
}
