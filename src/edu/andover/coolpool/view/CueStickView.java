package edu.andover.coolpool.view;

import java.util.Observable;
import java.util.Observer;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.CueStick;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Shape;

public class CueStickView implements Observer{
	private Line line;
	private double startX;
	private double startY;

	private double endX;
	private double endY;
	private CueStick cueStick;

	public CueStickView(CueStick cueStick){
		
		this.cueStick = cueStick;

		this.startX = cueStick.getStartX() * GameConstants.IN_TO_PIXEL;
		this.startY = cueStick.getStartY() *GameConstants.IN_TO_PIXEL;

		this.endX = cueStick.getEndX() *GameConstants.IN_TO_PIXEL;
		this.endY = cueStick.getEndY() *GameConstants.IN_TO_PIXEL;

		line = new Line(startX, startY, endX, endY);
		line.setStrokeWidth(6.0);
		line.setStroke(Color.BROWN);
	}

	public double getStartX() { return startX; }
	public double getStartY() { return startY; }
	public double getEndX() { return endX; }
	public double getEndY() { return endY; }

	public void setStartX(double startX) {
		this.startX = startX*GameConstants.IN_TO_PIXEL;
		line.setStartX(this.startX);
	}

	public void setStartY(double startY) {
		this.startY = startY*GameConstants.IN_TO_PIXEL;
		line.setStartY(this.startY);
	}

	public void setEndX(double endX) {
		this.endX = endX*GameConstants.IN_TO_PIXEL;
		line.setEndX(this.endX);
	}

	public void setEndY(double endY) {
		this.endY = endY*GameConstants.IN_TO_PIXEL;
		line.setEndY(this.endY);
	}
	
	public void remove() {
		Pane parentNode = (Pane) line.getParent();
		parentNode.getChildren().remove(line);
	}
	
	public void add() {
		Pane parentNode = (Pane) line.getParent();
		parentNode.getChildren().add(line);
	}
	public Shape getLine() {return line; }

	public void update(Observable o, Object arg) {
		if (o == cueStick){
			setStartX(cueStick.getStartX());
			setEndX(cueStick.getEndX());
			setStartY(cueStick.getStartY());
			setEndY(cueStick.getEndY());
		}
		
	}
	
	public CueStick getCueStick(){
		return cueStick;
	}
}
