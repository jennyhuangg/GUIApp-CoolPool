package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.Pocket;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PocketView {
	private Circle shape;
	
	// Creates a light brown JFX circle from a given (x,y) position and a given 
	// radius.
	public PocketView(Pocket pocket) {
		double x = pocket.getXPosition();
		double y = pocket.getYPosition();
		double radius = pocket.getRadius();
		x = x * GameConstants.IN_TO_PIXEL;
		y = y * GameConstants.IN_TO_PIXEL;
		radius = radius * GameConstants.IN_TO_PIXEL;
		shape = new Circle(x, y, radius);
		
		Color lightBrown = Color.web("0x665847");
		shape.setFill(lightBrown);
	}
	
	public Circle getCircle() { return shape; }
}
