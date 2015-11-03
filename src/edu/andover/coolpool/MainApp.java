package edu.andover.coolpool;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;

	@Override
	public void start(Stage primaryStage){
		BorderPane root = new BorderPane();

		Scene scene = new Scene(root);
		primaryStage.setScene(scene);
		primaryStage.show();

		GameManager poolGame = new GameManager(scene);
		
		poolGame.initStartScreen();
	}

	public static void main(String[] args){
		launch(args);
	}
}
