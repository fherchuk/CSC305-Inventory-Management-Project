package controller;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import model.TodoItems;
import utils.HelperMethods;

public class todoItemsAddEditController implements Initializable {

	@FXML private JFXTextField txtTitle,txtEstimate;
	@FXML private JFXTextArea txtAreaDesc;
	@FXML private JFXButton btnCancel;
	@FXML private Label lblError;
	@FXML private JFXComboBox<String> cmbPriority;
	@FXML private JFXDatePicker dueDate;
	
	private ObservableList<String> priorities;
	
	private int todoItemId = 0;
	
	//This code is executed each time that the user presses Add Item or Edit Item Button
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//this method makes the user press only integers on txtEstimate
		HelperMethods.make_jfxtextfield_integer(txtEstimate);
		priorities = FXCollections.observableArrayList();
		priorities.addAll("Low","Medium","High");
		cmbPriority.getItems().addAll(priorities);
		
		//if we press the Edit button, we have an id, we need that to know which item to edit
		if(todoItemsController.edit == true) {
			todoItemId = todoItemsController.todoItemsDataHolder.getId();
			getData(todoItemsController.todoItemsDataHolder);
		}
		else
			// if we are adding, we dont need the id, so we set that to 0, and sql will automatically increase that for us
			todoItemId = 0;
	}
	
	private void getData(TodoItems t) {
		//we get the data from the record on the table we want to edit
		txtTitle.setText(t.getTitle());
		txtEstimate.setText(t.getEstimate_hours()+"");
		txtAreaDesc.setText(t.getItem_description());
		cmbPriority.setValue(t.getPriority());
		dueDate.setValue(t.getDue_date().toLocalDate());
		
	}
	
	//This method saves the data added by the user to the database in both cases (for add button to add and edit button to update)
	@FXML
	public void save() throws Exception {
		//here we check for empty text
		if(HelperMethods.checkEmptyCombobox(cmbPriority) || HelperMethods.checkEmptyText(txtTitle.getText())) {
			lblError.setText("Please fill out the * fields!*");
			return;
		}
		
		//we create and TodoItems object and fill that with the data from textfields
		TodoItems t = new TodoItems();
		t.setId(todoItemId);
		t.setTitle(txtTitle.getText());
		t.setDue_date(Date.valueOf(dueDate.getValue()));
		t.setItem_description(txtAreaDesc.getText());
		t.setPriority(cmbPriority.getValue().toString());
		
		if(HelperMethods.checkEmptyText(txtEstimate.getText()))
			t.setEstimate_hours(0);
		else
			t.setEstimate_hours(Integer.parseInt(txtEstimate.getText()));
		
		//if id is 0 it means that we are adding
		if(todoItemId == 0) 
			ControlDAO.getControlDao().getTodoItemsDao().insertTodoItem(t);
		else
			ControlDAO.getControlDao().getTodoItemsDao().updateTodoItem(t);
		
		//after adding / editing we close the stage and return to the table
		HelperMethods.closeStage(btnCancel);
		
	}
	
	//Closes the current stage
	@FXML
	private void cancel() {
		HelperMethods.closeStage(btnCancel);
	}

}
