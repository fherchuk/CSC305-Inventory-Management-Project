package controller;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;
import utils.Controllers;

public class dashboardController implements Initializable {
	
	@FXML
	private VBox mainVbox;

	//we initialize the list items, which opens /fxml/items.fxml
	public void initialize(URL location, ResourceBundle resources) {
		Controllers.getListItems(mainVbox);
	}
	
	//This method quits the program
	@FXML
	public void quit() {
		Platform.exit();
	}
}
