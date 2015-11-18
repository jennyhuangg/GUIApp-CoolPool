package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

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

	
	// TODO: RackBalls sets correct location for one ball
	// TODO: RackBalls should set numBumperCollisions to 0.
	// TODO: stable returns true if stable
	// TODO: resetCue
	// TODO: 

}
