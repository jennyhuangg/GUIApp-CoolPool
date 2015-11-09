package edu.andover.coolpool.model;

//This class is not used in Version 0, but will be implemented in the final.
import edu.andover.coolpool.view.CueStickView;

public class CueStick {
	private double xVelocity;
	private double yVelocity;
	private double startX;
	private double startY;
	private double endX;
	private double endY;

	private CueStickView cueStickView;

	public CueStick() {
	}

	public CueStickView getView(){
		return cueStickView;
	}

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
	
	public void setCueBallVelocity(Ball cueBall){ 
		cueBall.setXVelocity(100);
		
	}

}
