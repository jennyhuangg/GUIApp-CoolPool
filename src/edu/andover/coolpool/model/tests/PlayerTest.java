package edu.andover.coolpool.model.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.andover.coolpool.model.Player;

public class PlayerTest {

	
	@Test
	public void addPointShouldIncrementPoints(){
		Player player = new Player();
		player.addPoint();
		assertEquals(player.getPoints(), 1);
		player.addPoint();
		assertEquals(player.getPoints(), 2);
	}
	
	@Test
	public void canPocketEightBallShouldReturnTrueWhenAllBallsIn(){
		Player player = new Player();
		assertFalse(player.canPocketEightBall());
		for (int i = 0; i < 6; i ++){
			player.addPoint();
			assertFalse(player.canPocketEightBall());
		}
		player.addPoint();
		assertTrue(player.canPocketEightBall());
	}
}
