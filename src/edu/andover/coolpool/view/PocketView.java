package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PocketView {
	private Circle shape;
	
	public PocketView(double x, double y, double radius) {
		shape = new Circle(x, y, radius * GameConstants.IN_TO_PIXEL);
		
		Color lightBrown = Color.web("0x665847");
		shape.setFill(lightBrown);
	}
	
	public Circle getCircle() { return shape; }
}
