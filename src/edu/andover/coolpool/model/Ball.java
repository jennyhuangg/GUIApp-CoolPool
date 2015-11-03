package edu.andover.coolpool.model;

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
		default:
			centerX = 0; //we need to change this later
			centerY = 0;
			break;
		}

		ballView = new BallView(centerX, centerY, radius, id);
		isPocketed = false;
	}

	public boolean getIsPocketed(){ return isPocketed; }
	public double getXVelocity(){ return xVelocity; }
	public double getYVelocity(){ return yVelocity; }
	public double getRadius(){ return radius; }
	public BallView getView(){ return ballView; }
	public double getCenterX(){ return centerX; }
	public double getCenterY() { return centerY; }

	public void setCenterX(double centerX) {
		this.centerX = centerX;
		ballView.setCenterX(centerX);
	}

	public void setCenterY(double centerY) {
		this.centerY = centerY;
		ballView.setCenterY(centerY);
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
