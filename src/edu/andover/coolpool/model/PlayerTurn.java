package edu.andover.coolpool.model;

public class PlayerTurn {

	private PoolBoard poolboard;
	//transfers the velocity/direction of the cue stick to the cue ball.
	public PlayerTurn(CueStick cueStick, PoolBoard poolboard) {
		this.poolboard = poolboard;
		
	}

}
