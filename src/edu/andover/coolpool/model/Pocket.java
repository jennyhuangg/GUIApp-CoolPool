package edu.andover.coolpool.model;

//This class is not used in Version 0, but will be implemented in the final.
public class Pocket {
	private double xPosition;
	private double yPosition;

	private final double radius = 5;
	
	private double maxX;
	private double maxY;
	private double minX;
	private double minY;

	public Pocket(int id, double boardLength, double boardWidth, double boardX, 
			double boardY){
		setPocketLocation(id);
		minX = boardX;
		minY = boardY;
		maxX = boardX + boardLength;
		maxY = boardY + boardLength;
	}

	public void setPocketLocation(int id){
		switch (id){
		case 0:
			xPosition = minX;
			yPosition = minY;
			break;
		case 1:
			xPosition = (minX + maxX)/2;
			yPosition = minY;
			break;
		case 2:
			xPosition = maxX;
			yPosition = minY;
			break;
		case 3:
			xPosition = 0;
			yPosition = maxY;
			break;
		case 4:
			xPosition = (minX + maxX)/2;
			yPosition = maxY;
			break;
		case 5:
			xPosition = maxX;
			yPosition = maxY;
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
