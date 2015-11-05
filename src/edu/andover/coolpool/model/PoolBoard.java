package edu.andover.coolpool.model;

import static java.lang.Math.sqrt;
import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.controller.PoolController;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.animation.AnimationTimer;

public class PoolBoard {

	private Ball[] balls; //16 balls
	private boolean isPaused;
	private Pocket[] pockets;
	private double length;
	private double width;
	
	private PoolController poolController = new PoolController();
	private PoolBoardView poolBoardView;

	private double boardX; //height of board
	private double boardY; //width of board

	AnimationTimer timer;
	
	private int mode = 0;

	public PoolBoard() {
		length = 92; //Inches
		width = 46; //Inches
		isPaused = false;

		timer = new AnimationTimer() {
			@Override
			public void handle(long timestamp) {
				update();
			}
		};
		setView();

		poolBoardView.getBigRectangle().getX();
		boardX = poolBoardView.getRectangle().getX() *
				GameConstants.PIXEL_TO_IN;
		boardY = poolBoardView.getRectangle().getY() *
				GameConstants.PIXEL_TO_IN;
		
		setUpBalls();

		for (Ball ball: balls){
			poolBoardView.getPane().getChildren().add(ball.getView());
		}
		
		pockets = new Pocket[6];
		for (int i = 0; i < 6; i ++) {pockets[i] = new Pocket(i, 
				this.length, this.width, boardX, boardY); }
	}

	public void setUpBalls() {
		double centerY = width / 2 + boardY;
		double incrementX = 2.25 * Math.cos(30) * GameConstants.IN_TO_PIXEL;
		double radius = 1.125;
		
		// Creates balls on table.
		balls = new Ball[16];
		for (int i = 0; i < 16; i ++) {
			balls[i] = new Ball(110 + i * 2, 110 + i * 2, i);
		}
		
		// Place balls in triangle formation.
		balls[1].setYVelocity(-100);
		balls[0] = new Ball(length * 3/4 + boardX, centerY, 1);

		balls[1] = new Ball(length * 3/4 + boardX + 1 * incrementX, 
				centerY + radius, 1);
		balls[2] = new Ball(length * 3/4 + boardX + 1 * incrementX, 
				centerY - radius, 2);
		
		balls[3] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY + 2 * radius, 2);
		balls[4] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY, 4); //8 Ball
		balls[5] = new Ball(length * 3/4 + boardX + 2 * incrementX, 
				centerY - 2 * radius, 1);

		balls[6] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY + 3 * radius, 1);
		balls[7] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY + radius, 2);
		balls[8] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY - radius, 1);
		balls[9] = new Ball(length * 3/4 + boardX + 3 * incrementX, 
				centerY - 3 * radius, 2);

		balls[10] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY + 4 * radius, 2);
		balls[11] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY + 2 * radius, 1);
		balls[12] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY, 1);
		balls[13] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY - 2 * radius, 2);
		balls[14] = new Ball(length * 3/4 + boardX + 4 * incrementX, 
				centerY - 4 * radius, 1);

		// Places cue ball in correct spot.
		balls[15] = new Ball(length * 1/4 + boardX, width / 2 + boardY, 3);
		
		// Allows mouse click to move cue ball.
		poolController.addMouseEventHandler(balls[15]);
	}

	public void checkPockets(){
		for (Pocket pocket: pockets){
			for (Ball ball: balls){
				double distance = Math.sqrt(Math.pow(pocket.getXPosition() -
						ball.getCenterX(), 2) + 
						Math.pow(pocket.getYPosition() - ball.getCenterY(), 2));
				if(distance <= Math.abs(pocket.getRadius() - ball.getRadius())
						&& !ball.getIsPocketed()){
					ball.setPocketed();
				}
			}
		}
	}

	public void update() {
		double elapsedSeconds = 0.01;
		for (Ball b : balls) {
			b.setCenterX(b.getCenterX() + elapsedSeconds * b.getXVelocity());
			b.setCenterY(b.getCenterY() + elapsedSeconds * b.getYVelocity());
		}
		checkCollisions();
		checkPockets();
		decelerateBalls();
		if (stable() && mode != 0) { 
			timer.stop();
			mode = (mode+1)%2;
		}
	}

	public void animate() {
		timer.start();
	}
	
	// Returns true if no balls are moving.
	public boolean stable(){
		boolean isStable = true;
		for (Ball ball: balls){
			if (ball.getXVelocity() != 0 || ball.getYVelocity() != 0) {
				isStable = false;
			}
		}
		return isStable;
	}

	public void checkCollisions() {
		for (Ball ball: balls)
		{
			// Changes velocity when ball collides with vertical edges.
			if ((ball.getCenterX() - ball.getRadius() <= 
					boardX && ball.getXVelocity() < 0) 
					|| (ball.getCenterX() + ball.getRadius() >= 
					length + boardX && ball.getXVelocity() > 0)) {
				ball.setXVelocity(ball.getXVelocity()*(-1));
			}
			// Changes velocity when ball collides with horizontal edges.
			if ((ball.getCenterY() - ball.getRadius() <= 
					boardY && ball.getYVelocity() < 0)
					|| (ball.getCenterY() + ball.getRadius() >= 
					width + boardY & ball.getYVelocity() > 0)) {
				ball.setYVelocity(ball.getYVelocity()*(-1));
			}
			
			// Changes velocity when ball collides with other balls.
			for (Ball b2: balls){
				final double deltaX = b2.getCenterX() - ball.getCenterX() ;
				final double deltaY = b2.getCenterY() - ball.getCenterY() ;
				if (colliding(ball, b2, deltaX, deltaY)) {
					bounce(ball, b2, deltaX, deltaY);
				}
			}
		}
	}
	
	
    public boolean colliding(final Ball b1, final Ball b2, final double deltaX, 
    							final double deltaY) {
        // Balls are colliding if (x2-x1)^2 + (y2-y1)^2 < (r1 + r2)^2
        // and if distance between them is decreasing.
        final double radiusSum = b1.getRadius() + b2.getRadius();
        if (deltaX * deltaX + deltaY * deltaY <= radiusSum * radiusSum) {
            if ( deltaX * (b2.getXVelocity() - b1.getXVelocity()) 
                    + deltaY * (b2.getYVelocity() - b1.getYVelocity()) < 0) {
                return true;
            }
        }
        return false;
    }
    
    private void bounce(final Ball b1, final Ball b2, final double deltaX, 
    					final double deltaY) {
    	// Direction of collision is <deltaX, deltaY>.
    
        double distance = sqrt(deltaX * deltaX + deltaY * deltaY);
        
        //Collision vector is <unitContactX, unitContactY>.
        double unitContactX = deltaX / distance; 
        double unitContactY = deltaY / distance;
        
        double b1_i = b1.getXVelocity()*unitContactX + b1.getYVelocity()*
        														unitContactY;
        double b2_i = b2.getXVelocity()*unitContactX + b2.getYVelocity()*
        														unitContactY;
        
        double b1_f = b2_i;
        double b2_f = b1_i;
        
        b1.setXVelocity(b1.getXVelocity() +  unitContactX*(b1_f - b1_i));
        b1.setYVelocity(b1.getYVelocity() +  unitContactY*(b1_f - b1_i));
        b2.setXVelocity(b2.getXVelocity() +  unitContactX*(b2_f - b2_i));
        b2.setYVelocity(b2.getYVelocity() +  unitContactY*(b2_f - b2_i));
        
    }

	public void decelerateBalls(){
		double elapsedSeconds = 0.1;

		for (Ball ball: balls){
			double xVel = ball.getXVelocity();
			double yVel = ball.getYVelocity();
			double speed = Math.sqrt(Math.pow(xVel, 2) + Math.pow(yVel, 2));
			if (xVel != 0 || yVel != 0){
				{
					if (xVel < 0){
						ball.setXVelocity(Math.min(xVel - 
								2*elapsedSeconds*xVel/speed, 0));
					}
					if (yVel < 0){
						ball.setYVelocity(Math.min(yVel - 
								2*elapsedSeconds*yVel/speed, 0));
					}
					if (xVel > 0){
						ball.setXVelocity(Math.max(xVel - 
								2*elapsedSeconds*xVel/speed, 0));
					}
					if (yVel > 0){
						ball.setYVelocity(Math.max(yVel - 
								2*elapsedSeconds*yVel/speed, 0));
					}
				}
			}
		}
	}

	public void pauseGame() {
		timer.stop();
	}

	public double getLength(){
		return length;
	}

	public double getWidth(){
		return width;
	}

	public void setView(){
		poolBoardView = new PoolBoardView(length, width);

		poolBoardView.getRectangle().setX(180);
		poolBoardView.getRectangle().setY(177);

		poolBoardView.getBigRectangle().setX(180);
		poolBoardView.getBigRectangle().setY(177);
	}

	public PoolBoardView getView(){
		return poolBoardView;
	}
}