package edu.andover.coolpool.model;

import edu.andover.coolpool.view.BallSprite;

public class Ball {
	private boolean isPocketed;

	private double xVelocity; //in CM/s
	private double yVelocity; //in CM/s

	private Point center; //stores Point(centerX, centerY);

	private final double radius = 2.86; //in centimeters

	public Ball(int x) {
	    isPocketed = false;
	}


	public double getCenterX() { return center.x; }
	public double getCenterY() { return center.y; }

	public void setCenterX(double centerX) { center.x = centerX; }
	public void setCenterY(double centerY) { center.y = centerY; }
	public void setXVelocity(double xVel) { xVelocity = xVel; }
	public void setYVelocity(double yVel) { yVelocity = yVel; }


	public void slowDown(){
	    xVelocity -= 1; //deceleration of 1 m/s^2

	    yVelocity -= 1; //deceleration of 1 m/s^2
	}


}
