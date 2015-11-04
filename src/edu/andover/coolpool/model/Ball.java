package edu.andover.coolpool.model;

import edu.andover.coolpool.view.BallView;
import javafx.scene.Node;
import javafx.scene.shape.Shape;

public class Ball {
	private boolean isPocketed;
	private double centerX;
	private double centerY;
	private double xVelocity; //in CM/s
	private double yVelocity; //in CM/s
	private final double radius = 2.56;

	private BallView ballView;

	public Ball(double centerX, double centerY, int id) {
		this.centerX = centerX;
		this.centerY = centerY;
		
		switch (id){
		default:
			
			break;
		}

		ballView = new BallView(centerX, centerY, radius, id);
		isPocketed = false;
	
		xVelocity = 100;
		yVelocity = 100;
	}

	public boolean getIsPocketed(){ return isPocketed; }
	public double getXVelocity(){ return xVelocity; }
	public double getYVelocity(){ return yVelocity; }
	public double getRadius(){ return radius; }
	public Shape getView(){ return ballView.getCircle(); }
	public double getCenterX(){ return centerX; }
	public double getCenterY() { return centerY; }

	public void setCenterX(double centerX) {
		this.centerX = centerX;
		ballView.setCenterX(this.centerX);
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
		ballView.setCenterY(this.centerY);
	}

	public void setXVelocity(double xVel) {xVelocity = xVel;}
	public void setYVelocity(double yVel) {yVelocity = yVel;}

	public void setPocketed() {
		isPocketed = !isPocketed;
		if (isPocketed) {

			ballView.remove();
			xVelocity = 0;
			yVelocity = 0;
		}
	}
}
