package edu.andover.coolpool.model;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.view.PocketView;
import javafx.scene.shape.Shape;

//This class is not used in Version 0, but will be implemented in the final.
public class Pocket {
	private double xPosition;
	private double yPosition;

	private double maxX;
	private double maxY;
	private double minX;
	private double minY;

	private final double radius = 2.25; //inches

	public Pocket(int id, double boardX, double boardY){
		minX = boardX;
		minY = boardY;
		maxX = minX + GameConstants.POOL_TABLE_LENGTH;
		maxY = minY +  GameConstants.POOL_TABLE_WIDTH;
		setPocketLocation(id); //set xPosition and yPosition
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
			xPosition = minX;
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
