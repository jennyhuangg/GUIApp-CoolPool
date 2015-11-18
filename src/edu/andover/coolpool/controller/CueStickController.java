package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.CueStick;
import edu.andover.coolpool.view.CueStickView;
import edu.andover.coolpool.view.GameSounds;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

public class CueStickController {

	// Position of mouse does not change when mouse is pressed.
	private double mouseX;
	private double mouseY;
	
	private boolean isMousePressed = false;
	private boolean hasJustDragged = false;
	
	private CueStickView cueStickView;
	private CueStick cueStick;
	
	public CueStickController(CueStickView cueStickView){
		this.cueStickView = cueStickView;
		this.cueStick = cueStickView.getCueStick();
	}
	
	// EH = Event Handler
	public void addMouseHoverEH(PoolBoardView pbv) {
	    // Adds event handler to the view's rectangle
		Rectangle r = pbv.getCueStickRectangle();
		r.setOnMouseMoved(new EventHandler<MouseEvent>() {
			@Override
	    	public void handle(MouseEvent me) {
				if (!isMousePressed && cueStick.canMove()) {
					mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
					mouseY = me.getY()*GameConstants.PIXEL_TO_IN;
					cueStick.setHoverCueStickLocation(mouseX, mouseY);
				}
	    	}
	    });
	}

	public void addMousePressedEH(PoolBoardView pbv) {
		Line l = (Line) cueStickView.getLine();
		l.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
	    			isMousePressed = true;
	    		}
	    	}
	    });
		Rectangle r = pbv.getCueStickRectangle();
		r.setOnMousePressed(new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
		    		isMousePressed = true;
	    		}
	    	}
	    });
	}
	
	public void addMouseDraggedEH(PoolBoardView pbv) {
	    Line l = (Line) cueStickView.getLine();
		l.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
		    		// Sets values for initial mouse click.
	    			double initMouseX = mouseX;
		    		double initMouseY = mouseY;
		    		cueStick.setInitialValues(initMouseX, initMouseY);
		    		
		    		// Sets new cue stick location based on mouse location.
		    		double endMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double endMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
	    			cueStick.setCueStickLocationOnDrag(endMouseX, endMouseY);
		    		
	    			hasJustDragged = true;
		    	}
		    }
	    });
		Rectangle r = pbv.getCueStickRectangle();
		r.setOnMouseDragged(new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
		    		// Sets values for initial mouse click.
	    			double initMouseX = mouseX;
		    		double initMouseY = mouseY;
		    		cueStick.setInitialValues(initMouseX, initMouseY);
		    		
		    		// Sets new cue stick location based on mouse location.
		    		double endMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double endMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
	    			cueStick.setCueStickLocationOnDrag(endMouseX, endMouseY);
		    		
	    			hasJustDragged = true;
		    	}
	    	}
	    });
	}
	
	public void addMouseReleasedEH(PoolBoardView pbv) {
	  Line l = (Line) cueStickView.getLine();
		l.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		// Only occurs when mouse is dragged, not randomly clicked.
	    		if (hasJustDragged) {
		    		double finalMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double finalMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
		    		
		    		// Implement collision.
		    		cueStick.setCueStickLocationAfterHit();
		    		cueStick.updateCueBallVelocity(finalMouseX, finalMouseY);
		    		
		    		cueStick.setCanMove(false);
		    		hasJustDragged = false;
	    		}
	    		isMousePressed = false;
	    	}
	    });
		Rectangle r = pbv.getCueStickRectangle();
		r.setOnMouseReleased(new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		// Only occurs when mouse was dragged, not randomly clicked.
	    		if (hasJustDragged) {
		    		double finalMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double finalMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
		    		
		    		// Implement collision.
		    		GameSounds.CUE_HITTING_BALL.play();
		    		cueStick.setCueStickLocationAfterHit();
		    		cueStick.updateCueBallVelocity(finalMouseX, finalMouseY);
		    		
		    		cueStick.setCanMove(false);
		    		hasJustDragged = false;
	    		}
	    		isMousePressed = false;
	    	}
	    });
	}

	
}
