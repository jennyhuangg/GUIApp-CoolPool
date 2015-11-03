package edu.andover.coolpool.model;

import java.awt.Point;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.view.BallView;

public class Ball {
	private boolean isPocketed;

	private double centerX;
	private double centerY;

	private double xVelocity; //in CM/s
	private double yVelocity; //in CM/s

	private final double radius = 2.56;

	private BallView ballView;

	public Ball(int id) {

		switch (id){
		case 1:
			break;
		case 2:
			break;
		case 3: 
			break;
		case 4: 
			break;
		default:
			centerX = 0; //we need to change this later
			centerY = 0;
			break;
		}

		ballView = new BallView(centerX, centerY, radius, id);

		isPocketed = false;
	}
	
	public void slowDown(){
		xVelocity -= 1; //deceleration of 1 m/s^2

		yVelocity -= 1; //deceleration of 1 m/s^2
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;

		ballView.setCenterX(centerX);
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;

		ballView.setCenterY(centerY);
	}

	public BallView getView() { return ballView; }
	public double getCenterX(){ return centerX; }
	public double getCenterY() { return centerY; }

	public void setXVelocity(double xVel) {xVelocity = xVel;}
	public void setYVelocity(double yVel) {yVelocity = yVel;}
}
