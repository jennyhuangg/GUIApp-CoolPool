package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;
import org.junit.Test;

import edu.andover.coolpool.model.Ball;

public class BallTest {
	@Test
	public void setPocketedShouldNegateIsPocketed() {
		Ball b = new Ball(2);
		b.setPocketed();
		assertEquals("Failed to pocket unpocketed ball.", true, b.getIsPocketed());
	}

	@Test
	public void setPocketedShouldGivePocketedBallNoXVelocity() {
		Ball b = new Ball(2);
		b.setPocketed();
		double pocketedBallVelocity = 0.0;
		System.out.println(b.getXVelocity());
		assertSame("X Velocity is not 0.", pocketedBallVelocity, b.getXVelocity());
	}	
	
	@Test
	public void setPocketedShouldGivePocketedBallNoYVelocity() {
		Ball b = new Ball(2);
		b.setPocketed();
		double pocketedBallVelocity = 0.0;
		assertSame("Y Velocity is not 0.", pocketedBallVelocity, b.getYVelocity());
	}
	
	// TODO: setPocketed centerX should be 0
	// TODO: setPocketed centerX should be 0
}
