package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class dbConnection {

	private static dbConnection instance = new dbConnection();
	private Connection conn;
	
	private dbConnection() {
		try {
			//this is just java syntax for database connections, our job is only to specify the database name, user and password
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","root");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException ex) {
			System.out.println("Not connected to database");
		}
	}
	public static dbConnection instance() {
        if (instance == null) {
        	instance = new dbConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }
	
	
}

