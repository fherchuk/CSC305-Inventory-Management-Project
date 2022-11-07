package dao;

//a helper class to process all dao (data access object) classes, its easier to read and faster to work with other database classes
public class ControlDAO {

	private static ControlDAO dao = new ControlDAO();
	private static itemsDao items_dao = new itemsDao();
	
	public static ControlDAO getControlDao() {
		return dao;
	}
	
	//here we keep a reference to itemsDao class so we can use it whenever we want
	public itemsDao getItemsDao() {
		return items_dao;
	}
	
	
}
