package utils;

import controller.itemsController;
import javafx.scene.layout.VBox;

public class Controllers {

	//here we keep all the controllers in one place, this is for clean code and easier access later on 
	private static itemsController itemsController;
	
	public static void getListItems(VBox box) {
		itemsController = new itemsController() ;
		config(box, itemsController);
	}
	
	public static void config(VBox box, VBox content) {
		box.getChildren().clear();
		box.getChildren().add(content);
	}

	
}
