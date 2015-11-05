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
	
	private Ball cueBall;

	public void addMouseEventHandler(Ball cueBall) {
	    this.cueBall = cueBall;
	    Circle circle = new Circle();
	    circle.setCenterX(cueBall.getCenterX());
	    circle.setCenterY(cueBall.getCenterY());
	    circle.setRadius(cueBall.getRadius());
	    circle.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		cueBall.setYVelocity(-100);
	    	}
	    });
	}
}
