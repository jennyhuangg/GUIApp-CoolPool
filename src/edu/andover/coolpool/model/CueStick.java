package edu.andover.coolpool.model;

import edu.andover.coolpool.view.CueStickView;
import javafx.scene.shape.Shape;

public class CueStick {
	
	// Start is position of tip of cue stick (end close to cue ball).
	private double startX;
	private double startY;
	
	// End is position of end of cue stick (end far from cue ball).
	private double endX;
	private double endY;
	
	// Proportional directions of x and y velocities.
	private double dirX;
	private double dirY;
	
	// Position of cue stick when mouse is first pressed.
	// Resets every time cue stick is used.
	private double initStartX;
	private double initStartY;
	private double initEndX;
	private double initEndY;
	
	// Mouse position when first pressed.
	private double initMouseX;
	private double initMouseY;
	
	// Distance between initial mouse position and start of cuestick.
	private double distanceInit;

	// True if values of initial mouse click position can be reset, i. e.
	// True only on first mouse click before drag.
	private boolean canReset = true;
	
	// True if cue stick can move on pool board.
	private boolean canMove = true;
	
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

	public double getStartX() { return startX; }
	public double getStartY() { return startY; }
	public double getEndX() { return endX; }
	public double getEndY() { return endY; }
	
	public Ball getCueBall() { return cueBall; }
	
	//TODO: this should not live in this class. Move to CueStickView.java
	public CueStickView getCueStickView() { return cueStickView; }
	//TODO: this should not live in this class. Move to CueStickView.java
	public Shape getView(){ return cueStickView.getLine(); }
	
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
	
	public boolean canMove() {
		return canMove;
	}
	
	public boolean canReset() {
		return canReset;
	}
	
	public void setCanMove(boolean canMove) {
		this.canMove = canMove;
	}

	public void setCanReset(boolean canReset) {
		this.canReset = canReset;
	}
	
	//------------------------- METHODS -------------------------------
	
	public void setCueBall(Ball cueBall){
		this.cueBall = cueBall;
	}
	
	// Uses distance formula to calculate distance between two points.
	public double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}
	
	// Sets values from initial mouse click position. Occurs before drag.
	public void setInitialValues(double initMouseX, double initMouseY) {
		if (canReset) {
			initStartX = this.getStartX();
			initStartY = this.getStartY();
			initEndX = this.getEndX();
			initEndY = this.getEndY();
			this.initMouseX =  initMouseX;
			this.initMouseY = initMouseY;
			
			// Vector projection to maintain angle that the ball is hit at.
			// Calculates perpendicular distance of mouse to cue stick.
			distanceInit = Math.abs((1 / cueStickLength)*((initMouseX - 
				initStartX)*(initEndX - initStartX) + (initMouseY - initStartY)
				*(initEndY - initStartY)));
		}
		canReset = false;
	}
	
	// Returns distance between first mouse click position and mouse on drag.
	public double getDistanceInitToMouse (double mouseX, double mouseY) {
		double distanceInitToMouse = Math.abs((1 / cueStickLength)*((mouseX - 
				initStartX)*(initEndX - initStartX) + (mouseY - initStartY)
				*(initEndY - initStartY)));
		return distanceInitToMouse;
	}
	
	// ------------------- SET LOCATION METHODS -----------------------
	
	// General method that calculates new position of cue stick based on 
	// distance of its tip from the cue ball and a point that indicates
	// direction away from cue ball.
	public void setNewCueStickLocation(double distanceTipFromCueBall, double x, double y) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
		
		double distanceBalltoMouse = getDistance(cueBallX, cueBallY, x, y);
		double distanceEndFromCueBall = distanceTipFromCueBall + cueStickLength;
		
		// Use similar triangles to find start and end points.
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
	
	// Set location when mouse is hovering.
	public void setHoverCueStickLocation(double mouseX, double mouseY) {
		setNewCueStickLocation(distanceTipFromCueBall, mouseX, mouseY);
	}
	
	// Set location when mouse is dragging.
	public void setCueStickLocationOnDrag(double mouseX, double mouseY) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
		
		double distanceInitToEnd = 0;
		
		//TODO: Refactor.
		if (cueBallX > initMouseX) {
			if (cueBallY > initMouseY) {
				if (mouseX >=initMouseX && mouseY >= initMouseY) {
					distanceInitToEnd = distanceInit;
				}	
				else {
					distanceInitToEnd = getDistanceInitToMouse(mouseX, mouseY);
				}
			}	
			else if (cueBallY < initMouseY ) {
				if (mouseX >=initMouseX && mouseY <= initMouseY) {
					distanceInitToEnd = distanceInit;
				}	
				else {
					distanceInitToEnd = getDistanceInitToMouse(mouseX, mouseY);
				}
			}
			else {
				distanceInitToEnd = distanceInit;

			}
		}
		else { 
			if (cueBallY > initMouseY) {
				if (mouseX <=initMouseX && mouseY >= initMouseY) {
					distanceInitToEnd = distanceInit;
				}	
				else {
					distanceInitToEnd = getDistanceInitToMouse(mouseX, mouseY);
				}
			}	
			else if (cueBallY < initMouseY ) {
				if (mouseX <=initMouseX && mouseY <= initMouseY) {
					distanceInitToEnd = distanceInit;
				}	
				else {
					distanceInitToEnd = getDistanceInitToMouse(mouseX, mouseY);
				}
			}
			else {
				distanceInitToEnd = distanceInit;
			}
		}

		// Subtract distanceInit so cue stick moves with where the mouse clicked it.
 		double newDistanceTipFromCueBall = distanceTipFromCueBall + 
 				distanceInitToEnd - distanceInit;
 		
 		// Stretch limitation
 		double stretchLimit = 20.0;
 		if (newDistanceTipFromCueBall > stretchLimit) {
 			newDistanceTipFromCueBall = stretchLimit;
 		}
		setNewCueStickLocation(newDistanceTipFromCueBall, initStartX, initStartY);
	}
	
	//Set location when mouse is released.
	public void setCueStickLocationAfterHit() {
		double newDistanceTipFromCueBall = 1;
		setNewCueStickLocation(newDistanceTipFromCueBall, initStartX, initStartY);
	}
	
	//----------------------- UPDATE CUEBALL METHODS ----------------------
	
	public void updateCueBallVelocity(double finalMouseX, double finalMouseY) {
		double amplifier = .6;
		double velocity = Math.abs(getDistanceInitToMouse(finalMouseX, 
				finalMouseY) - distanceInit);
		// Stretch limitation. TODO: make it a field.
		double stretchLimit = 17;
		if (velocity > stretchLimit) { velocity = stretchLimit; }
		double xVel = amplifier*velocity*dirX;
		double yVel = amplifier*velocity*dirY;
		cueBall.setXVelocity(xVel);
		cueBall.setYVelocity(yVel);
		poolGame.turn(); 
	}
	
	// To ensure that if mouse is released on the opposite side, the cue ball
	// still travels in the intended direction.
	// TODO: Eventually delete this class because when mouse is on opposite side
	// there should be zero velocity.
	
	public void setDirection(double mouseX, double mouseY) {
		dirX = -mouseX + cueBall.getCenterX();
		dirY = -mouseY + cueBall.getCenterY();
	}
}