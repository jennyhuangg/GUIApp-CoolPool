package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.model.CueStick;

public class CueStickTest {

	@Test
	public void setInitialValuesShouldSetCorrectDistanceInit() {
		int ballID = 2;
		Ball b = new Ball(ballID);
		CueStick cueStick = new CueStick(b);
		double cueStickLength = cueStick.getCueStickLength();
		double distanceTipFromCueBall = cueStick.getDistanceTipFromCueBall();
		cueStick.setStartX(distanceTipFromCueBall);
		cueStick.setStartY(0);
		cueStick.setEndX(distanceTipFromCueBall + cueStickLength);
		cueStick.setEndY(0);
		
		double initMouseX = 50.0;
		double initMouseY = 50.0;
		cueStick.setInitialValues(initMouseX, initMouseY);
		
		double expectedInitDistance = 47.0;
		boolean check = cueStick.getDistanceInit() == expectedInitDistance;
		assertTrue("Failed to set correct initial distance.", check);
	}
	
	@Test
	public void updateCueBallVelocityShouldCorrectlyUpdateXVelocity() {
		int ballID = 2;
		Ball b = new Ball(ballID);
		CueStick cueStick = new CueStick(b);
		double cueStickLength = cueStick.getCueStickLength();
		double distanceTipFromCueBall = cueStick.getDistanceTipFromCueBall();
		cueStick.setStartX(distanceTipFromCueBall);
		cueStick.setStartY(0);
		cueStick.setEndX(distanceTipFromCueBall + cueStickLength);
		cueStick.setEndY(0);
		
		double initMouseX = 50.0;
		double initMouseY = 50.0;
		cueStick.setInitialValues(initMouseX, initMouseY);
		
		double finalMouseX = 60.0;
		double finalMouseY = 60.0;
		cueStick.updateCueBallVelocity(finalMouseX, finalMouseY);
		
		double expectedCueBallXVelocityAfterHit = - 90.0;
		boolean check = b.getXVelocity() == expectedCueBallXVelocityAfterHit;
		assertTrue("Failed to set correct cue ball x velocity.", check);
	} 
	
	@Test
	public void updateCueBallVelocityShouldCorrectlyUpdateYVelocity() {
		int ballID = 2;
		Ball b = new Ball(ballID);
		CueStick cueStick = new CueStick(b);
		double cueStickLength = cueStick.getCueStickLength();
		double distanceTipFromCueBall = cueStick.getDistanceTipFromCueBall();
		cueStick.setStartX(distanceTipFromCueBall);
		cueStick.setStartY(0);
		cueStick.setEndX(distanceTipFromCueBall + cueStickLength);
		cueStick.setEndY(0);
		
		double initMouseX = 50.0;
		double initMouseY = 50.0;
		cueStick.setInitialValues(initMouseX, initMouseY);
		
		double finalMouseX = 60.0;
		double finalMouseY = 60.0;
		cueStick.updateCueBallVelocity(finalMouseX, finalMouseY);
		
		double expectedCueBallYVelocityAfterHit = 0.0;
		boolean check = b.getYVelocity() == expectedCueBallYVelocityAfterHit;
		assertTrue("Failed to set correct cue ball x velocity.", check);
	} 
}
