package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.CueStick;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

public class CueStickController {

	public void addMouseEventHandler(PoolBoardView pbv, CueStick cueStick) {
	    Rectangle r = pbv.getCueStickRectangle();
		r.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		double mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
	    		double mouseY = me.getY()*GameConstants.PIXEL_TO_IN;
	    		cueStick.setCueStickLocation(mouseX, mouseY);
	    	}
	    });
	}
}
