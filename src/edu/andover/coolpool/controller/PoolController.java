package edu.andover.coolpool.controller;

import edu.andover.coolpool.model.Ball;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;


public class PoolController {
	
	// handler for mouse click on the cue ball sets the speed of the cue
	// ball to 100 in/s horizontally
	
	public void addMouseEventHandler(Ball cueBall) {
	    /*Circle circle = (Circle) cueBall.getView();
	    circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		cueBall.setXVelocity(100);
	    	}
	    });*/
	}
}
