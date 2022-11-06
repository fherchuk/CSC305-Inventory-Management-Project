package utils;

import controller.inProgressController;
import controller.setCompletedController;
import controller.todoItemsController;
import javafx.scene.layout.VBox;

public class Controllers {

	//here we keep all the controllers in one place, this is for clean code and easier access later on 
	private static todoItemsController todoItemsController;
	private static setCompletedController setCompletedController;
	private static inProgressController inProgressController;
	
	public static void getListItems(VBox box) {
		todoItemsController = new todoItemsController() ;
		config(box, todoItemsController);
	}
	
	public static void getCompleted(VBox box) {
		setCompletedController = new setCompletedController() ;
		config(box, setCompletedController);
	}
	
	public static void getCheckItems(VBox box) {
		inProgressController = new inProgressController() ;
		config(box, inProgressController);
	}
	
	public static void config(VBox box, VBox content) {
		box.getChildren().clear();
		box.getChildren().add(content);
	}
	
}
