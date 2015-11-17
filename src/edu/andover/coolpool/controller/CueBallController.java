package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class CueBallController {
	private boolean isMousePressed = false;
	private double mouseX;
	private double mouseY;

	// EH abbreviation means Event Handler.
	public void addMousePressedEventHandler(PoolBoardView pbv, Ball cueBall) {
		Rectangle r = pbv.getScratchRectangle();
		r.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				cueBall.setCenterX(mouseX);
				cueBall.setCenterY(mouseY);
			}
		});
	}

	public void addMouseHoverEventHandler(PoolBoardView pbv, Ball cueBall) {
		pbv.getScratchRectangle().toFront();
		Rectangle r = pbv.getScratchRectangle();

		r.setOnMouseMoved(new EventHandler<MouseEvent>() {		
			@Override
			public void handle(MouseEvent me) {
				if (!isMousePressed) {
					System.out.println("Hi");
					mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
					mouseY = me.getY()*GameConstants.PIXEL_TO_IN;
					cueBall.setCenterX(mouseX);
					cueBall.setCenterY(mouseY);
				}
			}
		});
	}
}
