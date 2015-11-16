package edu.andover.coolpool.model;


import edu.andover.coolpool.GameConstants;
import java.util.Observable;
import edu.andover.coolpool.view.BallView;
import javafx.scene.shape.Shape;

// Model class for a pool ball. Can represent a "solid" ball, "striped" ball,
// cue ball, or 8 ball depending on the ID passed into the constructor. The
// ID of the ball does not matter until we implement players.

public class Ball extends Observable{
	public boolean isPocketed;
	private double centerX;
	private double centerY;
	private double xVelocity; // in inches/sec
	private double yVelocity; // in inches/sec
	private final double radius = GameConstants.BALL_RADIUS;
	private int id;

	public Ball(double centerX, double centerY, int id) {
		this.centerX = centerX;
		this.centerY = centerY;
		isPocketed = false;
	
		xVelocity = 0;
		yVelocity = 0;
		this.id = id;
	}

	public boolean getIsPocketed(){ return isPocketed; }
	public double getXVelocity(){ return xVelocity; }
	public double getYVelocity(){ return yVelocity; }
	public double getRadius(){ return radius; }
	public double getCenterX(){ return centerX; }
	public double getCenterY() { return centerY; }

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

	public void setXVelocity(double xVel) { xVelocity = xVel;}
	public void setYVelocity(double yVel) { yVelocity = yVel;}
	
	// pockets or unpockets a ball
	// TODO: allow only cue ball to be unpocketed
	public void setPocketed() {
		isPocketed = !isPocketed;
		if (isPocketed) {
			xVelocity = 0;
			yVelocity = 0;
			centerX = 0;
			centerY = 0;
		}
		setChanged();
		notifyObservers();
	}
	
	public int getId(){ return id;}
}
