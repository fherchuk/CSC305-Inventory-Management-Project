package controller;

import java.io.IOException;
import java.sql.SQLException;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import dao.ControlDAO;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.TodoItems;
import utils.HelperMethods;

public class todoItemsController extends VBox {

	@FXML private TableView<TodoItems> tblTodoItems;
	@FXML private TableColumn<TodoItems, String> tblcolId, tblcolTitle, tblcolDescription, tblcolDueDate, tblcolPriority, tblcolItemState;
	@FXML private TableColumn<TodoItems, TodoItems> tblcolDelete;
	@FXML private VBox root;
	@FXML private JFXTextField txtSearch;

	public ObservableList<TodoItems> data = FXCollections.observableArrayList();
	public static TodoItems todoItemsDataHolder = new TodoItems();
	public static boolean edit = false;

	//Launches the todoItems.fxml view
	public todoItemsController() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/todoItems.fxml"));

		fxml.setRoot(this);
		fxml.setController(this);
		try {
			fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Runs automatically when List All Items is pressed
	@FXML
	public void initialize() throws Exception {
		search_tableview();
		populateTable();
	}

	//Method that iterates and searches throughout the table
	public void search_tableview() {
		txtSearch.textProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable o) {
				//if we dont press nothing, we have the full data
				if(txtSearch.textProperty().get().isEmpty()) {
					tblTodoItems.setItems(data);
					return;
				}

				ObservableList<TodoItems> tableItems = FXCollections.observableArrayList();
				ObservableList<TableColumn<TodoItems, ?>> cols = tblTodoItems.getColumns();

				//we search for the specific search text in every column
				for(int i=0; i<data.size(); i++) {
					for(int j=1; j<cols.size(); j++) {
						TableColumn<TodoItems, ?> col = cols.get(j);
						String cellValue = col.getCellData(data.get(i)).toString();
						cellValue = cellValue.toLowerCase();
						if(cellValue.contains(txtSearch.textProperty().get().toLowerCase())) {
							tableItems.add(data.get(i));
							break;
						}                        
					}
				}

				tblTodoItems.setItems(tableItems);
			}
		});
	}

	//Method that fills the cells of the table with information from the database
	private void populateTable() throws SQLException {
		//we always clear the table before populating data because data changes on add / edit / delete
		tblTodoItems.getItems().clear();
		//we populate it with the new data after the above actions
		data.addAll(ControlDAO.getControlDao().getTodoItemsDao().getAllItems());

		//we fill every column with it specific data
		tblcolId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tblcolTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		tblcolDescription.setCellValueFactory(new PropertyValueFactory<>("item_description"));
		tblcolDueDate.setCellValueFactory(new PropertyValueFactory<>("due_date"));
		tblcolPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
		tblcolItemState.setCellValueFactory(new PropertyValueFactory<>("item_state"));

		//since we need a delete button inside a table column cell we do that here
		tblcolDelete.setStyle("-fx-alignment:center;");
		tblcolDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolDelete.setCellFactory(param -> new TableCell<TodoItems, TodoItems>() {

			Button delete = new Button("");
			protected void updateItem(TodoItems i, boolean empty) {

				//if no data, this button will no be displayed
				if (i == null) {
					setGraphic(null);
					return;
				}

				setGraphic(delete);
				//we style this button using css class names which in this case is delete (it can be found in css/table_view.css using /images/delete_table.png image)
				HelperMethods.style_delete_button(delete);
				delete.setOnMouseClicked(event -> {
					//here we create the alert buttons , confirm and cancel and style them
					JFXAlert alert = new JFXAlert((Stage) tblTodoItems.getScene().getWindow());
					JFXButton cancel = new JFXButton("Cancel");
					cancel.setStyle("-fx-background-color: #B74636; -fx-text-fill: white;-fx-cursor: hand;");
					JFXButton confirm = new JFXButton("Confirm");
					confirm.setStyle("-fx-background-color: #06C49C; -fx-text-fill: white;-fx-cursor: hand;");
					HelperMethods.alert_delete(alert, confirm, cancel, false, "", i.getTitle());	
					confirm.setOnAction(e-> {
						try {
							//when pressing confirm we delete the item
							delete(i.getId());
						} catch (IOException e2) {
							e2.printStackTrace();
						} catch (SQLException e2) {
							e2.printStackTrace();
						}
						alert.close();
					}); 
					//if we pres cancel we close the alert with animation
					cancel.setOnAction( e1 -> {
						alert.close();
					});
					HelperMethods.refresh_focus_table(tblTodoItems);
				});
			}
		});

		tblTodoItems.setItems(data);
	}

	//Method that opens Add Item view when Add Item is pressed
	@FXML
	public void add() throws Exception {
		edit = false;
		new HelperMethods().openOpenEditScene(root, "todoItemsAdd", "add");
		populateTable();
	}

	//Method that opens Edit Item view when Edit Item is pressed
	@FXML
	public void edit() throws Exception {
		edit = true;
		if (tblTodoItems.getSelectionModel().getSelectedItem() != null) 
			getData();
		else
			HelperMethods.alert("Warning!", "Choose a record from table.", AlertType.WARNING);

	}

	//Method that uses the model to get and set data to the object and adds it to the to do item view
	private void getData() throws IOException, SQLException {
		TodoItems todoItems = tblTodoItems.getSelectionModel().getSelectedItem();

		//on edit, todoItemsDataHolder is an object of type TodoItems which holds the data from the specific clicked column on the table
		//and sets all the data to this object so it can be used to fill the textfields 
		todoItemsDataHolder.setTitle(todoItems.getTitle());
		todoItemsDataHolder.setDue_date(todoItems.getDue_date());
		todoItemsDataHolder.setPriority(todoItems.getPriority());
		todoItemsDataHolder.setId(todoItems.getId());
		todoItemsDataHolder.setItem_description(todoItems.getItem_description());
		todoItemsDataHolder.setItem_state(todoItems.getItem_state());

		//here , the view for adding/editing todoitems opens
		new HelperMethods().openOpenEditScene(root, "todoItemsAdd", "add");
		//after this view closes, the table is populated again to reflect the new added or edited data 
		populateTable();
	}

	//Method that deletes when delete button is pressed
	private void delete(int i) throws IOException, SQLException {
		//to delete an todoitem we need the id for that item, so we go and find that in the database using the method below 
		ControlDAO.getControlDao().getTodoItemsDao().deleteItem(i);
		//after deleting we populate table again to reflect the new data (without the item we deleted)
		populateTable();
	}

}
