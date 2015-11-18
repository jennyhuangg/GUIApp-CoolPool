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
		assertTrue("X Velocity is not 0.", check);
	}	
	
	@Test
	public void setPocketedShouldGivePocketedBallNoYVelocity() {
		Ball b = new Ball(2);
		b.setPocketed();
		double pocketedBallVelocity = 0.0;
		
		boolean check = b.getYVelocity() == pocketedBallVelocity;
		assertTrue("Y Velocity is not 0.", check);
	}
	
	// TODO: setPocketed centerX should be 0
	// TODO: setPocketed centerX should be 0
	// TODO: unpocketed should negate isPocketed.
}
