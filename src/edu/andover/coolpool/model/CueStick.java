package edu.andover.coolpool.model;

import edu.andover.coolpool.view.CueStickView;
import javafx.scene.shape.Shape;

public class CueStick {
	
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	
	private static final double distanceTipFromCueBall = 3.0;
	private static final double cueStickLength = 37.0;
	private static final double distanceEndFromCueBall = distanceTipFromCueBall
			+ cueStickLength;
	
	private Ball cueBall;
	
	private CueStickView cueStickView;

	public CueStick(Ball cueBall) {
		double distanceTipFromCueBall = 3.0;
		this.cueBall = cueBall;
		startX = cueBall.getCenterX() - distanceTipFromCueBall;
		startY = cueBall.getCenterY();
		endX = startX - cueStickLength;
		endY = startY;
		cueStickView = new CueStickView(startX, startY, endX, endY);
	}
	
	public Shape getView(){ return cueStickView.getLine(); }
	
	public Ball getCueBall() { return cueBall; }
	public double getStartX() { return startX; }
	public double getStartY() { return startY; }
	public double getEndX() { return endX; }
	public double getEndY() { return endY; }

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
	
	public void setHoverCueStickLocation(double mouseX, double mouseY) {
		setNewCueStickLocation(distanceTipFromCueBall, mouseX, mouseY);
	}
	
	public void setCueStickLocationOnDrag(double initMouseX, double initMouseY,
			double endMouseX, double endMouseY) {
		
		//vector projection
		double distanceInitToEnd = (1 / cueStickLength)*(endMouseX - 
				this.getStartX())*(this.getEndX() - this.getStartX()) + 
				(endMouseY - this.getEndY())*(this.getEndY() - this.getEndY());

		double newDistanceTipFromCueBall = distanceTipFromCueBall + distanceInitToEnd;
		
		setNewCueStickLocation( newDistanceTipFromCueBall, initMouseX, initMouseY);
		
	}
	
	public void setCueStickLocationAfterHit(double mouseX, double mouseY) {
		double newDistanceTipFromCueBall = 1;
		setNewCueStickLocation(newDistanceTipFromCueBall, mouseX, mouseY);
	}
	
	public void setNewCueStickLocation(double distanceTipFromCueBall, double x, double y) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
		
		double distanceBalltoMouse = getDistance(cueBallX, cueBallY, x, y);
		
		double distanceEndFromCueBall = distanceTipFromCueBall + cueStickLength;
		
		startX = (distanceTipFromCueBall / distanceBalltoMouse) * (x -
				cueBallX) + cueBallX;
		startY = (distanceTipFromCueBall / distanceBalltoMouse) * (y -
				cueBallY) + cueBallY;
		endX = (distanceEndFromCueBall / distanceBalltoMouse) * (x -
				cueBallX) + cueBallX;
		endY = (distanceEndFromCueBall / distanceBalltoMouse) * (y -
				cueBallY) + cueBallY;
		
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);
	}
	public void updateCueBallVelocity(double initMouseDragX, double endMouseDragX, 
			double initMouseDragY, double endMouseDragY) {
		double amplifier = 2.5;
		double xVel = amplifier*(initMouseDragX - endMouseDragX);
		double yVel = amplifier*(initMouseDragY - endMouseDragY);
		
		cueBall.setXVelocity(xVel);
		cueBall.setYVelocity(yVel);
	}

	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public double getAngle (double x1, double y1, double x2, double y2) {
	    double xdiff = x1 - x2;
	    double ydiff = y1 - y2;
	    double tan = xdiff / ydiff;
	    double atan = Math.atan2(ydiff, xdiff);
	    return atan;
	}
	
	public static double getPerpendicularAngle (double angle) {
	    return angle + Math.PI / 2;
	}
	
	public double getSlope(double x1, double y1, double x2, double y2) {
		return (y2-y1)/(x2-x1);
	}
	
	public void setCueBallVelocity(Ball cueBall){ 
		cueBall.setXVelocity(100);
	}
}
