package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PoolBoardView {
	private Pane view;
	double length;
	double width;
	private Rectangle rectangle;
	private Rectangle bigRectangle;
	private Rectangle cueStickRectangle;
	
	public PoolBoardView(double length, double width){
		view = new Pane();
		
		double xMargin = 50;
		double yMargin = 50;
		this.length = length*GameConstants.IN_TO_PIXEL;
		this.width = width*GameConstants.IN_TO_PIXEL;
		
		Color brown = Color.web("0x3D362D");
		bigRectangle = new Rectangle(xMargin, yMargin, this.length, this.width);
		bigRectangle.setScaleX(1.09);//ERIC WHAT IS THIS
		bigRectangle.setScaleY(1.157); //WHAT ARE THOSEEEE
		bigRectangle.setFill(brown);
		view.getChildren().add(bigRectangle);
		
		rectangle = new Rectangle(xMargin, yMargin, this.length, this.width);
		
		Color green = Color.web("0x27AE60");

		rectangle.setFill(green);
		view.getChildren().add(rectangle);
		
		double addLength = 2000;
		double addWidth = 2000;
		cueStickRectangle = new Rectangle(0,0, this.length + addLength, 
				this.width + addWidth);
		cueStickRectangle.setFill(Color.TRANSPARENT);
		view.getChildren().add(cueStickRectangle);
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
}
