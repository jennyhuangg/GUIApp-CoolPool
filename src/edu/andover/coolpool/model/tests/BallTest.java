package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.andover.coolpool.model.Ball;

public class BallTest {
	@Test
	public void setPocketedShouldNegateIsPocketed() {
		Ball b = new Ball(2);
		b.setPocketed();
		assertEquals("Failed to pocket unpocketed ball.", true, b.isPocketed());
	}

	@Test
	public void setPocketedShouldGivePocketedBallNoXVelocity() {
		Ball b = new Ball(2);
		b.setPocketed();
		double pocketedBallVelocity = 0.0;
		
		boolean check = b.getXVelocity() == pocketedBallVelocity;
		assertTrue("Failed to set X Velocity to 0.", check);
	}	
	
	@Test
	public void setPocketedShouldGivePocketedBallNoYVelocity() {
		Ball b = new Ball(2);
		b.setPocketed();
		double pocketedBallVelocity = 0.0;
		
		boolean check = b.getYVelocity() == pocketedBallVelocity;
		assertTrue("Failed to set Y Velocity to 0.", check);
	}
	
	@Test
	public void setPocketedShouldGivePocketedBall0XCenterPos() {
		Ball b = new Ball(2);
		b.setPocketed();
		double pocketedBallXPosition = 0.0;
		
		boolean check = b.getYVelocity() == pocketedBallXPosition;
		assertTrue("Failed to set center x position to 0.", check);
	}
	
	@Test
	public void setPocketedShouldGivePocketedBall0YCenterPos() {
		Ball b = new Ball(2);
		b.setPocketed();
		double pocketedBallYPosition = 0.0;
		
		boolean check = b.getCenterY() == pocketedBallYPosition;
		assertTrue("Failed to set center y position to 0.", check);
	}
	
	@Test
	public void unPocketedShouldNegateIsPocketed() {
		Ball b = new Ball(2);
		b.setPocketed();
		b.unpocket();
		boolean isPocketed = b.isPocketed();
		assertFalse("Failed to unpocket ball", isPocketed);
	}
}
