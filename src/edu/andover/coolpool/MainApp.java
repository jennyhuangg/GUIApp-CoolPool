package edu.andover.coolpool;

/*
 * Project 2: Cool Pool
 * 
 * Amy Chou, Jenny Huang, Eric Lee, and Pranav Tadikonda
 * COMP-630: Software Design, Instructor: Dr. Miles
 * 17 November 2015
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

// This class is the runner of the program.


public class MainApp extends Application {
	private Scene scene;

	@Override
	public void start(Stage primaryStage){
		Pane root = new Pane();
		
		double width = GameConstants.SCREEN_WIDTH;
		double length = GameConstants.SCREEN_LENGTH;
		
		scene = new Scene(root, width, length);
		
		primaryStage.setTitle("Cool Pool");
		primaryStage.setWidth(width);
		primaryStage.setHeight(length);
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.show();

		GameManager poolGame = GameManager.getInstance(scene);

		poolGame.initStartScreen(scene);
	}

	public static void main(String[] args){
		launch(args);
	}
}
