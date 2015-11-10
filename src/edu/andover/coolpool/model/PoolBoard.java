package edu.andover.coolpool.model;

import static java.lang.Math.sqrt;

import java.util.ArrayList;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.view.GameSounds;
import edu.andover.coolpool.view.PoolBoardView;

// Model class for a pool board, including interactions between the
// pool balls.

public class PoolBoard {

	private Ball[] balls; //Array of balls
	private ArrayList<Ball> pocketedBalls = new ArrayList<Ball>();
	private ArrayList<Ball> unpocketedBalls = new ArrayList<Ball>();
	private Pocket[] pockets; //Array of pockets

	private double length; 
	private double width;
	
	private PoolBoardView poolBoardView;

	private double boardX; //X coordinate of top left corner of playable board
	private double boardY; //Y coordinate of top left corner of playable board

	public static boolean isStable;
	
	public PoolBoard() {
		length = 92; //Inches
		width = 46; //Inches
		setView();

		poolBoardView.getBigRectangle().getX();
		boardX = poolBoardView.getRectangle().getX() *
				GameConstants.PIXEL_TO_IN;
		boardY = poolBoardView.getRectangle().getY() *
				GameConstants.PIXEL_TO_IN;

		setUpBalls();
		setUpPockets();

		for (Pocket pocket: pockets){
			poolBoardView.getPane().getChildren().add(pocket.getView());
		}

		for (Ball ball: balls){
			poolBoardView.getPane().getChildren().add(ball.getView());
		}

	}
	
	private void setUpPockets() {
		pockets = new Pocket[6];

		for (int i = 0; i < pockets.length; i++) {
			pockets[i] = new Pocket(i, length, width, boardX, boardY);
		}
	}

	// Initializes the array of balls and places the balls in the correct
	// locations on the pool board
	private void setUpBalls() {
		double centerY = width / 2 + boardY;
		double incrementX = 2.25 * Math.cos(30) * GameConstants.IN_TO_PIXEL;
		double radius = 1.125;

		// Creates balls on table.
		balls = new Ball[16];

		// Place balls in triangle formation.
		balls[0] = new Ball(length * 3/4 + boardX, centerY, 0);

		balls[1] = new Ball(length * 3/4 + boardX + 1 * incrementX, 
				centerY + radius, 0);
		balls[2] = new Ball(length * 3/4 + boardX + 1 * incrementX, 
				centerY - radius, 1);

		balls[3] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY + 2 * radius, 1);
		balls[4] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY, 3); //8 Ball
		balls[5] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY - 2 * radius, 0);

		balls[6] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY + 3 * radius, 0);
		balls[7] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY + radius, 1);
		balls[8] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY - radius, 0);
		balls[9] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY - 3 * radius, 1);

		balls[10] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY + 4 * radius, 1);
		balls[11] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY + 2 * radius, 0);
		balls[12] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY, 1);
		balls[13] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY - 2 * radius, 1);
		balls[14] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY - 4 * radius, 0);

		// Places cue ball in correct spot.
		balls[15] = new Ball(length * 1/4 + boardX, width / 2 + boardY, 2);
		
		for (Ball b: balls) {
			unpocketedBalls.add(b);
			}
	}

	//updates positions and states of the balls at each time step of 
	//0.01 seconds
	public void update() {
		double elapsedSeconds = 0.01;
		for (Ball b : unpocketedBalls) {
			b.setCenterX(b.getCenterX() + elapsedSeconds * b.getXVelocity());
			b.setCenterY(b.getCenterY() + elapsedSeconds * b.getYVelocity());
		}
		checkPockets();
		checkCollisions();
		decelerateBalls();
	}

	// Checks to see if any balls have fallen inside the pockets
	// Falls inside pockets if (distance between ball and pocket) <=
	// (radius_of_pocket - radius_of_ball)
	public void checkPockets(){
		for (Pocket pocket: pockets){
			for (Ball ball: balls){
				double distance = Math.sqrt(Math.pow(pocket.getXPosition() -
						ball.getCenterX(), 2) + 
						Math.pow(pocket.getYPosition() - ball.getCenterY(), 2));

				if(distance <= pocket.getRadius()
						&& !ball.getIsPocketed()){
					ball.setPocketed();
					pocketedBalls.add(ball);
					unpocketedBalls.remove(ball);
					GameSounds.BALL_FALLING_IN_POCKET.play();
				}
			}
		}
	}

	public void checkCollisions() {
		for (Ball ball: unpocketedBalls)
		{
			// Changes velocity when ball collides with vertical edges.
			if ((ball.getCenterX() - ball.getRadius() <= 
					boardX && ball.getXVelocity() < 0) 
					|| (ball.getCenterX() + ball.getRadius() >= 
					length + boardX && ball.getXVelocity() > 0)) {
				ball.setXVelocity(ball.getXVelocity()*(-1));
			}
			// Changes velocity when ball collides with horizontal edges.
			if ((ball.getCenterY() - ball.getRadius() <= 
					boardY && ball.getYVelocity() < 0)
					|| (ball.getCenterY() + ball.getRadius() >= 
					width + boardY & ball.getYVelocity() > 0)) {
				ball.setYVelocity(ball.getYVelocity()*(-1));
			}

			// Changes velocity when ball collides with other balls.
			for (Ball b2: unpocketedBalls){
				final double deltaX = b2.getCenterX() - ball.getCenterX() ;
				final double deltaY = b2.getCenterY() - ball.getCenterY() ;
				if (colliding(ball, b2, deltaX, deltaY)) {
					GameSounds.BALL_HIT_BALL.play();
					bounce(ball, b2, deltaX, deltaY);
				}
			}
		}
	}

	// Decreases the speed of all balls uniformly due to kinetic friction
	// with the pool board unless speed of ball is already 0.
	public void decelerateBalls(){
		double elapsedSeconds = 0.1;

		for (Ball ball: unpocketedBalls){
			double xVel = ball.getXVelocity();
			double yVel = ball.getYVelocity();
			double speed = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
			if (xVel != 0 || yVel != 0){
				{
					if (xVel < 0){
						ball.setXVelocity(Math.min(xVel - 
								2*elapsedSeconds*xVel/speed, 0));
					}
					if (yVel < 0){
						ball.setYVelocity(Math.min(yVel - 
								2*elapsedSeconds*yVel/speed, 0));
					}
					if (xVel > 0){
						ball.setXVelocity(Math.max(xVel - 
								2*elapsedSeconds*xVel/speed, 0));
					}
					if (yVel > 0){
						ball.setYVelocity(Math.max(yVel - 
								2*elapsedSeconds*yVel/speed, 0));
					}
				}
			}
		}
	}

	// Returns true if no balls are moving.
	public boolean stable(){
		isStable = true;
		for (Ball ball: unpocketedBalls){
			if (ball.getXVelocity() != 0 || ball.getYVelocity() != 0) {
				isStable = false;
			}
		}
		return isStable;
	}

	// Returns true if b1 and b2 are colliding. 
	public boolean colliding(final Ball b1, final Ball b2, final double deltaX, 
			final double deltaY) {
		// Balls are colliding if (x2-x1)^2 + (y2-y1)^2 < (r1 + r2)^2
		// and if distance between them is decreasing.
		final double radiusSum = b1.getRadius() + b2.getRadius();
		if (deltaX * deltaX + deltaY * deltaY <= radiusSum * radiusSum) {
			if ( deltaX * (b2.getXVelocity() - b1.getXVelocity()) 
					+ deltaY * (b2.getYVelocity() - b1.getYVelocity()) < 0) {
				return true;
			}
		}
		return false;
	}

	// Processes a collision with momentum equations. Updates velocities of 
	// the balls after collisions
	private void bounce(final Ball b1, final Ball b2, final double deltaX, 
			final double deltaY) {
		// Direction of collision is <deltaX, deltaY>.

		double distance = sqrt(deltaX * deltaX + deltaY * deltaY);

		//Collision vector is <unitContactX, unitContactY>.
		double unitContactX = deltaX / distance; 
		double unitContactY = deltaY / distance;

		double b1_i = b1.getXVelocity()*unitContactX + b1.getYVelocity()*
				unitContactY;
		double b2_i = b2.getXVelocity()*unitContactX + b2.getYVelocity()*
				unitContactY;

		double b1_f = b2_i;
		double b2_f = b1_i;

		b1.setXVelocity(b1.getXVelocity() +  unitContactX*(b1_f - b1_i));
		b1.setYVelocity(b1.getYVelocity() +  unitContactY*(b1_f - b1_i));
		b2.setXVelocity(b2.getXVelocity() +  unitContactX*(b2_f - b2_i));
		b2.setYVelocity(b2.getYVelocity() +  unitContactY*(b2_f - b2_i));

	}

	public double getLength(){ return length; }

	public double getWidth(){ return width; }

	public PoolBoardView getView(){ return poolBoardView; }

	public Ball[] getBalls() { return balls; }

	public void setView(){
		poolBoardView = new PoolBoardView(length, width);

		poolBoardView.getRectangle().setX(180);
		poolBoardView.getRectangle().setY(177);

		poolBoardView.getBigRectangle().setX(180);
		poolBoardView.getBigRectangle().setY(177);
	}
	
	public ArrayList<Ball> pocketedBalls() { return pocketedBalls; }
	
	public void resetCueBall() { //will change to get User Input Later
		pocketedBalls.remove(balls[15]);
		balls[15] = new Ball(length * 1/4 + boardX, width / 2 + boardY, 2);
		poolBoardView.getPane().getChildren().add(balls[15].getView());
		unpocketedBalls.add(balls[15]);
	}
	
	public void resetPocketedBalls() {pocketedBalls = new ArrayList<Ball>(); }
	
	
	
}