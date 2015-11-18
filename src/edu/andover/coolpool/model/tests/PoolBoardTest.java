package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.model.PoolBoard;

// TODO: Create tests for interfaces, not implementations. We shouldn't be
// testing private methods.
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
	public void decelerateBallsWhenVelocityIsZeroShouldDoNothing(){
		PoolBoard poolBoard = new PoolBoard();
		Ball[] balls = poolBoard.getBalls();
		for (int i = 0; i < 16; i ++){
			double speed = Math.sqrt(Math.pow(balls[i].getXVelocity(), 2) + 
					Math.pow(balls[1].getYVelocity(), 2));
			assertEquals(0, speed);
		}

		poolBoard.decelerateBalls();
		for (int i = 0; i < 16; i ++){
			double speed = Math.sqrt(Math.pow(balls[i].getXVelocity(), 2) + 
					Math.pow(balls[1].getYVelocity(), 2));
			assertEquals(0, speed);
		}
	}

}
