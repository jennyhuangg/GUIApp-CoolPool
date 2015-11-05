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
	
	public PoolBoardView(double length, double width){
		view = new Pane();
		
		this.length = length*GameConstants.IN_TO_PIXEL;
		this.width = width*GameConstants.IN_TO_PIXEL;
		
		bigRectangle = new Rectangle(0, 0, this.length, this.width);
		bigRectangle.setScaleX(1.07);
		bigRectangle.setScaleY(1.107);
		bigRectangle.setFill(Color.BURLYWOOD);
		view.getChildren().add(bigRectangle);
		
		rectangle = new Rectangle(0, 0, this.length, this.width);
		rectangle.setFill(Color.GREEN);
		view.getChildren().add(rectangle);
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
}
