package edu.andover.coolpool;

import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.view.BallView;

public class BallTest {
	public static void main(String[] args){
		Ball ball = new Ball(0, 0, 0);
		BallView ballView = new BallView(ball);
		ball.addObserver(ballView);
		
		ball.setCenterX(10);
	}
}
