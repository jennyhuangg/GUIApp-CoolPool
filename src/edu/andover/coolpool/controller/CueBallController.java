package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class CueBallController {
	private boolean isMousePressed = false;
	private double mouseX;
	private double mouseY;

	// EH abbreviation means Event Handler.
	public void addMousePressedEventHandler(PoolBoardView pbv, Ball cueBall) {
		Rectangle r = pbv.getScratchRectangle();
		Circle c = (Circle) pbv.getBallViews()[15].getCircle();
		c.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {	
				
				// If the ball isn't overlapping with other balls, then
				// place the cueball wherever the mouse is.
				for (Ball b: pbv.getPoolBoard().getBalls()){
					if (!pbv.getPoolBoard().colliding(cueBall, b, 0, 0)){
						cueBall.setCenterX(mouseX);
						cueBall.setCenterY(mouseY);
						r.setOnMouseMoved(null);
						c.setOnMousePressed(null);
						r.toBack();
						pbv.getCueStickView().getLine().setVisible(true);
						pbv.setCueStickHandlers();
					}
				}
			}
		});
	}

	public void addMouseHoverEventHandler(PoolBoardView pbv, Ball cueBall) {
		pbv.getScratchRectangle().toFront();
		Rectangle r = pbv.getScratchRectangle();
		r.setFill(Color.ORANGE);
		pbv.bringBallsToFront();
		pbv.removeCueStickHandlers();
		pbv.getCueStickView().getLine().setVisible(false);
		
		r.setOnMouseMoved(new EventHandler<MouseEvent>() {		
			@Override
			public void handle(MouseEvent me) {
				if (!isMousePressed) {
					mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
					mouseY = me.getY()*GameConstants.PIXEL_TO_IN;
					cueBall.setCenterX(mouseX);
					cueBall.setCenterY(mouseY);
				}
			}
		});
	}
}
