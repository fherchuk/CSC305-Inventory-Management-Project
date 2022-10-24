import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class mySQL {
    public static Connection connection;
    public static Statement statement;
    public ResultSet resultSet;

    public mySQL() throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory-manager", "root", "default");
            statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}

;
