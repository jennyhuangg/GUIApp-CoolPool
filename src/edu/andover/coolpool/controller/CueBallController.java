package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.Ball;
import edu.andover.coolpool.model.CueStick;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class CueBallController {
	private boolean isMousePressed;
	private double mouseX;
	private double mouseY;
	
	public void addMouseHoverEventHandler(PoolBoardView pbv, CueStick cueStick, Ball cueBall) {
	    Rectangle r = pbv.getRectangle();
		r.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
			@Override
	    	public void handle(MouseEvent me) {
				if (!isMousePressed) {
	    		mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
	    		mouseY = me.getY()*GameConstants.PIXEL_TO_IN;
	    		cueStick.setHoverCueStickLocation(mouseX, mouseY);
				}
	    	}
	    });
	}
}
