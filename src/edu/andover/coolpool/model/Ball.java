package edu.andover.coolpool.model;

public class Ball {
	private boolean isPocketed;

	private double xVelocity;

	private double yVelocity;

	private final double radius = 2.86;

	private final double cmToPixel = 37.795;


	private BallSprite view;


	public Ball(int id) {

		view = new BallSprite(id);

		isPocketed = false;

	}


	public double getCenterX() {return view.getCenterX()/cmToPixel; }

	public double getCenterY() {return view.getCenterY()/cmToPixel; }

	public double setCenterX(double centerX) {view.setCenterX(centerX*cmToPixel); }

	public double setCenterY(double centerY) {view.setCenterY(centerY*cmToPixel); }

	public void setXVelocity(double xVel) {xVelocity = xVel};

	public void setYVelocity(double yVel) {yVelocity = yVel};


	public void slowDown(){

		xVelocity *= 99/100;

		yVelocity *= 99/100;

	}

}
