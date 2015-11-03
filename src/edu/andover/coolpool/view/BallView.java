package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class BallView {
	private Circle circle;

	private double radius;

	private double centerX;

	private double centerY;


	public BallView(double centerXCM, double centerYCM, double radiusCM, int id){

		this.centerX = centerXCM * GameConstants.CM_TO_PIXEL;
		this.centerY = centerYCM * GameConstants.CM_TO_PIXEL;

		this.radius = radiusCM * GameConstants.CM_TO_PIXEL;

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
		this.centerX = centerXCM * GameConstants.CM_TO_PIXEL; 
	}
	
	public void setCenterY(double centerYCM) {
		this.centerY = centerYCM * GameConstants.CM_TO_PIXEL;
	}
}
