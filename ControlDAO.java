package dao;


public class ControlDAO {

	private static ControlDAO dao = new ControlDAO();
	private static todoItemsDao todoItems_dao = new todoItemsDao();
	
	public static ControlDAO getControlDao() {
		return dao;
	}
	
	public todoItemsDao getTodoItemsDao() {
		return todoItems_dao;
	}
	
}
