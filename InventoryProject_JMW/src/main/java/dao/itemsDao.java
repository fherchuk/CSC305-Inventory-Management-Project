package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import model.Items;
import utils.HelperMethods;

public class itemsDao extends DAO {

	public itemsDao() {
		super();
	}

	//Method that reads from the database, adds them on a list and returns the list
	public List<Items> getAllItems() throws SQLException {
		List<Items> data = new ArrayList<Items>();
		String query = "select id,itemName,itemColor,itemBrand,secondaryItemColor,"
				+ "size,location_name,location_rack,location_shelf from inventory.items";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			Items i = new Items();
			i.setId(rs.getInt(1));
			i.setItemName(rs.getString(2));
			i.setItemColor(rs.getString(3));
			i.setItemBrand(rs.getString(4));
			i.setSecondaryItemColor(rs.getString(5));
			i.setSize(rs.getString(6));
			i.setLocation_name(rs.getString(7));
			i.setLocation_rack(rs.getString(8));
			i.setLocation_shelf(rs.getString(9));
			
			data.add(i);
		}
		return data;
	}
	
	

	//Method that inserts a new row in the database
	public void insertItem(Items items) throws SQLException {
		
		if(this.checkitem(items.getItemName())) {
			HelperMethods.alert("Warning!", "Item exists!", AlertType.WARNING);
			return;
		}
		
		String insert = "insert into inventory.items(itemName, itemColor, "
				+ "secondaryItemColor, itemBrand, size, location_name, location_rack, location_shelf) values(?,?,?,?,?,?,?,?)";
		
		stm = connector.prepareStatement(insert);

		stm.setString(1, items.getItemName());
		stm.setString(2, items.getItemColor());
		stm.setString(3, items.getSecondaryItemColor());
		stm.setString(4, items.getItemBrand());
		stm.setString(5, items.getSize());
		stm.setString(6, items.getLocation_name());
		stm.setString(7, items.getLocation_rack());
		stm.setString(8, items.getLocation_shelf());
		
		stm.executeUpdate();
		stm.close();
	}
	
	//Method that checks if an item with this name already exists in the database and returns true or false 
	public boolean checkitem(String itemName) throws SQLException {
		String query = "select * from inventory.items where itemName = '"+itemName+"'";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		if(rs.next() == true)
			return true;
		else 
			return false;
	}

	//Method that updates a row in the database
	public void updateItem(Items items) throws SQLException {
		String update = "update inventory.items set itemName = ?, itemColor = ?,"
				+ " secondaryItemColor = ?, itemBrand = ?, size = ?, location_name = ?, location_rack = ?, location_shelf = ? where id = ?";
		stm = connector.prepareStatement(update);

		stm.setString(1, items.getItemName());
		stm.setString(2, items.getItemColor());
		stm.setString(3, items.getSecondaryItemColor());
		stm.setString(4, items.getItemBrand());
		stm.setString(5, items.getSize());
		stm.setString(6, items.getLocation_name());
		stm.setString(7, items.getLocation_rack());
		stm.setString(8, items.getLocation_shelf());
		
		stm.setInt(9, items.getId());
		
		stm.executeUpdate();
		stm.close();
	}
	
	//Method that deletes a row in the database
	public void deleteItem(int id) throws SQLException{
		String query = "DELETE FROM inventory.items where id=?";
		stm = connector.prepareStatement(query);
		stm.setInt(1, id);

		stm.execute();
		stm.close();

	}

}
