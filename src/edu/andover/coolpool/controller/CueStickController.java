package edu.andover.coolpool.controller;

import edu.andover.coolpool.model.CueStick;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

public class CueStickController {
	
	public void addMouseEventHandler(PoolBoardView pbv, CueStick cueStick) {
		Rectangle r = pbv.getRectangle();
	    r.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		cueStick.setCueStickLocation(me.getSceneX(), me.getSceneY());
	    	}
	    });
	}
}
