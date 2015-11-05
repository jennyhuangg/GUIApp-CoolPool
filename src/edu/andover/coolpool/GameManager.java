package edu.andover.coolpool;

import java.io.IOException;

import edu.andover.coolpool.model.PoolBoard;
import edu.andover.coolpool.view.PoolBoardView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameManager {
	private Scene scene;

	private BorderPane rootLayout;


	public GameManager(Scene scene){
	    this.scene = scene;

	    rootLayout = new BorderPane();

	    scene.setRoot(rootLayout);
	}


	public void initStartScreen(Scene scene){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				GameManager.class.getResource("view/MainMenuScreen.fxml"));
		try {
			Parent mainMenuScreen = (Parent) loader.load();
			rootLayout.setCenter(mainMenuScreen);
			scene.setRoot(rootLayout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//controller for start screen calls this when button is pressed
	public void initPoolScreen(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				GameManager.class.getResource("view/PoolScreen.fxml"));
		try {
			
			Parent poolScreen = (Parent) loader.load();
			PoolBoard poolBoard = new PoolBoard();

			rootLayout.setCenter(poolScreen);
			Pane pane = (Pane) poolScreen.getChildrenUnmodifiable().get(1);
			
			PoolBoardView poolBoardView = poolBoard.getView();
			
			pane.getChildren().add(poolBoardView.getPane());
			scene.setRoot(rootLayout);
			
			poolBoard.animate();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public void initEndScreen(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				GameManager.class.getResource("view/EndScreen.fxml"));
		try {
			Parent mainMenuScreen = (Parent) loader.load();
			rootLayout.setCenter(mainMenuScreen);
			scene.setRoot(rootLayout);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
