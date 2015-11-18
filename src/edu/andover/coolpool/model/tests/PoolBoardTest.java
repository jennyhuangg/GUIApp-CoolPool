package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.model.PoolBoard;

public class PoolBoardTest {

	@Test
	public void collidingShouldReturnTrueWhenTwoBallsTouch(){
		Ball b1 = new Ball(0, 0, 1);
		b1.setXVelocity(10);
		Ball b2 = new Ball(1, 0, 1);
		b2.setXVelocity(-10);

		double deltaX = b2.getCenterX() - b1.getCenterX();
		double deltaY = b2.getCenterY() - b1.getCenterY();

		PoolBoard poolBoard = new PoolBoard();
		boolean colliding = poolBoard.colliding(b1, b2, deltaX, deltaY);
		assertEquals(colliding, true);
	}

	@Test
	public void collidingShouldReturnFalseWhenTwoBallsDontTouch(){
		Ball b1 = new Ball(0, 0, 1);
		Ball b2 = new Ball(1, 0, 2);

		double deltaX = b2.getCenterX() - b1.getCenterX() ;
		double deltaY = b2.getCenterY() - b1.getCenterY() ;

		PoolBoard poolBoard = new PoolBoard();
		boolean colliding = poolBoard.colliding(b1, b2, deltaX, deltaY);
		assertEquals(colliding, false);
	}

	@Test
	public void ballsShouldBeStableAtStart(){
		PoolBoard poolBoard = new PoolBoard();
		assertEquals(poolBoard.stable(), true);
	}

	@Test
	public void setUpBallsShouldSet16Balls() {
		PoolBoard poolBoard = new PoolBoard();
		Ball[] balls = poolBoard.getBalls();
		assertEquals(16, balls.length);
	}

	@Test
	public void rackBallsShouldSetCorrectLocationForEightBall() {
		PoolBoard poolBoard = new PoolBoard();
		Ball[] balls = poolBoard.getBalls();
		poolBoard.rackBalls(balls);
		double threeQuartersLength = 0.75 * 92 + GameConstants.POOLBOARD_X 
				* GameConstants.PIXEL_TO_IN;

		boolean check = threeQuartersLength == poolBoard.getBalls()[0].getCenterX();

		assertEquals(true, check);
	}

	@Test
	public void rackBallsShouldSetNumBumperCollisionsToZero() {
		PoolBoard poolBoard = new PoolBoard();
		Ball[] balls = poolBoard.getBalls();
		poolBoard.rackBalls(balls);

		assertEquals(0, poolBoard.getNumBumperCollisions());
	}

	@Test
	public void poolBoardShouldBeImmediatelyStable() {
		PoolBoard poolBoard = new PoolBoard();

		assertTrue(poolBoard.stable());
	}

	@Test
	public void resetCueBallShouldUnpocketCueBall() {
		PoolBoard poolBoard = new PoolBoard();
		Ball[] balls = poolBoard.getBalls();
		
		poolBoard.resetCueBall();
		
		assertFalse(balls[15].isPocketed());
	}
}
