package edu.andover.coolpool.controller;

import edu.andover.coolpool.GameManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {
	@FXML Button btn_Go;
	
	@FXML
	public void handleButtonAction() {
		GameManager gm = new GameManager(btn_Go.getScene());
		gm.initPoolScreen();
	}
}
