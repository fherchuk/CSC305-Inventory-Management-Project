import java.sql.*;


public class mySQL {
    public static Connection connection;
    public static Statement statement;
    public static ResultSet resultSet;

    public mySQL(String fileName) throws RuntimeException {

        String url = "jdbc:sqlite:database/" + fileName;
        try {
            connection = DriverManager.getConnection(url);
            if (connection != null) {
                statement = connection.createStatement();
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public mySQL() throws RuntimeException {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory-manager", "root", "default");
            statement = connection.createStatement();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
