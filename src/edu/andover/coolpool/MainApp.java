package edu.andover.coolpool;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

	private Stage primaryStage;
	private Scene scene;

	@Override
	public void start(Stage primaryStage){
		BorderPane root = new BorderPane();

		scene = new Scene(root, 1280, 780);
		primaryStage.setScene(scene);
		primaryStage.show();

		GameManager poolGame = new GameManager(scene);

		poolGame.initStartScreen(scene);

	}

	public static void main(String[] args){
		launch(args);
	}
}
