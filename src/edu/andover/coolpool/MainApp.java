package edu.andover.coolpool;

/*
 * Proj 2: Version 0
 * 
 * Amy Chou, Jenny Huang, and Eric Lee
 * COMP-630: Software Design, Instructor: Dr. Miles
 * 5 October 2015
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage){
		Pane root = new Pane();
		
		int width = 1280;
		int length = 730;
		
		scene = new Scene(root, width, length);
		
		primaryStage.setTitle("Cool Pool");
		primaryStage.setWidth(width);
		primaryStage.setHeight(length);
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
