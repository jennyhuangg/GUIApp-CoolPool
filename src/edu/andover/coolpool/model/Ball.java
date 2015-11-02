package edu.andover.coolpool.model;

import java.awt.Point;

import edu.andover.coolpool.view.BallSprite;

public class Ball {
	private boolean isPocketed;

	private double xVelocity; //in CM/s
	private double yVelocity; //in CM/s

	private double centerX;
	private double centerY;

	private final double radius = 2.86; //in centimeters

	public Ball(int x) {
	    isPocketed = false;
	}


	public double getCenterX() { return centerX; }
	public double getCenterY() { return centerY; }

	public void setCenterX(double centerX) { this.centerX = centerX; }
	public void setCenterY(double centerY) { this.centerY = centerY; }
	public void setXVelocity(double xVel) { xVelocity = xVel; }
	public void setYVelocity(double yVel) { yVelocity = yVel; }


	public void slowDown(){
	    xVelocity -= 1; //deceleration of 1 m/s^2

	    yVelocity -= 1; //deceleration of 1 m/s^2
	}


}
