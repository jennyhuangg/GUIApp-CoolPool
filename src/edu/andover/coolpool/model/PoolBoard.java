package edu.andover.coolpool.model;

import edu.andover.coolpool.GameConstants;
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

	private double boardX;
	private double boardY;

	AnimationTimer timer;

	public PoolBoard() {
		length = 92; //Inches
		width = 46; //Inches
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

		poolBoardView.getBigRectangle().getX();
		boardX = poolBoardView.getRectangle().getX() *
				GameConstants.PIXEL_TO_IN;
		boardY = poolBoardView.getRectangle().getY() *
				GameConstants.PIXEL_TO_IN;

		setUpBalls();

		for (Ball ball: balls){
			poolBoardView.getPane().getChildren().add(ball.getView());
		}

		System.out.println(boardX);
	}

	public void setUpBalls() {
		double centerY = width / 2 + boardY;
		double incrementX = 2.25 * Math.cos(30) * GameConstants.IN_TO_PIXEL;
		double radius = 1.125;

		balls = new Ball[16];

		balls[0] = new Ball(length * 3/4 + boardX, centerY, 1);

		balls[1] = new Ball(length * 3/4 + boardX + 1 * incrementX, 
				centerY + radius, 1);
		balls[2] = new Ball(length * 3/4 + boardX + 1 * incrementX, 
				centerY - radius, 2);


		balls[3] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY + 2 * radius, 2);
		balls[4] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY, 4); //8 Ball
		balls[5] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY - 2 * radius, 1);


		balls[6] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY + 3 * radius, 1);
		balls[7] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY + radius, 2);
		balls[8] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY - radius, 1);
		balls[9] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY - 3 * radius, 2);


		balls[10] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY + 4 * radius, 2);
		balls[11] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY + 2 * radius, 1);
		balls[12] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY, 1);
		balls[13] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY - 2 * radius, 2);
		balls[14] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY - 4 * radius, 1);

		//CueBall
		balls[15] = new Ball(length * 1/4 + boardX, width / 2 + boardY, 3);
		balls[15].setXVelocity(100);
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
		if (stable()) { 
			timer.stop();
		}
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
			if ((ball.getCenterX() - ball.getRadius() <= boardX && ball.getXVelocity() < 0) 
					|| (ball.getCenterX() + ball.getRadius() >= length + boardX && ball.getXVelocity() > 0)) {
				ball.setXVelocity(ball.getXVelocity()*(-1));
			}
			if ((ball.getCenterY() - ball.getRadius() <= boardY && ball.getYVelocity() < 0)
					|| (ball.getCenterY() + ball.getRadius() >= width + boardY & ball.getYVelocity() > 0)) {
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

		poolBoardView.getRectangle().setX(180);
		poolBoardView.getRectangle().setY(177);

		poolBoardView.getBigRectangle().setX(180);
		poolBoardView.getBigRectangle().setY(177);
	}

	public PoolBoardView getView(){
		return poolBoardView;
	}
}
