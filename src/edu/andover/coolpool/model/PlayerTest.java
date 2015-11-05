package edu.andover.coolpool.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void setCanPocketEightBallShouldMakeCanPocketEightBallTrue() {

		Player p = new Player();

		p.setCanPocketEightBall();

		assertEquals("canPocketEightBall() should return true", p.canPocketEightBall(), true);
	}
	
	@Test
	public void addToPocketedBallsShouldAddBall() {
		Player p = new Player();
		Ball b = new Ball(50, 50, 3);
		p.addToPocketedBalls(b);
		ArrayList<Ball> pocketedBalls = p.getPocketedBalls();
		boolean hasBall = false;
		for(int i = 0; i < pocketedBalls.size(); i++) {
			if(pocketedBalls.size() > 0)
				hasBall = true;
		}
		assertEquals("Should have added Ball to pocketedBalls.", hasBall, true);
	}

}
