package edu.andover.coolpool.model;

import static org.junit.Assert.*;
import org.junit.Test;

public class BallTest {
	@Test

	public void setPocketedShouldNegateIsPocketed() {

		Ball b = new Ball(50.0, 50.0, 3);

		b.setPocketed();

		assertEquals("Failed to pocket unpocketed ball.", true, b.getIsPocketed());
	}

	@Test
	public void setPocketedShouldGivePocketedBallNoVelocity() {

		Ball b = new Ball(50.0, 50.0, 3);

		b.setPocketed();

		assertEquals("X Velocity is not 0", b.getXVelocity(), 0);

		assertEquals("Y Velocity is not 0", b.getYVelocity(), 0);
	}

}
