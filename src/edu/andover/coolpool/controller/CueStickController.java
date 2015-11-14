package edu.andover.coolpool.controller;


import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.CueStick;
import edu.andover.coolpool.view.GameSounds;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;

// TODO: Comments and Refactoring.
public class CueStickController {

	private double mouseX;
	private double mouseY;
	private boolean isMousePressed = false;
	private boolean hasJustDragged = false;
	
	public void addMouseHoverEventHandler(PoolBoardView pbv, CueStick cueStick) {
	    Rectangle r = pbv.getCueStickRectangle();
		r.addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
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

	public void addMousePressedEventHandler(PoolBoardView pbv, CueStick cueStick) {
	    Line l = (Line) cueStick.getView();
		l.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
	    			isMousePressed = true;
	    			/*mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
	    			mouseY = me.getY()*GameConstants.PIXEL_TO_IN;	    		
	    			cueStick.setHoverCueStickLocation(mouseX, mouseY); //not hover*/
	    		}
	    	}
	    });
		Rectangle r = pbv.getCueStickRectangle();
		r.addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
		    		isMousePressed = true;
		    		/*mouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		mouseY = me.getY()*GameConstants.PIXEL_TO_IN;	    		
	    			cueStick.setHoverCueStickLocation(mouseX, mouseY);*/
	    		}
	    	}
	    });
	}
	
	public void addMouseDraggedEventHandler(PoolBoardView pbv, CueStick cueStick) {
	    Line l = (Line) cueStick.getView();
		l.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
		    		isMousePressed = false;
		    		double initMouseX = mouseX;
		    		double initMouseY = mouseY;
		    		double endMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double endMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
		    		cueStick.setInitialValues(initMouseX, initMouseY);
		    		
		    		double distance = cueStick.getDistanceInitToMouse(endMouseX,
		    				endMouseY);
	    			cueStick.setCueStickLocationOnDrag(endMouseX, endMouseY);
	    			
	    			// Set color of cue stick. 
	    			int k = (int) (1900/distance);
	    			if ( k > 255) { k = 255; }
	    			cueStick.getView().setStroke(Color.rgb((int)(140+.45*k),k, 0));
		    		
	    			hasJustDragged = true;
		    	}
		    }
	    });
		Rectangle r = pbv.getCueStickRectangle();
		r.addEventHandler(MouseEvent.MOUSE_DRAGGED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (cueStick.canMove()) {
		    		isMousePressed = false;
		    		double initMouseX = mouseX;
		    		double initMouseY = mouseY;
		    		double endMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double endMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
		    		cueStick.setInitialValues(initMouseX, initMouseY);
		    		
		    		double distance = cueStick.getDistanceInitToMouse(endMouseX,
		    				endMouseY);
	    			cueStick.setCueStickLocationOnDrag(endMouseX, endMouseY);
	    			
	    			// Set color of cue stick.
	    			int k = (int) (1900/distance);
	    			if ( k > 255) { k = 255; }
	    			cueStick.getView().setStroke(Color.rgb((int)(140+.45*k),k, 0));
		    		
	    			hasJustDragged = true;
	    		}
	    	}
	    });
		
	}
	public void addMouseReleasedEventHandler(PoolBoardView pbv, CueStick cueStick) {
	  Line l = (Line) cueStick.getView();
		l.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (hasJustDragged) {
		    		double finalMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double finalMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
		    		
		    		GameSounds.CUE_HITTING_BALL.play();
		    		cueStick.setCueStickLocationAfterHit();
		    		cueStick.updateCueBallVelocity(finalMouseX, finalMouseY);
		    		cueStick.getView().setStroke(Color.BROWN);
		    		
		    		cueStick.setCanMove(false);
		    		hasJustDragged = false;
	    		}
	    		isMousePressed = false;
	    	}
	    });
		Rectangle r = pbv.getCueStickRectangle();
		r.addEventHandler(MouseEvent.MOUSE_RELEASED, new EventHandler<MouseEvent>() {
	    	@Override
	    	public void handle(MouseEvent me) {
	    		if (hasJustDragged) {
		    		double finalMouseX = me.getX()*GameConstants.PIXEL_TO_IN;
		    		double finalMouseY = me.getY()*GameConstants.PIXEL_TO_IN;
		    		
		    		GameSounds.CUE_HITTING_BALL.play();
		    		cueStick.setCueStickLocationAfterHit();
		    		cueStick.updateCueBallVelocity(finalMouseX, finalMouseY);
		    		cueStick.getView().setStroke(Color.BROWN);
		    		
		    		cueStick.setCanMove(false);
		    		hasJustDragged = false;
	    		}
	    		isMousePressed = false;
	    	}
	    });
	}
}
