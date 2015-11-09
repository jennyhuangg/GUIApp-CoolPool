package edu.andover.coolpool.controller;

import edu.andover.coolpool.model.CueStick;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class MouseController {
	
	public void addMouseEventHandler(Pane rl, CueStick cueStick) {
	    rl.getScene().setOnMouseMoved(new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		cueStick.setCueStickLocation(me.getX(), me.getY());
	    	}
	    });
	}
}
