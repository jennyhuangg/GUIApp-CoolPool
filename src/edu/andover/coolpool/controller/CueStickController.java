package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameConstants;
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
	    		System.out.println(cueStick.getCueBall().getCenterX());
	    		double mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
	    		double mouseY = me.getY()*GameConstants.PIXEL_TO_IN;
	    		System.out.println(mouseX);
	    		cueStick.setCueStickLocation(mouseX, mouseY);
	    		System.out.println(cueStick.getStartX());
	    	}
	    });
	}
}
