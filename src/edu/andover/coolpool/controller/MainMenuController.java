package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

//This class is the controller for the MainMenu.

public class MainMenuController {
	@FXML Button btn_Go;
	
	@FXML
	public void handleButtonAction() {
		GameManager gm = GameManager.getInstance((btn_Go.getScene()));
		gm.initPoolScreen();
	}
}
