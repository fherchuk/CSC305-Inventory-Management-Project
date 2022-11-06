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

	//we initialize the list items, which opens /fxml/todoItems.fxml
	public void initialize(URL location, ResourceBundle resources) {
		Controllers.getListItems(mainVbox);
	}

	@FXML
	public void listItems() {
		Controllers.getListItems(mainVbox);
	}

	//here we open /fxml/setCompleted.fxml
	@FXML
	public void setCompleted() {
		Controllers.getCompleted(mainVbox);
	}
	
	//here we open /fxml/inProgress.fxml
	@FXML
	public void checkItems() {
		Controllers.getCheckItems(mainVbox);
	}
	
	//This method quits the program
	@FXML
	public void quit() {
		Platform.exit();
	}
}
