package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert.AlertType;
import model.TodoItems;
import utils.HelperMethods;

public class todoItemsDao extends DAO {

	public todoItemsDao() {
		super();
	}

	//Method that reads from the database, adds them on a list and returns the list
	public List<TodoItems> getAllItems() throws SQLException {
		List<TodoItems> data = new ArrayList<TodoItems>();
		String query = "select * from organizer.todo_items";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			TodoItems t = new TodoItems();
			t.setId(rs.getInt("id"));
			t.setTitle(rs.getString("title"));
			t.setItem_description(rs.getString("item_description"));
			t.setDue_date(rs.getDate("due_date"));
			t.setPriority(rs.getString("priority"));
			t.setItem_state(rs.getString("item_state"));
	
			data.add(t);
		}
		return data;
	}
	
	//Method that reads from the database where item_state servers as a parameter, adds them on a list and returns the list
	public List<TodoItems> getAllItems(String item_state) throws SQLException {
		List<TodoItems> data = new ArrayList<TodoItems>();
		String query = "select * from organizer.todo_items where item_state = '"+item_state+"' " ;
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query); 

		while(rs.next()) {
			TodoItems t = new TodoItems();
			t.setId(rs.getInt("id"));
			t.setTitle(rs.getString("title"));
			t.setItem_description(rs.getString("item_description"));
			t.setDue_date(rs.getDate("due_date"));
			t.setPriority(rs.getString("priority"));
			t.setItem_state(rs.getString("item_state"));
	
			data.add(t);
		}
		return data;
	}

	//Method that inserts a new row in the database
	public void insertTodoItem(TodoItems todoItems) throws SQLException {
		if(this.checkitem(todoItems.getTitle())) {
			HelperMethods.alert("Warning!", "This TODO Item exists!", AlertType.WARNING);
			return;
		}
		
		String insert = "insert into organizer.todo_items(title,item_description,due_date,priority,estimate_hours) values(?,?,?,?,?)";
		stm = connector.prepareStatement(insert);

		stm.setString(1,todoItems.getTitle());
		stm.setString(2,todoItems.getItem_description());
		stm.setDate(3, todoItems.getDue_date());
		stm.setString(4, todoItems.getPriority());
		stm.setInt(5, todoItems.getEstimate_hours());
		
		stm.executeUpdate();
		stm.close();
	}
	
	//Method that checks if an item with this title already exists in the database and returns a boolean
	public boolean checkitem(String title) throws SQLException {
		String query = "select * from organizer.todo_items where title = '"+title+"'";
		stm = connector.prepareStatement(query);
		rs = stm.executeQuery(query);
		if(rs.next() == true)
			return true;
		else 
			return false;
	}
	
	//Method that updates a row in the database
	public void updateTodoItem(TodoItems todoItems) throws SQLException {
		String update = "update organizer.todo_items set title = ?, item_description = ?, due_date = ?, priority = ?, estimate_hours = ? where id = ?";
		stm = connector.prepareStatement(update);

		stm.setString(1,todoItems.getTitle());
		stm.setString(2,todoItems.getItem_description());
		stm.setDate(3, todoItems.getDue_date());
		stm.setString(4, todoItems.getPriority());
		stm.setInt(5, todoItems.getEstimate_hours());
		
		stm.setInt(6, todoItems.getId());
		
		stm.executeUpdate();
		stm.close();
	}

	//Method that updates the state of a row in the database
	public void updateItemState(TodoItems t) throws SQLException {
		String update = "update organizer.todo_items set item_state = ?  where id = ?";
		stm = connector.prepareStatement(update);

		stm.setString(1, t.getItem_state());
		stm.setInt(2, t.getId());

		stm.executeUpdate();
		stm.close();

	}
	
	//Method that deletes a row in the database
	public void deleteItem(int id) throws SQLException{
		String query = "DELETE FROM organizer.todo_items where id=?";
		stm = connector.prepareStatement(query);
		stm.setInt(1, id);

		stm.execute();
		stm.close();

	}
}
