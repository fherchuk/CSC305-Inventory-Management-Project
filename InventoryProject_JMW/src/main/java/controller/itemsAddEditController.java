package controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.Items;
import utils.HelperMethods;

public class itemsAddEditController implements Initializable {

	@FXML private JFXTextField txtItemName, txtItemColor, txtSecondaryItemColor, txtItemBrand, txtItemSize, txtLocationName, txtLocationRack, txtLocationShelf;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	
	private int itemId = 0;
	
	//This code is executed each time that the user presses Add Item or Edit Item Button
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//if we press the Edit button, we have an id, we need that to know which item to edit
		if(itemsController.edit == true) {
			itemId = itemsController.itemsDataHolder.getId();
			getData(itemsController.itemsDataHolder);
		}
		else
			// if we are adding, we dont need the id, so we set that to 0, and sql will automatically increase that for us
			itemId = 0;
	}
	
	private void getData(Items t) {
		//we get the data from the record on the table we want to edit
		txtItemName.setText(t.getItemName());
		txtItemColor.setText(t.getItemColor());
		txtSecondaryItemColor.setText(t.getSecondaryItemColor());
		txtItemBrand.setText(t.getItemBrand());
		txtItemSize.setText(t.getSize());
		txtLocationName.setText(t.getLocation_name());
		txtLocationRack.setText(t.getLocation_rack());
		txtLocationShelf.setText(t.getLocation_shelf());
	}
	
	//This method saves the data added by the user to the database in both cases (for add button to add and edit button to update)
	@FXML
	public void save() throws Exception {
		//here we check for empty text
		if(HelperMethods.checkEmptyText(txtItemName.getText(), txtItemColor.getText(), 
				txtItemBrand.getText(), txtItemSize.getText(), txtLocationName.getText())) {
			lblError.setText("Please fill out the * fields!*");
			return;
		}
		
		if(!HelperMethods.checkEmptyText(txtLocationRack.getText()) && !HelperMethods.checkEmptyText(txtLocationName.getText()) && txtLocationShelf.getText().isEmpty()) {
			lblError.setText("Please fill out the shelf");
			return;
		}
		
		if(!HelperMethods.checkEmptyText(txtLocationShelf.getText()) && !HelperMethods.checkEmptyText(txtLocationName.getText()) && txtLocationRack.getText().isEmpty() ) {
			lblError.setText("Please fill out the rack");
			return;
		}
		
		//we create and Items object and fill that with the data from textfields
		Items t = new Items();
		t.setId(itemId);
		t.setItemName(txtItemName.getText());
		t.setItemColor(txtItemColor.getText());
		t.setSecondaryItemColor(txtSecondaryItemColor.getText());
		t.setItemBrand(txtItemBrand.getText());
		t.setSize(txtItemSize.getText());
		t.setLocation_name(txtLocationName.getText());
		t.setLocation_rack(txtLocationRack.getText());
		t.setLocation_shelf(txtLocationShelf.getText());
		
		//if id is 0 it means that we are adding
		if(itemId == 0)
			ControlDAO.getControlDao().getItemsDao().insertItem(t);
		else
			ControlDAO.getControlDao().getItemsDao().updateItem(t);
		
		//after adding / editing we close the stage and return to the table
		HelperMethods.closeStage(btnCancel);
		
	}
	
	//Closes the current stage
	@FXML
	private void cancel() {
		HelperMethods.closeStage(btnCancel);
	}

}
