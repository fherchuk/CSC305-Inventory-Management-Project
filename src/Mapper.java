import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

public class Mapper {
    // Mapper class for communicating between JAVA program and SQL Database.
    // Functions Execute SQL Queries.
    public mySQL SQL;


    public Mapper(mySQL SQL) {
        this.SQL = SQL;
    }

    public void makeSQLTable(Table table) throws SQLException {
        String tableStr = "CREATE TABLE " + table.name + table.createTableStatement();
        System.out.println(tableStr);
        PreparedStatement stmt = mySQL.connection.prepareStatement(tableStr);
        stmt.executeUpdate();
    }

    public void insertStatement(Table table, List<String> attributes) throws SQLException {
        String insertStr = "INSERT INTO " +table.name+" "+ table.insertValues() + "VALUES" + table.insertVariables();
        System.out.println(insertStr);
        PreparedStatement stmt = mySQL.connection.prepareStatement(insertStr);
        int index = 1;
        for (String i : attributes) {
            if (Objects.equals(i, "")) {
                stmt.setString(index, null);
            } else {
                stmt.setString(index, i);
            }
            index += 1;
        }
        stmt.executeUpdate();

    }

    public void dropTable(String tableName) throws SQLException {
        String dropStr = "DROP TABLE " + tableName + ";";
        PreparedStatement stmt = mySQL.connection.prepareStatement(dropStr);
        stmt.executeUpdate();
    }

    public void makePK(Table table) throws SQLException {
        String alter = "ALTER TABLE "+table.name+ " ADD PRIMARY KEY "+"("+table.primary_key.getName()+");";
        System.out.println(alter);
        PreparedStatement stmt = mySQL.connection.prepareStatement(alter);
        stmt.executeUpdate();

    }

    public static Integer getStock(String tableName) throws SQLException {
        String selectStock = "SELECT * FROM "+ tableName;
        mySQL.statement = mySQL.connection.createStatement();
        mySQL.resultSet = mySQL.statement.executeQuery(selectStock);

        return Integer.parseInt(mySQL.resultSet.getString("variant_inventory_qty"));
    }

    public static Vector<Integer> getStocks(String tableName) throws SQLException {
        String selectStock = "SELECT * FROM "+ tableName;
        mySQL.statement = mySQL.connection.createStatement();
        mySQL.resultSet = mySQL.statement.executeQuery(selectStock);
        Vector<Integer> stocks = new Vector<>();
        mySQL.resultSet.next();
        while (mySQL.resultSet.next()){
            int i = Integer.parseInt(mySQL.resultSet.getString("variant_inventory_qty"));
            stocks.add(i);
            System.out.println(i);
        }
        return stocks;
    }


    public static List<String> getSKU(String tableName) throws SQLException {
        String selectStock = "SELECT * FROM "+ tableName;
        mySQL.statement = mySQL.connection.createStatement();
        mySQL.resultSet = mySQL.statement.executeQuery(selectStock);
        List<String> stocks = new ArrayList<>();
        while (mySQL.resultSet.next()){
            String i = mySQL.resultSet.getString("variant_inventory_qty");
            stocks.add(i);
        }
        return stocks;
    }
}


