package controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.TodoItems;
import utils.HelperMethods;

public class setCompletedController extends VBox {

	@FXML private TableView<TodoItems> tblTodoItems;
	@FXML private TableColumn<TodoItems, String> tblcolId, tblcolTitle, tblcolDueDate, tblcolPriority, tblcolItemState;
	@FXML private VBox root;
	@FXML private JFXTextField txtSearch;
	@FXML private TableColumn<TodoItems , TodoItems> tblcolComplete;

	public ObservableList<TodoItems> data = FXCollections.observableArrayList();
	public static TodoItems todoItemsDataHolder = new TodoItems();
	public static boolean edit = false;
	private String state = "Completed";
	private String stateLabelForAlert = "";

	//Launches the setCompleted.fxml view
	public setCompletedController() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/setCompleted.fxml"));

		fxml.setRoot(this);
		fxml.setController(this);
		try {
			fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@FXML
	public void initialize() throws Exception {
		populateTable();
	}

	//Method that fills table cells with data from the database
	private void populateTable() throws SQLException {
		//we always clear the table before populating data because data changes complete action button
		tblTodoItems.getItems().clear();
		data.addAll(ControlDAO.getControlDao().getTodoItemsDao().getAllItems());

		tblcolId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tblcolTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tblcolDueDate.setCellValueFactory(new PropertyValueFactory<>("due_date"));
		tblcolPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
		tblcolItemState.setCellValueFactory(new PropertyValueFactory<>("item_state"));

		//since we need a delete button inside a table column cell we do that here
		tblcolComplete.setStyle("-fx-alignment:center;");
		tblcolComplete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolComplete.setCellFactory(param -> new TableCell<TodoItems, TodoItems>() {

			//in order to make a todo item In Progress or Completed we add this button
			JFXButton btn_submit = new JFXButton("");
			protected void updateItem(TodoItems todo, boolean empty) {
				super.updateItem(todo, empty);

				//if no data, this button will no be displayed
				if (todo == null) {
					setGraphic(null);
					return;
				}
				setGraphic(btn_submit);

				btn_submit.setOnMouseClicked(event -> {
					//here we create the alert buttons , confirm and cancel and style them
					JFXAlert alert = new JFXAlert((Stage) btn_submit.getScene().getWindow());
					JFXButton cancel = new JFXButton("Cancel");
					JFXButton confirm = new JFXButton("Confirm");

					//if the item that you choose is in To Do state, we submit it and it will change to "In Progress"
					if(todo.getItem_state().equals("To Do")) {
						state = "In Progress";
						stateLabelForAlert = "Do you want to set " + todo.getTitle() + " in Progress";
					}
					//if already In Progress, we submit and mark it as Completed
					else if(todo.getItem_state().equals("In Progress")) {
						state = "Completed";
						stateLabelForAlert = "Do you want to set " + todo.getTitle() + " to Completed";
					}
					HelperMethods.alertSubmit(alert, stateLabelForAlert + " ?", confirm, cancel);
					cancel.setStyle("-fx-background-color: #B74636; -fx-text-fill: white;-fx-cursor: hand;");
					confirm.setStyle("-fx-background-color: #06C49C; -fx-text-fill: white;-fx-cursor: hand;");

					confirm.setOnAction(e-> {
						//here we change the state to In Progress / Completed based on the if condition above
						completeTodo(alert, todo.getId(), state);
					}); 
					cancel.setOnAction( e1 -> {
						alert.close();
					}); 
					HelperMethods.refresh_focus_table(tblTodoItems);
				});

				btn_submit.setMaxWidth(30);
				btn_submit.setCursor(Cursor.HAND);
				btn_submit.getStyleClass().add("btnSubmit");

			}
		});

		tblTodoItems.setItems(data);
	}

	//Method that updates the state to completed
	public void completeTodo(JFXAlert alert, int id, String state) {
		try {
			alert.hideWithAnimation();
			TodoItems t = new TodoItems();
			t.setItem_state(state);
			t.setId(id);
			//we need the state and the id of the item
			//we need the state to know what will be the item's following state
			//and we need the it to know which item is
			ControlDAO.getControlDao().getTodoItemsDao().updateItemState(t);
			//after updating we populate table again to reflect the new data
			populateTable();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}


}
