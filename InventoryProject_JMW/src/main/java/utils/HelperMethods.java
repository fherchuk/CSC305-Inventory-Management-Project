package utils;

import java.io.IOException;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class HelperMethods {

	//Method that closes the stage where the button used as a parameter is
	public static void closeStage(JFXButton button) {
		Stage stage = (Stage) button.getScene().getWindow();
		stage.close();
	}

	//Methotd that opens a new scene by specifying the view name and icon of the view
	public void openOpenEditScene(VBox vbox,String view_name, String icon) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/"+view_name+".fxml")); 
		Parent root=(Parent)loader.load();
		Scene scene = new Scene(root);
		scene.setFill(Color.TRANSPARENT);
		Stage stage = new Stage();
		stage.initStyle(StageStyle.TRANSPARENT); 
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
	}

	//Method that creates an alert and waits for confirm or cancel action
	public static void alertSubmit(JFXAlert alert, String entity, JFXButton confirm, JFXButton cancel) {
		alert.initModality(Modality.APPLICATION_MODAL);
        alert.setOverlayClose(false);
        JFXDialogLayout layout = new JFXDialogLayout();
        ImageView img = new ImageView(new Image("/images/check.png"));
        layout.setHeading(img);
        layout.setBody(new Label(entity));
        layout.setActions(confirm,cancel);
        alert.setContent(layout);
        alert.show();
   }

	
	//Method that displays an alert message
	public static void alert(String title, String text, AlertType type) {
		Alert alert = new Alert (type,text);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.showAndWait();
	}
	
	//Method that checks for empty text
	public static boolean checkEmptyText(String... strings) {
		for(String s : strings)
			if(s == null || s.isEmpty())
				return true;

		return false;
	}

	//Method that refreshes the table where the focus is
	public static void refresh_focus_table(TableView<?> tbl) {
		tbl.refresh();
		tbl.requestFocus();
	}
	
	//Method that styles the delete button
	public static void style_delete_button(Button btn_delete) {
		btn_delete.setMaxWidth(20);
		btn_delete.setCursor(Cursor.HAND);
		btn_delete.getStyleClass().add("delete");
	}
	
	//Method that alerts once more before deleting
	public static void alert_delete(JFXAlert alert,Button confirm,JFXButton cancel, boolean overlay, String imagePath) {
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setOverlayClose(overlay);
		JFXDialogLayout layout = new JFXDialogLayout();
		
		if(overlay == false) {
			layout.setBody(new Label("Are you sure you want to delete?"));
			layout.setActions(confirm,cancel);
		}
		else {
			ImageView img = new ImageView(new Image(imagePath));
			img.setFitWidth(700);
			img.setFitHeight(700);
			layout.setBody(img);
		}
		alert.setContent(layout);
		alert.show();
	}

}
