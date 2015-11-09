package edu.andover.coolpool.view;

//This class is not used in Version 0, but will be implemented in the final.
import edu.andover.coolpool.GameConstants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class CueStickView {
	private Line line;

	private double startX;
	private double startY;

	private double endX;
	private double endY;

	public CueStickView(double startXCM, double startYCM, 
			double endXCM, double endYCM){

		this.startX = startXCM * GameConstants.IN_TO_PIXEL;
		this.startY = startYCM*GameConstants.IN_TO_PIXEL;

		this.endX = endXCM*GameConstants.IN_TO_PIXEL;
		this.endY = endYCM*GameConstants.IN_TO_PIXEL;

		line = new Line(startX, startY, endX, endY);
		line.setStrokeWidth(2.0);
		line.setFill(Color.BROWN);
	}

	public double getStartX() { return startX; }
	public double getStartY() { return startY; }
	public double getEndX() { return endX; }
	public double getEndY() { return endY; }

	public void setStartX(double startX) {
		this.startX = startX*GameConstants.IN_TO_PIXEL;
	}

	public void setStartY(double startY) {
		this.startY = startY*GameConstants.IN_TO_PIXEL;
	}

	public void setEndX(double endX) {
		this.endX = endX*GameConstants.IN_TO_PIXEL;
	}

	public void setEndY(double endY) {
		this.endY = endY*GameConstants.IN_TO_PIXEL;
	}
	
	public Shape getLine() {return line; }
}
