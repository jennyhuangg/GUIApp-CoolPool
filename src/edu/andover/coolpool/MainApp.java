package edu.andover.coolpool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage){
		Pane root = new Pane();
	
		scene = new Scene(root);
		
		primaryStage.setTitle("Cool Pool");
		primaryStage.setWidth(1280);
		primaryStage.setHeight(746);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		GameManager poolGame = new GameManager(scene);

		poolGame.initStartScreen(scene);
	}

	public static void main(String[] args){
		launch(args);
	}
}
