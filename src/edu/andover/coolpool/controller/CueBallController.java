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
	// After scratch, when mouse is pressed, place ball in that area.
	public void addMousePressedEventHandler(PoolBoardView pbv, Ball cueBall) {
		Rectangle r = pbv.getScratchRectangle();
		Circle c = (Circle) pbv.getBallViews()[15].getCircle();
		c.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {	
				
				// If the ball isn't overlapping with other balls, then
				// place the cueball wherever the mouse is.
				// place the cue ball wherever the mouse is

				boolean isNotOverlapping = true;
				for (Ball b: pbv.getPoolBoard().getBalls()){
					double radiusSum = b.getRadius() + cueBall.getRadius();
					double deltaX = cueBall.getCenterX() - b.getCenterX();
					double deltaY = cueBall.getCenterY() - b.getCenterY();
					if (b != cueBall && (deltaX * deltaX + 
							deltaY * deltaY < radiusSum * radiusSum)){
						isNotOverlapping = false;
					}
				}
				
				if (isNotOverlapping){
					cueBall.setCenterX(mouseX);
					cueBall.setCenterY(mouseY);
					r.setOnMouseMoved(null);
					c.setOnMousePressed(null);
					r.toBack();
					pbv.getCueStickView().getLine().setVisible(true);
					pbv.setCueStickHandlers();
				}
			}
		});
	}

	// After scratch, cue ball follows hovering mouse position until mouse is
	// pressed and placed on the board.
	public void addMouseHoverEventHandler(PoolBoardView pbv, Ball cueBall) {
		pbv.getScratchRectangle().toFront();
		Rectangle r = pbv.getScratchRectangle();
		r.setFill(Color.ORANGE);
		pbv.bringElementsToFront();
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
