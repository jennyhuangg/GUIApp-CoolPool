package edu.andover.coolpool.model;

import java.util.Observable;
import edu.andover.coolpool.GameConstants;

// Model class for a pool ball. Can represent a red ball, blue ball,
// cue ball, or 8 ball depending on the ID passed into the constructor. The
// ID of the ball does not matter until we implement players.

public class Ball extends Observable {
	private boolean isPocketed;
	private double centerX;
	private double centerY;
	private double xVelocity; // in inches/second.
	private double yVelocity; // in inches/second.
	private final double radius = GameConstants.BALL_RADIUS;
	private int id;
	
	// ID = 0: Red 
	// ID = 1: Blue
	// ID = 2: Cue Ball
	// ID = 3: Eight Ball
	public Ball(int id) {
		centerX = 0;
		centerY = 0;
		isPocketed = false;
		xVelocity = 0;
		yVelocity = 0;
		this.id = id;
	}

	public Ball(double centerX, double centerY, int id) {
		this.centerX = centerX;
		this.centerY = centerY;
		isPocketed = false;
		xVelocity = 0;
		yVelocity = 0;
		this.id = id;
	}

	// Unpockets a ball, used only for cueball.
	public void unpocket(){
		isPocketed = !isPocketed;
	}

	public void setCenterX(double centerX) {
		this.centerX = centerX;
		setChanged();
		notifyObservers();
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
		setChanged();
		notifyObservers();
	}

	public void setCenter(double centerX, double centerY) {
		this.centerX = centerX;
		this.centerY = centerY;
		setChanged();
		notifyObservers();
	}
	
	public void setXVelocity(double xVel) { 
		xVelocity = xVel;
		setChanged();
		notifyObservers();
	}
	
	public void setYVelocity(double yVel) { 
		yVelocity = yVel;
		setChanged();
		notifyObservers();
	}
	
	// Pockets a ball.
	public void setPocketed() {
		isPocketed = !isPocketed;
		xVelocity = 0;
		yVelocity = 0;
		centerX = 0;
		centerY = 0;
		setChanged();
		notifyObservers();
	}
	
	public int getId(){ return id;}
	public boolean isPocketed() { return isPocketed; }
	public double getXVelocity(){ return xVelocity; }
	public double getYVelocity(){ return yVelocity; }
	public double getRadius(){ return radius; }
	public double getCenterX(){ return centerX; }
	public double getCenterY() { return centerY; }
}
