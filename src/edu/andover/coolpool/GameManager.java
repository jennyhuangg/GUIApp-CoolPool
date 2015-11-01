package edu.andover.coolpool;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class GameManager {
	private Scene scene;

	private BorderPane rootLayout;


	public GameManager(Scene scene){
	    this.scene = scene;

	    rootLayout = new BorderPane();

	    scene.setRoot(rootLayout);
	}


	public void initStartScreen(){}

	//controller for start screen calls this when button is pressed
	public void initPoolScreen(){}

	public void initEndScreen(){}
}
