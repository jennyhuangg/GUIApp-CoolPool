package edu.andover.coolpool.model;

import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Observable;
import edu.andover.coolpool.GameConstants;

// Model class for a pool board, including interactions between the
// pool balls, cuestick, and pockets

public class PoolBoard extends Observable {

	private Ball[] balls = new Ball[16]; // All 16 balls in 8-ball pool
	private ArrayList<Ball> pocketedBalls = new ArrayList<Ball>();
	private ArrayList<Ball> remainingBalls = new ArrayList<Ball>();
	private Pocket[] pockets = new Pocket[6]; // 6 pockets along rim of board
	private CueStick cueStick;

	// X and Y coordinates of top left corner of playable board
	// TODO: Eric: Fix magic constants
	private double boardX = GameConstants.POOLBOARD_X 
			* GameConstants.PIXEL_TO_IN;
	private double boardY = GameConstants.POOLBOARD_Y 
			* GameConstants.PIXEL_TO_IN;

	// Length and width of playable board
	private double length = GameConstants.POOL_TABLE_LENGTH; 
	private double width = GameConstants.POOL_TABLE_WIDTH;

	// Counts number of collisions of balls with boundaries in each term
	private int numBumperCollisions = 0;

	public boolean isStable; // True if all balls are at rest
	public boolean bounced = false;
	public boolean resetCue = false;

	public PoolBoard() {
		for (int i = 0; i < pockets.length; i++) {
			pockets[i] = new Pocket(i, boardX, boardY);
		}
		setUpBalls();
		cueStick = new CueStick(balls[15]);
	}

	// Initializes the array of balls and places the balls in the correct
	// locations on the pool board
	private void setUpBalls() {
		// Set IDs for each ball.
		for (int k = 0; k < balls.length; k ++) { 
			if (k < 7) { balls[k] = new Ball(0); }
			else if (k >= 7 && k < 14) { balls[k] = new Ball(1); }
			else if (k == 14) { balls[k] = new Ball(3); } //Eight ball
			else { balls[k] = new Ball(2); } // Cueball
		}
		// Set balls in triangle formation.
		rackBalls(balls);

		for (Ball b: balls) {
			remainingBalls.add(b);
		}
	}

	// Sets balls back up in triangle formation. Useful for re-racking after
	// illegal breaks.
	public void rackBalls(Ball[] balls) {
		double centerY = width / 2 + boardY;
		double incrementX = 2.25 * Math.cos(30) * GameConstants.IN_TO_PIXEL;
		double radius = 1.125;
		double threeQuartersLength = 0.75*length+boardX;

		balls[0].setCenter(threeQuartersLength, centerY);

		balls[1].setCenter(threeQuartersLength + 1 * incrementX,
				centerY + radius);

		balls[7].setCenter(threeQuartersLength + 1 * incrementX, 
				centerY - radius);

		balls[8].setCenter(threeQuartersLength + 2 * incrementX, 
				centerY + 2 * radius);
		balls[14].setCenter(threeQuartersLength + 2 * incrementX, 
				centerY); //8 Ball
		balls[2].setCenter(threeQuartersLength + 2 * incrementX, 
				centerY - 2 * radius);

		balls[3].setCenter(threeQuartersLength + 3 * incrementX, 
				centerY + 3 * radius);
		balls[9].setCenter(threeQuartersLength + 3 * incrementX, 
				centerY + radius);
		balls[4].setCenter(threeQuartersLength + 3 * incrementX, 
				centerY - radius);
		balls[10].setCenter(threeQuartersLength + 3 * incrementX, 
				centerY - 3 * radius);

		balls[11].setCenter(threeQuartersLength + 4 * incrementX, 
				centerY + 4 * radius);
		balls[5].setCenter(threeQuartersLength + 4 * incrementX, 
				centerY + 2 * radius);
		balls[12].setCenter(threeQuartersLength + 4 * incrementX, 
				centerY);
		balls[13].setCenter(threeQuartersLength + 4 * incrementX, 
				centerY - 2 * radius);
		balls[6].setCenter(threeQuartersLength + 4 * incrementX, 
				centerY - 4 * radius);

		// Places cue ball in correct spot.
		balls[15].setCenter(length * 1/4 + boardX, width / 2 + boardY);

		numBumperCollisions = 0;
	}

	// Updates positions and states of the balls at each time step of 
	// 0.01 seconds
	public void update() {
		double elapsedSeconds = 0.01;
		for (Ball b : remainingBalls) {
			b.setCenterX(b.getCenterX() + elapsedSeconds * b.getXVelocity());
			b.setCenterY(b.getCenterY() + elapsedSeconds * b.getYVelocity());
		}
		checkPockets();
		checkCollisions();
		decelerateBalls();
	}

	// Returns true if no balls are moving.
	public boolean stable(){
		isStable = true;
		for (Ball ball: remainingBalls){
			if (ball.getXVelocity() != 0 || ball.getYVelocity() != 0) {
				isStable = false;
			}
		}
		return isStable;
	}

	public void resetCueBall() { //TODO: will change to get User Input Later
		pocketedBalls.remove(balls[15]);
		remainingBalls.add(balls[15]);
		balls[15].unpocket();
		
		balls[15].setCenter(length * 1/4 + boardX, width / 2 + boardY);

		resetCue = true;
		setChanged();
		notifyObservers();
		resetCue = false;
	}

	// Checks to see if any balls have fallen inside the pockets
	// Falls inside pockets if (distance between ball and pocket) <=
	// (radius_of_pocket - radius_of_ball)
	private void checkPockets(){
		for (Pocket pocket: pockets){
			for (Ball ball: balls){
				double distance = Math.sqrt(Math.pow(pocket.getXPosition() -
						ball.getCenterX(), 2) + 
						Math.pow(pocket.getYPosition() - ball.getCenterY(), 2));
				if(distance <= pocket.getRadius()
						&& !ball.getIsPocketed()){
					ball.setPocketed();
					pocketedBalls.add(ball);
					remainingBalls.remove(ball);
				}
			}
		}
	}

	// Checks whether balls have collided with each other or with the walls
	private void checkCollisions() {
		for (Ball ball: remainingBalls)
		{
			// Changes velocity when ball collides with the vertical walls of
			// the pool board (i.e the vertical bumpers).
			if ((ball.getCenterX() - ball.getRadius() <= 
					boardX && ball.getXVelocity() < 0) 
					|| (ball.getCenterX() + ball.getRadius() >= 
					length + boardX && ball.getXVelocity() > 0)) {
				ball.setXVelocity(ball.getXVelocity()*(-1));
				numBumperCollisions++;
			}
			// Changes velocity when ball collides with horizontal walls of the
			// pool board (i.e the horizontal bumpers).
			if ((ball.getCenterY() - ball.getRadius() <= 
					boardY && ball.getYVelocity() < 0)
					|| (ball.getCenterY() + ball.getRadius() >= 
					width + boardY & ball.getYVelocity() > 0)) {
				ball.setYVelocity(ball.getYVelocity()*(-1));
				numBumperCollisions++;
			}
			// Changes velocity when ball collides with other balls.
			for (Ball b2: remainingBalls){
				final double deltaX = b2.getCenterX() - ball.getCenterX() ;
				final double deltaY = b2.getCenterY() - ball.getCenterY() ;
				if (colliding(ball, b2, deltaX, deltaY)) {
					bounced = true;
					bounce(ball, b2, deltaX, deltaY);
					setChanged();
					notifyObservers();
					bounced = false;
				}
			}
		}
	}

	// Decreases the speed of all balls uniformly due to kinetic friction
	// with the pool board unless speed of ball is already 0.
	public void decelerateBalls(){
		double elapsedSeconds = 0.1;
		for (Ball ball: remainingBalls){
			double xVel = ball.getXVelocity();
			double yVel = ball.getYVelocity();
			double speed = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
			if (xVel != 0 || yVel != 0){
				if (xVel < 0){
					ball.setXVelocity(Math.min(xVel - GameConstants.MU_K*
							elapsedSeconds*xVel/speed, 0));
				}
				if (yVel < 0){
					ball.setYVelocity(Math.min(yVel - GameConstants.MU_K*
							elapsedSeconds*yVel/speed, 0));
				}
				if (xVel > 0){
					ball.setXVelocity(Math.max(xVel - GameConstants.MU_K*
							elapsedSeconds*xVel/speed, 0));
				}
				if (yVel > 0){
					ball.setYVelocity(Math.max(yVel - GameConstants.MU_K*
							elapsedSeconds*yVel/speed, 0));
				}
			}
		}
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

	// Clears pockets after each turn
	public void resetPocketedBalls() { 
		pocketedBalls = new ArrayList<Ball>(); 
	}

	public Ball[] getBalls() { return balls; }
	public ArrayList<Ball> pocketedBalls() { return pocketedBalls; }
	public Pocket[] getPockets(){ return pockets; }
	public CueStick getCueStick(){ return cueStick; }
	public int getNumBumperCollisions() { return numBumperCollisions; }
}
