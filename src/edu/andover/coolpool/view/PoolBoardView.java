package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import edu.andover.coolpool.model.Ball;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class PoolBoardView {
	private Pane view;
	double length;
	double width;
	private Rectangle rectangle;
	
	public PoolBoardView(double length, double width){
		view = new Pane();
		this.length = length*GameConstants.CM_TO_PIXEL;
		this.width = width*GameConstants.CM_TO_PIXEL;
		rectangle = new Rectangle(0, 0, this.length, this.width);
		rectangle.setFill(Color.GREEN);
		view.getChildren().add(rectangle);
	}
	
	public Pane getPane(){
		return view;
	}
	
	public Rectangle getRectangle(){
		return rectangle;
	}
}
