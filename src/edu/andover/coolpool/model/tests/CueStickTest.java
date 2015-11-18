package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.model.CueStick;

public class CueStickTest {

	@Test
	public void setInitialValuesShouldSetCorrectDistanceInit() {
		Ball b = new Ball(2);
		CueStick cueStick = new CueStick(b);
		double cueStickLength = cueStick.getCueStickLength();
		cueStick.setStartX(0);
		cueStick.setStartY(0);
		cueStick.setEndX(cueStickLength);
		cueStick.setEndY(0);
		
		cueStick.setInitialValues(50.0, 50.0);
		
		double expectedInitDistance = 50.0;
		
		boolean check = cueStick.getDistanceInit() == expectedInitDistance;
		assertTrue("Failed to set correct initial distance.", check);
	}

	@Test
	public void setHoverCueStickLocationShouldSetCorrectLocation() {
		
	}
	
	// TODO: setInitValues sets correct distanceInit
	// TODO: setLocation methods (3)
	// TODO: UpdateCueBallVelocity updates cue ball velocity correctly.
}
