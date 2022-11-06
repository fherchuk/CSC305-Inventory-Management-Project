package controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import model.TodoItems;

public class inProgressController extends VBox {

	@FXML private TableView<TodoItems> tblTodoItems;
	@FXML private TableColumn<TodoItems, String> tblcolId, tblcolTitle, tblcolDescription, tblcolDueDate, tblcolPriority, tblcolItemState;
	@FXML private VBox root;
	@FXML private JFXTextField txtSearch;

	public ObservableList<TodoItems> data = FXCollections.observableArrayList();
	public static TodoItems todoItemsDataHolder = new TodoItems();
	public static boolean edit = false;

	//Launches the inProgress.fxml view
	public inProgressController() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/inProgress.fxml"));

		fxml.setRoot(this);
		fxml.setController(this);
		try {
			fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Runs automatically when Check Items In Progress is pressed
	@FXML
	public void initialize() throws Exception {
		populateTable();
	}

	//Method where table cells and filled with information
	private void populateTable() throws SQLException {
		//we always clear the table before populating data 
		tblTodoItems.getItems().clear();
		//we populate this table only with the todo items that are "In progress"
		data.addAll(ControlDAO.getControlDao().getTodoItemsDao().getAllItems("In Progress"));

		tblcolId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tblcolTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tblcolDescription.setCellValueFactory(new PropertyValueFactory<>("item_description"));
		tblcolDueDate.setCellValueFactory(new PropertyValueFactory<>("due_date"));
		tblcolPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
		tblcolItemState.setCellValueFactory(new PropertyValueFactory<>("item_state"));
		
		tblTodoItems.setItems(data);
	}

}
