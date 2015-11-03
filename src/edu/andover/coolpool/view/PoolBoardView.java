package edu.andover.coolpool.view;

import edu.andover.coolpool.GameConstants;
import javafx.scene.layout.BorderPane;
import javafx.scene.shape.Rectangle;

public class PoolBoardView {
	public BorderPane view;
	double length;
	double width;
	
	public PoolBoardView(double length, double width){
		view = new BorderPane();
		this.length = length*GameConstants.CM_TO_PIXEL;
		this.width = length*GameConstants.CM_TO_PIXEL;
		Rectangle rect = new Rectangle(this.length, this.width);
		view.setCenter(rect);
	}
	
	public BorderPane getPane(){
		return view;
	}
}
