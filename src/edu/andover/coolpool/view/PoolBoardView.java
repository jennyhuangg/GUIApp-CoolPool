package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PoolBoardView {
	private Pane view;
	double length;
	double width;
	
	// The Green
	private Rectangle rectangle;
	
	// The Rectangle that has the pockets.
	private Rectangle bigRectangle;
	
	// Invisible Rectangle that is used for the mouse hover handler.
	private Rectangle cueStickRectangle;
	
	// Invisible Rectangle that is used for the mouse hover handler for scratch
	// event.
	private Rectangle scratchRectangle;
	
	public PoolBoardView(double length, double width){
		view = new Pane();
		
		double xMargin = 50;
		double yMargin = 50;
		this.length = length*GameConstants.IN_TO_PIXEL;
		this.width = width*GameConstants.IN_TO_PIXEL;
		
		// Set up table
		Color brown = Color.web("0x3D362D");
		bigRectangle = new Rectangle(xMargin, yMargin, this.length, this.width);
		bigRectangle.setScaleX(1.09);//TODO: ERIC WHAT IS THIS
		bigRectangle.setScaleY(1.157); //TODO: WHAT ARE THOSEEEE
		bigRectangle.setFill(brown);
		view.getChildren().add(bigRectangle);
		
		// Set up green
		Color green = Color.web("0x27AE60");
		rectangle = new Rectangle(xMargin, yMargin, this.length, this.width);
		rectangle.setFill(green);
		view.getChildren().add(rectangle);
		
		// Set up CuestickRectangle.
		double addLength = 2000;
		double addWidth = 2000;
		cueStickRectangle = new Rectangle(0, 0, this.length + addLength, 
				this.width + addWidth);
		cueStickRectangle.setFill(Color.TRANSPARENT);
		view.getChildren().add(cueStickRectangle);
		
		// Set up ScratchRectangle.
		double ballRadius = GameConstants.BALL_RADIUS;
		scratchRectangle = new Rectangle(xMargin + ballRadius, 
				yMargin + ballRadius, this.length / 4.0, this.width);
		
	}
	
	public Pane getPane() {
		return view;
	}
	
	public Rectangle getRectangle() {
		return rectangle;
	}
	
	public Rectangle getBigRectangle() {
		return bigRectangle;
	}
	
	public Rectangle getCueStickRectangle() {
		return cueStickRectangle;
	}
	
	public Rectangle getScratchRectangle() {
		return scratchRectangle;
	}
}
