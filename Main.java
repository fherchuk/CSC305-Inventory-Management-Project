package main;
	
import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	public Parent root;
	
    @Override
    public void start(Stage primaryStage) throws IOException {       
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
    	root=(Parent)loader.load();
    	
    	Scene scene = new Scene(root);
    	primaryStage.setResizable(true);
    	primaryStage.setScene(scene);
    	primaryStage.setTitle("Dashboard");
    	//primaryStage.getIcons().add(new Image("/images/logo.png"));
    	primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}