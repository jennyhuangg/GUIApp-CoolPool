package edu.andover.coolpool.controller;

import edu.andover.coolpool.model.Ball;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class PoolController {

	public void addMouseEventHandler(Ball cueBall) {
	    Circle circle = (Circle) cueBall.getView();
	    circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		cueBall.setXVelocity(100);
	    	}
	    });
	}
}
