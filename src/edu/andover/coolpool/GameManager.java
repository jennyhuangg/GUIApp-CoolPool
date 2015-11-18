package edu.andover.coolpool;

import java.io.IOException;

import edu.andover.coolpool.model.EndScreenStatus;
import edu.andover.coolpool.model.PoolBoard;
import edu.andover.coolpool.model.PoolGame;
import edu.andover.coolpool.model.PoolGameStatus;
import edu.andover.coolpool.view.EndScreenStatusView;
import edu.andover.coolpool.view.GameSounds;
import edu.andover.coolpool.view.PoolBoardView;
import edu.andover.coolpool.view.PoolGameStatusView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;

// The "manager" that is in charge of changing scenes within the program..
public class GameManager {
	private Scene scene;
	private BorderPane rootLayout;
	private PoolGame poolGame;

	private static GameManager instance = null;
	
	public static GameManager getInstance(Scene scene) {
		if(instance == null) {
			instance = new GameManager(scene);
		}
		
		return instance;
	}
	
	public static GameManager getInstance() {
		return instance;
	}
	
	private GameManager(Scene scene){
	    this.scene = scene;

	    rootLayout = new BorderPane();

	    scene.setRoot(rootLayout);
	}

	// Initializes the start screen.
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

	// The controller for start screen calls this when button is pressed.
	// Initializes the sidebar of the pool game and the view of the
	// pool board.
	public void initPoolScreen(){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				GameManager.class.getResource("view/PoolScreen.fxml"));
		try {
			Parent poolScreen = (Parent) loader.load();
			PoolGameStatusView poolGameStatusView = loader.getController();
			
			poolGame = new PoolGame();
			PoolBoard poolBoard = poolGame.getPoolBoard();
			PoolBoardView poolBoardView = new PoolBoardView(poolBoard);
			poolBoard.addObserver(poolBoardView);
			
			PoolGameStatus poolGameStatus = poolGame.getPoolGameStatus();
			poolGameStatusView.setObservable(poolGameStatus);
			poolGameStatus.addObserver(poolGameStatusView);
			
			rootLayout.setCenter(poolScreen);
			Pane pane = (Pane) poolScreen.getChildrenUnmodifiable().get(1);
			
			
			pane.getChildren().add(poolBoardView.getPane());		

			scene.setRoot(rootLayout);
			
			GameSounds.MUSIC.cycleCountProperty().set(AudioClip.INDEFINITE);
			GameSounds.MUSIC.play();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Initializes end screen.
	public void initEndScreen() {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(
				GameManager.class.getResource("view/EndScreen.fxml"));
		try {
			GameSounds.MUSIC.stop();
			
			Parent endScreen = (Parent) loader.load();
			EndScreenStatusView endScreenStatusView = loader.getController();
			EndScreenStatus endScreenStatus = poolGame.getEndScreenStatus();
			
			endScreenStatusView.setObservable(endScreenStatus);
			endScreenStatus.addObserver(endScreenStatusView);
			
			rootLayout.setCenter(endScreen);
			scene.setRoot(rootLayout);
		
			GameSounds.YAY.play();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
