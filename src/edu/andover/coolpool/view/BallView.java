package edu.andover.coolpool.view;
import java.util.Observable;
import java.util.Observer;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.Ball;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

//View class for the ball.

public class BallView implements Observer {
	private Circle circle;
	private double radius;
	private double centerX;
	private double centerY;

	private Ball ball; //observable value.

	public BallView(Ball ball){
		
		this.ball = ball;

		double centerX_inches = ball.getCenterX();
		double centerY_inches = ball.getCenterY();
		double radius_inches = ball.getRadius();
		int id = ball.getId();

		this.centerX = centerX_inches * GameConstants.IN_TO_PIXEL;
		this.centerY = centerY_inches * GameConstants.IN_TO_PIXEL;
		this.radius = radius_inches * GameConstants.IN_TO_PIXEL;
		circle = new Circle(centerX, centerY, radius);
		
		switch(id) {
			case 0:
				circle.setFill(Color.RED);
				break;
			case 1:
				circle.setFill(Color.BLUE);
				break;
			case 2:
				circle.setFill(Color.WHITE); // cueBall.
				break;		
			case 3:
				circle.setFill(Color.BLACK); // 8 ball.
				break;
			default: 
				break;
		}			
	}

	public void setCenterX() { 
		this.centerX = ball.getCenterX() * GameConstants.IN_TO_PIXEL; 
		circle.setCenterX(centerX);
	}

	public void setCenterY() {
		this.centerY = ball.getCenterY() * GameConstants.IN_TO_PIXEL;
		circle.setCenterY(centerY);
	}
	
	public void remove() {
		Pane parentNode = (Pane) circle.getParent();
		parentNode.getChildren().remove(circle);
	}

	public Shape getCircle() {return circle; }

	public void update(Observable o, Object arg) {
		if (ball == o){
			setCenterX();
			setCenterY();
			if (ball.isPocketed()){
				this.remove();
				GameSounds.BALL_FALLING_IN_POCKET.play();
			}
		}

	}
}
