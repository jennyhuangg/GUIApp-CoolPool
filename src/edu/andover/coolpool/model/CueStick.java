package edu.andover.coolpool.model;

import edu.andover.coolpool.view.CueStickView;
import javafx.scene.shape.Shape;

public class CueStick {
	
	private double startX;
	private double startY;
	private double endX;
	private double endY;
	
	private double dirX;
	private double dirY;
	
	private double initStartX;
	private double initStartY;
	private double initEndX;
	private double initEndY;
	private double initMouseX;
	private double initMouseY;
	private double endMouseX;
	private double endMouseY;
	boolean isSet = true;
	
	private static final double distanceTipFromCueBall = 3.0;
	private static final double cueStickLength = 37.0;

	private Ball cueBall;
	
	private CueStickView cueStickView;
	private PoolGame poolGame;

	public CueStick(Ball cueBall, PoolGame poolGame) {
		this.cueBall = cueBall;
		startX = cueBall.getCenterX() - distanceTipFromCueBall;
		startY = cueBall.getCenterY();
		endX = startX - cueStickLength;
		endY = startY;
		cueStickView = new CueStickView(startX, startY, endX, endY);
		this.poolGame = poolGame;
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
	
	public void setInitMouse(double mouseX, double mouseY) {
		this.initMouseX = mouseX;
		this.initMouseY = mouseY;
	}
	public void setCueStickLocationOnDrag(
			double endMouseX, double endMouseY) {
		if (isSet) {
			initStartX = this.getStartX();
			initStartY = this.getStartY();
			initEndX = this.getEndX();
			initEndY = this.getEndY();
			this.endMouseX = endMouseX;
			this.endMouseY = endMouseY;
		}
		
		//vector projection to maintain angle that the ball is hit at
		double distanceInitToEnd = Math.abs((1 / cueStickLength)*((endMouseX - 
				initStartX)*(initEndX - initStartX) + (endMouseY - initStartY)
				*(initEndY - initStartY)));
		
		//if (isSet) {
		//distanceInitMouseToCueBall = distanceInitToEnd;
		//}
		
		double newDistanceTipFromCueBall = distanceTipFromCueBall + distanceInitToEnd;
			//	- distanceInitMouseToCueBall- getDistance(initStartX, initStartY,);
		//System.out.println(distanceInitMouseToCueBall);
		//System.out.println(distanceInitToEnd);
		setNewCueStickLocation(newDistanceTipFromCueBall, initMouseX, initMouseY);
	
		isSet = false;
		
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
		double amplifier = .35;
		double xVel = amplifier*Math.abs(initMouseDragX - endMouseDragX)*dirX;
		double yVel = amplifier*Math.abs(initMouseDragY - endMouseDragY)*dirY;
		cueBall.setXVelocity(xVel);
		cueBall.setYVelocity(yVel);
		
		poolGame.turn();
	}
	
	public void setDirection(double mouseX, double mouseY) {
		dirX = cueBall.getCenterX()-mouseX;
		dirY = cueBall.getCenterY()-mouseY;
	}
	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	public void setCueBall(Ball cueBall){
		this.cueBall = cueBall;
	}
}
