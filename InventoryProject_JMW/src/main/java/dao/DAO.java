package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// these are all the database variables we are going to need so we store them in one class, we dont have to write them in every database class 
public class DAO {

	protected Connection connector = dbConnection.instance().getConnection();
    protected ResultSet rs;
    protected PreparedStatement stm;
	
	public DAO() {
		
	}
}
