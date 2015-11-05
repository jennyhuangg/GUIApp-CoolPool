package edu.andover.coolpool.model;

//This class is not used in Version 0, but will be implemented in the final.
public class PlayerTurn {
	private PoolBoard poolboard;
	
	//transfers the velocity/direction of the cue stick to the cue ball.
	public PlayerTurn(CueStick cueStick, PoolBoard poolboard) {
		this.poolboard = poolboard;	
	}

}
