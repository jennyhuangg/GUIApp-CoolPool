package edu.andover.coolpool.model;

import edu.andover.coolpool.view.CueStickView;
import javafx.scene.shape.Shape;

public class CueStick {
	private double xVelocity;
	private double yVelocity;
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	
	private static final double distanceTipFromCueBall = 3.0;
	private static final double cueStickLength = 37.0;
	private static final double distanceEndFromCueBall = distanceTipFromCueBall +
			cueStickLength;

	private Ball cueBall;
	
	private CueStickView cueStickView;

	public CueStick(Ball cueBall) {
		
		cueStickView = new CueStickView(startX, startY, endX, endY);
		this.cueBall = cueBall;
		startX = cueBall.getCenterX() - distanceTipFromCueBall;
		startY = cueBall.getCenterY();
		endX = startX - cueStickLength;
		endY = startY;
	}
	
	public Shape getView(){ return cueStickView.getLine(); }
	
	public double getStartX() { return startX; }
	public double getStartY() { return startY; }
	public double getEndX() { return endX; }
	public double getEndY() { return endY; }
	public double getXVelocity() { return xVelocity; }
	public double getYVelocity() { return yVelocity; }
	
	public void setStartX(double startX) {
		this.startX = startX;
		cueStickView.setStartX(startX);
	}
	
	public void setStartY(double startY) {
		this.startY = startY;
		cueStickView.setStartY(startY);
	}
	
	public void setEndX(double endX) {
		this.endX = endX;
		cueStickView.setEndX(endX);
	}
	
	public void setEndY(double endY) {
		this.endY = endY;
		cueStickView.setEndY(endY);
	}
	
	public void setXVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}
	
	public void setYVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
	
	public void setCueStickLocation(double mouseX, double mouseY) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
		//Line2D line = new Line2D.Double(cueBallX, cueBallY, mouseX, mouseY);
		double distanceBalltoMouse = getDistance(cueBallX, cueBallY, mouseX, mouseY);
		startX = (distanceTipFromCueBall / distanceBalltoMouse) * (mouseX-
				cueBallX) + cueBallX;
		startY = (distanceTipFromCueBall / distanceBalltoMouse) * (mouseY-
				cueBallY) + cueBallY;
		endX = (distanceEndFromCueBall / distanceBalltoMouse) * (mouseX-
				cueBallX) + cueBallX;
		endY = (distanceEndFromCueBall / distanceBalltoMouse) * (mouseY-
				cueBallY) + cueBallY;
	}
	
	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public double getSlope(double x1, double y1, double x2, double y2) {
		return (y2-y1)/(x2-x1);
	}
}
