package edu.andover.coolpool.model;

public class Pocket {
	private double xPosition;
	private double yPosition;

	private final double radius = 5.72;
	
	private double boardLength;
	private double boardWidth;

	public Pocket(int id, double boardLength, double boardWidth){
		setPocketLocation(id);
		this.boardLength = boardLength;
		this.boardWidth = boardWidth;
	}

	public void setPocketLocation(int id){
		switch (id){
		case 0:
			xPosition = 0;
			yPosition = 0;
			break;
		case 1:
			xPosition = boardLength/2;
			yPosition = 0;
			break;
		case 2:
			xPosition = boardLength;
			yPosition = 0;
			break;
		case 3:
			xPosition = 0;
			yPosition = boardWidth;
			break;
		case 4:
			xPosition = boardLength;
			yPosition = boardWidth;
			break;
		case 5:
			xPosition = boardLength;
			yPosition = boardWidth;
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
