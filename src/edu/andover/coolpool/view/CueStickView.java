package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import javafx.scene.shape.Line;

public class CueStickView {
	private Line line;

	private double startX;
	private double startY;

	private double endX;
	private double endY;

	public CueStickView(double startXCM, double startYCM, 
			double endXCM, double endYCM){

		this.startX = startXCM * GameConstants.CM_TO_PIXEL;
		this.startY = startYCM*GameConstants.CM_TO_PIXEL;

		this.endX = endXCM*GameConstants.CM_TO_PIXEL;
		this.endY = endYCM*GameConstants.CM_TO_PIXEL;

		line = new Line(startX, startY, endX, endY);
	}


	public double getStartX() { return startX; }
	public double getStartY() { return startY; }
	public double getEndX() { return endX; }
	public double getEndY() { return endY; }

	public void setStartX(double startX) {
		this.startX = startX*GameConstants.CM_TO_PIXEL;
	}

	public void setStartY(double startY) {
		this.startY = startY*GameConstants.CM_TO_PIXEL;
	}

	public void setEndX(double endX) {
		this.endX = endX*GameConstants.CM_TO_PIXEL;
	}

	public void setEndY(double endY) {
		this.endY = endY*GameConstants.CM_TO_PIXEL;
	}
}
