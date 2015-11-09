package edu.andover.coolpool.model;

import edu.andover.coolpool.view.CueStickView;
import javafx.scene.shape.Shape;

public class CueStick {
	
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	
	private double distanceTipFromCueBall = 3.0;
	private static final double cueStickLength = 37.0;
	private double distanceEndFromCueBall = distanceTipFromCueBall +
			cueStickLength;

	private Ball cueBall;
	
	private CueStickView cueStickView;

	public CueStick(Ball cueBall) {
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
	
	public void setCueStickLocation(double mouseX, double mouseY) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
				
		double distanceBalltoMouse = getDistance(cueBallX, cueBallY, mouseX, mouseY);
		startX = (distanceTipFromCueBall / distanceBalltoMouse) * (mouseX-
				cueBallX) + cueBallX;
		startY = (distanceTipFromCueBall / distanceBalltoMouse) * (mouseY-
				cueBallY) + cueBallY;
		endX = (distanceEndFromCueBall / distanceBalltoMouse) * (mouseX-
				cueBallX) + cueBallX;
		endY = (distanceEndFromCueBall / distanceBalltoMouse) * (mouseY-
				cueBallY) + cueBallY;
		
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);
	}
	
	public void setCueStickLocationOnDrag(double initMouseX, double initMouseY,
			double endMouseX, double endMouseY) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
		
		
		//convert mouse coordinates so that they stay on the same line
		
		
		
		double distanceBalltoMouse = getDistance(cueBallX, cueBallY, endMouseX, endMouseY);
		
		double distanceInitToEnd = getDistance(initMouseX, initMouseY,
				endMouseX, endMouseY);
		
		double newDistanceTipFromCueBall = distanceTipFromCueBall + distanceInitToEnd;
		double newDistanceEndFromCueBall = newDistanceTipFromCueBall + cueStickLength;
		
		startX = (newDistanceTipFromCueBall / distanceBalltoMouse) * (endMouseX-
				cueBallX) + cueBallX;
		startY = (newDistanceTipFromCueBall / distanceBalltoMouse) * (endMouseY-
				cueBallY) + cueBallY;
		endX = (newDistanceEndFromCueBall / distanceBalltoMouse) * (endMouseX-
				cueBallX) + cueBallX;
		endY = (newDistanceEndFromCueBall / distanceBalltoMouse) * (endMouseY-
				cueBallY) + cueBallY;
		
		this.setStartX(startX);
		this.setStartY(startY);
		this.setEndX(endX);
		this.setEndY(endY);
	}
	
	public void setCueStickLocationAfterHit(double mouseX, double mouseY) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
		distanceTipFromCueBall = 1;
		double distanceBalltoMouse = getDistance(cueBallX, cueBallY, mouseX, mouseY);
		startX = (distanceTipFromCueBall / distanceBalltoMouse) * (mouseX-
				cueBallX) + cueBallX;
		startY = (distanceTipFromCueBall / distanceBalltoMouse) * (mouseY-
				cueBallY) + cueBallY;
		endX = (distanceEndFromCueBall / distanceBalltoMouse) * (mouseX-
				cueBallX) + cueBallX;
		endY = (distanceEndFromCueBall / distanceBalltoMouse) * (mouseY-
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
	
	public double getSlope(double x1, double y1, double x2, double y2) {
		return (y2-y1)/(x2-x1);
	}
	
	public void setCueBallVelocity(Ball cueBall){ 
		cueBall.setXVelocity(100);
	}
}
