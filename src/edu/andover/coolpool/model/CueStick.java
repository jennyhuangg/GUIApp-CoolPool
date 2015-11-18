package edu.andover.coolpool.model;

import java.util.Observable;

public class CueStick extends Observable {
	
	// Start is position of tip of cue stick (end close to cue ball).
	private double startX;
	private double startY;
	
	// End is position of end of cue stick (end far from cue ball).
	private double endX;
	private double endY;
	
	// Position of cue stick when mouse is first pressed.
	// Resets every time cue stick is used.
	private double initStartX;
	private double initStartY;
	private double initEndX;
	private double initEndY;
	
	// Mouse position when first pressed.
	// Resets every time cue stick is used.
	private double initMouseX;
	private double initMouseY;
	
	// Projected distance between initial mouse position and start of cue stick.
	private double distanceInit;
	
	// Projected distance between initial mouse position and dragging mouse.
	private static double distanceInitToMouse;
	
	// True if values of initial mouse click position can be reset, 
	// i.e. true only on first mouse click before drag.
	private boolean canReset = true;
	
	// True if cue stick can move on pool board.
	private boolean canMove = true;
	
	private boolean isDragging = false;
	
	private boolean hasHit = false;
	
	private final double distanceTipFromCueBall = 3.0;
	private final double cueStickLength = 37.0;
	
	// Upper limit for cue stick drag distance.
	private final double stretchLimit = 17.0;

	private Ball cueBall;
	
	public CueStick(Ball cueBall) {
		this.cueBall = cueBall;
		startX = cueBall.getCenterX() - distanceTipFromCueBall;
		startY = cueBall.getCenterY();
		endX = startX - cueStickLength;
		endY = startY;
	}

	// Updates cue ball velocity proportional to the projected distance dragged.
	public void updateCueBallVelocity(double finalMouseX, double finalMouseY) {
		
		// Adjusts amplification of cue ball speed.
		double amplifier = 3; 
		setDistanceInitToMouse(finalMouseX, finalMouseY);
		
		// Determines proportionally accurate direction of cue ball.
		double dirX = -initStartX + cueBall.getCenterX();
		double dirY = -initStartY + cueBall.getCenterY();
		
		// Determines velocity of cue ball.
		double xVel = amplifier*distanceInitToMouse*dirX;
		double yVel = amplifier*distanceInitToMouse*dirY;
		
		// Sets velocity of cue ball.
		cueBall.setXVelocity(xVel);
		cueBall.setYVelocity(yVel);
		
		// Resets drag distance to 0.
		distanceInitToMouse = 0;
		
		hasHit = true;
		setChanged();
		notifyObservers();
		hasHit = false;
	}

	public double getStartX() { return startX; }
	public double getStartY() { return startY; }
	public double getEndX() { return endX; }
	public double getEndY() { return endY; }
	public double getDistanceInitToMouse() { return distanceInitToMouse; }
	public Ball getCueBall() { return cueBall; }
	
	// Uses distance formula to calculate distance between two points.
	private double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
	}

	public void setStartX(double startX) { this.startX = startX; }
	
	public void setStartY(double startY) { this.startY = startY; }
	
	public void setEndX(double endX) { this.endX = endX; }
	
	public void setEndY(double endY) { this.endY = endY; }
	
	public boolean canMove() {	return canMove; }
	
	public boolean canReset() { return canReset; }
	
	public boolean isDragging(){ return isDragging; }
	
	// Returns true if mouse is dragging in the correct direction (away from
	// cue ball in direction of initial mouse click).
	private boolean isDraggingInCorrectDirection(double mouseX, double mouseY) {
		double cueBallX = cueBall.getCenterX();
		double cueBallY = cueBall.getCenterY();
		
		boolean isDraggingInCorrectDirection = true; 
		if (cueBallX > initMouseX) {
			if (cueBallY > initMouseY) {
				if (mouseX >= initMouseX && mouseY >= initMouseY) {
					isDraggingInCorrectDirection = false; 
				}
			}	
			else if (cueBallY < initMouseY ) {
				if (mouseX >= initMouseX && mouseY <= initMouseY) {
					isDraggingInCorrectDirection = false; 
				}
			}
		}
		else { 
			if (cueBallY > initMouseY) {
				if (mouseX <= initMouseX && mouseY >= initMouseY) {
					isDraggingInCorrectDirection = false;					
				}	
			}	
			else if (cueBallY < initMouseY ) {
				if (mouseX <= initMouseX && mouseY <= initMouseY) {
					isDraggingInCorrectDirection = false;					
				}	
			}
		}
		return isDraggingInCorrectDirection;
	 }

	public boolean hasHit(){ return hasHit; }
	
	public void setCanMove(boolean canMove) { this.canMove = canMove; }

	public void setCanReset(boolean canReset) { this.canReset = canReset; }
	
	public void setCueBall(Ball cueBall){ this.cueBall = cueBall; }
	
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
	
	// Sets projected distance between initial mouse click and mouse on drag.
	// Must go in correct direction. Has max stretch limit.
	private void setDistanceInitToMouse (double mouseX, double mouseY) {
		
		// Projected distance between mouse and initial tip of cue stick.
		double distanceInitTipToMouse = Math.abs((1 / cueStickLength)*((mouseX - 
				initStartX)*(initEndX - initStartX) + (mouseY - initStartY)
				*(initEndY - initStartY)));
		
		// Projected distance between mouse and initial mouse click position
		double distanceInitToMouse = distanceInitTipToMouse - distanceInit;
		
		// If dragging in incorrect direction, then drag distance is 0.
		if (!isDraggingInCorrectDirection(mouseX, mouseY)) {
			distanceInitToMouse = 0;
		}
		
		// Cue stick can only drag as far as the stretch limit.
 		if (distanceInitToMouse > stretchLimit) {
 			distanceInitToMouse = stretchLimit;
 		}
 		
		CueStick.distanceInitToMouse = Math.abs(distanceInitToMouse);
		
		setChanged();
		notifyObservers();
	}
	
	// General method that calculates new position of cue stick based on 
	// distance of its tip from the cue ball and a point that indicates
	// direction away from cue ball.
	private void setNewCueStickLocation(double distanceTipFromCueBall, double x, 
			double y) {
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
		
		setChanged();
		notifyObservers();
	}
	
	// Set location when mouse is hovering.
	public void setHoverCueStickLocation(double mouseX, double mouseY) {
		setNewCueStickLocation(distanceTipFromCueBall, mouseX, mouseY);
	}
	
	// Set location when mouse is dragging.
	public void setCueStickLocationOnDrag(double mouseX, double mouseY) {
		isDragging = true;
		
		setDistanceInitToMouse(mouseX, mouseY);
		// Distance between the tip of cue stick to the cue ball
 		double newDistanceTipFromCueBall = distanceTipFromCueBall + 
 				distanceInitToMouse;
		
 		setNewCueStickLocation(newDistanceTipFromCueBall, initStartX, 
				initStartY);
	}
			
	// Set location when mouse is released.
	public void setCueStickLocationAfterHit() {
		isDragging = false;
		setNewCueStickLocation(distanceTipFromCueBall, initStartX, initStartY);
	}
}