package edu.andover.coolpool.view;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class PocketView {
	private Circle shape;
	private final double radius = 3;
	
	public PocketView(double x, double y) {
		shape = new Circle(x, y, radius);
		
		shape.setFill(Color.BLACK);
	}
	
	public Circle getCircle() { return shape; }
}
