package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import javafx.scene.Parent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class BallView {
	private Circle circle;

	private double radius;

	private double centerX;
	private double centerY;


	public BallView(double centerXCM, double centerYCM, double radius_inches, int id){

		this.centerX = centerXCM * GameConstants.IN_TO_PIXEL;
		this.centerY = centerYCM * GameConstants.IN_TO_PIXEL;

		this.radius = radius_inches * GameConstants.IN_TO_PIXEL;

		circle = new Circle(centerX, centerY, radius);

		//open up different image files for the sprite
		switch(id) {
		
		// 1 = red
		// 2 = blue
		// 3 = cue
		// 4 = black/8 ball
		case 1:
			circle.setFill(Color.RED);
			break;

		case 2:
			circle.setFill(Color.BLUE);
			break;

		case 3:
			circle.setFill(Color.WHITE);
			break;

		case 4:
			circle.setFill(Color.BLACK);
			break;

		default: 
			break;
		}
	}

	public double getCenterX() {return centerX;}
	public double getCenterY() {return centerY;}

	public void setCenterX(double centerXCM) { 
		this.centerX = centerXCM * GameConstants.IN_TO_PIXEL; 
		circle.setCenterX(centerX);
	}
	
	public void setCenterY(double centerYCM) {
		this.centerY = centerYCM * GameConstants.IN_TO_PIXEL;
		circle.setCenterY(centerY);
	}
	public void remove() {
		Pane parentNode = (Pane) circle.getParent();
		parentNode.getChildren().remove(circle);
	}
	
	public Shape getCircle() {return circle; }
}
