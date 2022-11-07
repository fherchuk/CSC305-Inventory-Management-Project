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
import model.Items;
import utils.HelperMethods;

public class itemsController extends VBox {

	@FXML private TableView<Items> tblItems;
	@FXML private TableColumn<Items, String> tblcolId, tblcolName, tblcolColor, tblcolSecondColor, tblcolBrand, tblcolSize, tblcolLocationName, tblcolLocationRack, tblcolLocationShelf;
	@FXML private TableColumn<Items, Items> tblcolDelete;
	@FXML private VBox root;
	@FXML private JFXTextField txtSearch;
	@FXML private JFXButton btnAdd, btnEdit;

	public ObservableList<Items> data = FXCollections.observableArrayList();
	public static Items itemsDataHolder = new Items();
	public static boolean edit = false;

	//Launches the items.fxml view
	public itemsController() {
		FXMLLoader fxml = new FXMLLoader(getClass().getResource("/fxml/items.fxml"));

		fxml.setRoot(this);
		fxml.setController(this);
		try {
			fxml.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//Runs automatically when View Items is pressed
	@FXML
	public void initialize() throws Exception {
		search_tableview();
		populateTable();
	}

	//Method that iterates and searches throughout the table
	public void search_tableview() {
		//while we add characters to the search text, we check if in our table we have records that match our search expression
		txtSearch.textProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable o) {
				//if we dont press nothing, we have the full data
				if(txtSearch.textProperty().get().isEmpty()) {
					tblItems.setItems(data);
					return;
				}

				ObservableList<Items> tableItems = FXCollections.observableArrayList();
				ObservableList<TableColumn<Items, ?>> cols = tblItems.getColumns();

				//we search for the specific search text in every column
				for(int i=0; i<data.size(); i++) {
					for(int j=1; j<cols.size(); j++) {
						TableColumn<Items, ?> col = cols.get(j);
						String cellValue = col.getCellData(data.get(i)).toString();
						cellValue = cellValue.toLowerCase();
						if(cellValue.contains(txtSearch.textProperty().get().toLowerCase())) {
							tableItems.add(data.get(i));
							break;
						}                        
					}
				}

				tblItems.setItems(tableItems);
			}
		});
	}

	//Method that fills the cells of the table with information from the database
	private void populateTable() throws SQLException {
		//we always clear the table before populating data because data changes on add / edit / delete
		tblItems.getItems().clear();
		//we populate it with the new data after the above actions
		data.addAll(ControlDAO.getControlDao().getItemsDao().getAllItems());

		//we fill every column with it specific data
		tblcolId.setCellValueFactory(new PropertyValueFactory<>("id"));
		tblcolName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		tblcolColor.setCellValueFactory(new PropertyValueFactory<>("itemColor"));
		tblcolSecondColor.setCellValueFactory(new PropertyValueFactory<>("secondaryItemColor"));
		tblcolBrand.setCellValueFactory(new PropertyValueFactory<>("itemBrand"));
		tblcolSize.setCellValueFactory(new PropertyValueFactory<>("size"));
		tblcolLocationName.setCellValueFactory(new PropertyValueFactory<>("location_name"));
		tblcolLocationRack.setCellValueFactory(new PropertyValueFactory<>("location_rack"));
		tblcolLocationShelf.setCellValueFactory(new PropertyValueFactory<>("location_shelf"));

		//since we need a delete button inside a table column cell we do that here
		tblcolDelete.setStyle("-fx-alignment:center;");
		tblcolDelete.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
		tblcolDelete.setCellFactory(param -> new TableCell<Items, Items>() {
			//we create this button
			Button delete = new Button("");
			protected void updateItem(Items i, boolean empty) {
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
					JFXAlert alert = new JFXAlert((Stage) tblItems.getScene().getWindow());
					JFXButton cancel = new JFXButton("Cancel");
					cancel.setStyle("-fx-background-color: #FB6605; -fx-text-fill: white;-fx-cursor: hand;");
					JFXButton confirm = new JFXButton("Confirm");
					confirm.setStyle("-fx-background-color: #41A5EE; -fx-text-fill: white;-fx-cursor: hand;");
					HelperMethods.alert_delete(alert, confirm, cancel, false, "");	
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
					HelperMethods.refresh_focus_table(tblItems);
				});
			}
		});

		tblItems.setItems(data);

	}

	//Method that opens the adding stage when add button is pressed
	@FXML
	public void add() throws Exception {
		edit = false;
		new HelperMethods().openOpenEditScene(root, "itemsAdd", "add");
		populateTable();
	}

	//Method that opens the stage when edit button is pressed
	@FXML
	public void edit() throws Exception {
		edit = true;
		if (tblItems.getSelectionModel().getSelectedItem() != null) 
			getData();
		else
			HelperMethods.alert("Warning!", "Choose a record from table.", AlertType.WARNING);
	}

	//Method that uses the model to get and set data to the object and adds it to the add item view
	private void getData() throws IOException, SQLException {
		Items i = tblItems.getSelectionModel().getSelectedItem();

		//on edit, itemsDataHolder is an object of type Items which holds the data from the specific clicked column on the table
		//and sets all the data to this object so it can be used to fill the textfields 
		itemsDataHolder.setId(i.getId());
		itemsDataHolder.setItemName(i.getItemName());
		itemsDataHolder.setItemColor(i.getItemColor());
		itemsDataHolder.setSecondaryItemColor(i.getSecondaryItemColor());
		itemsDataHolder.setItemBrand(i.getItemBrand());
		itemsDataHolder.setSize(i.getSize());
		itemsDataHolder.setLocation_name(i.getLocation_name());
		itemsDataHolder.setLocation_rack(i.getLocation_rack());
		itemsDataHolder.setLocation_shelf(i.getLocation_shelf());

		//here , the view for adding/editing items opens
		new HelperMethods().openOpenEditScene(root, "itemsAdd", "add");
		//after this view closes, the table is populated again to reflect the new added or edited data 
		populateTable();
	}

	//Method that deletes when delete button is pressed
	private void delete(int i) throws IOException, SQLException {
		//to delete an item we need the id for that item, so we go and find that in the database using the method below 
		ControlDAO.getControlDao().getItemsDao().deleteItem(i);
		//after deleting we populate table again to reflect the new data (without the item we deleted)
		populateTable();
	}

}
