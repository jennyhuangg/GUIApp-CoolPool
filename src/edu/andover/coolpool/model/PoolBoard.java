package edu.andover.coolpool.model;

import edu.andover.coolpool.view.PoolBoardView;
import javafx.animation.AnimationTimer;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.scene.layout.Pane;

public class PoolBoard {

	private Ball[] balls; //16 balls
	private boolean isPaused;
	private Pocket[] pockets;
	private double length;
	private double width;
	private PoolBoardView poolBoardView;

	AnimationTimer timer;

	public PoolBoard() {
		balls = new Ball[16];
		for (int i = 0; i < 16; i ++) {balls[i] = new Ball(i); }
		length = 284; //CENTIMETERS
		width = 142; //CENTIMETERS
		isPaused = false;

		timer = new AnimationTimer() {
			@Override
			public void handle(long timestamp) {
				update();
			}
		};
		

		pockets = new Pocket[6];
		for (int i = 0; i < 6; i ++) {pockets[i] = new Pocket(i, 
				this.length, this.width); }
		setView();
	}

	public void checkPockets(){
		for (Pocket pocket: pockets){
			for (Ball ball: balls){
				double distance = Math.sqrt(Math.pow(pocket.getXPosition() - ball.getCenterX(), 2) + 
						Math.pow(pocket.getYPosition() - ball.getCenterY(), 2));
				if (distance <= Math.abs(pocket.getRadius() - ball.getRadius())){
					ball.setPocketed();
				}
			}
		}
	}

	public void update() {
		double elapsedSeconds = 0.01;
		for (Ball b : balls) {
			b.setCenterX(b.getCenterX() + elapsedSeconds * b.getXVelocity());
			b.setCenterY(b.getCenterY() + elapsedSeconds * b.getYVelocity());
		}
		checkCollisions();
		//checkPockets();
		decelerateBalls();
		if (stable()) timer.stop();


	}

	public void setAnimationTimer(){
	}

	public void animate() {
		timer.start();
	}

	public boolean stable(){
		boolean isStable = true;
		for (Ball ball: balls){
			if (ball.getXVelocity() != 0 || ball.getYVelocity() != 0) {
				isStable = false;
			}
		}
		return isStable;
	}

	public void checkCollisions() {
		for (Ball ball: balls)
		{
			if ((ball.getCenterX() - ball.getRadius() <= 0 && ball.getXVelocity() < 0) || (ball.getCenterX() + ball.getRadius() >= length && ball.getXVelocity() > 0)) {
				ball.setXVelocity(ball.getXVelocity()*(-1));
			}
			if ((ball.getCenterY() - ball.getRadius() <= 0 && ball.getYVelocity() < 0)|| (ball.getCenterY() + ball.getRadius() >= width && ball.getYVelocity() > 0)) {
				ball.setYVelocity(ball.getYVelocity()*(-1));
			}
		}
	}

	public void decelerateBalls(){
		double elapsedSeconds = 0.1;

		for (Ball ball: balls){
			double xVel = ball.getXVelocity();
			double yVel = ball.getYVelocity();
			double speed = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
			if (xVel != 0 || yVel != 0){
				{
					if (xVel < 0){
						ball.setXVelocity(Math.min(xVel - 3*elapsedSeconds*xVel/speed, 0));
					}
					if (yVel < 0){
						ball.setYVelocity(Math.min(yVel - 3*elapsedSeconds*yVel/speed, 0));
					}
					if (xVel > 0){
						ball.setXVelocity(Math.max(xVel - 3*elapsedSeconds*xVel/speed, 0));
					}
					if (yVel > 0){
						ball.setYVelocity(Math.max(yVel - 3*elapsedSeconds*yVel/speed, 0));
					}
				}
			}
		}
	}

	public void pauseGame() {
		timer.stop();
	}
	
	public double getLength(){
		return length;
	}
	
	public double getWidth(){
		return width;
	}
	
	public void setView(){
		
		poolBoardView = new PoolBoardView(length, width);
		for (Ball ball: balls){
			poolBoardView.getPane().getChildren().add(ball.getView());
		}
		
	}
	
	public Pane getView(){
		return poolBoardView.getPane();
	}
}
