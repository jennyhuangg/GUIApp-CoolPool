package edu.andover.coolpool.model;

public class Pocket {
	private double xPosition;
	private double yPosition;

	private final double radius = 5.72;
	
	private PoolBoard poolBoard;

	public Pocket(int id, PoolBoard poolBoard){
		setPocketLocation(id);
		this.poolBoard = poolBoard;	
	}

	public void setPocketLocation(int id){
		switch (id){
		case 0:
			xPosition = 0;
			yPosition = 0;
			break;
		case 1:
			xPosition = poolBoard.getLength()/2;
			yPosition = 0;
			break;
		case 2:
			xPosition = poolBoard.getLength();
			yPosition = 0;
			break;
		case 3:
			xPosition = 0;
			yPosition = poolBoard.getWidth();
			break;
		case 4:
			xPosition = poolBoard.getLength()/2;
			yPosition = poolBoard.getWidth();
			break;
		case 5:
			xPosition = poolBoard.getLength();
			yPosition = poolBoard.getWidth();
			break;
		default:
			xPosition = 0;
			yPosition = 0;
			break;
		}
	}
	
	public double getRadius(){ return radius; }

	public double getXPosition(){ return xPosition; }

	public double getYPosition(){ return yPosition; }
}
