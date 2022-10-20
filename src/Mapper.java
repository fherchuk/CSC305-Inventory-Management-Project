import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

public class Mapper {
    // Mapper class for communicating between JAVA program and SQL Database.
    // Functions Execute SQL Queries.
    public mySQL SQL;


    public Mapper(mySQL SQL) {
        this.SQL = SQL;
    }
    public void makeSQLTable(Table table) throws SQLException {
        String tableStr = "create table "+table.name+ table.makeTableHeaders();
        System.out.println(tableStr);
        PreparedStatement stmt = mySQL.connection.prepareStatement(tableStr);
        stmt.executeUpdate();
    }

    public void insertStatement(Table table, List<String> attributeList) throws SQLException {
        String insertStr = "INSERT INTO "+table.name+table.iterateHeaders() +"VALUES"+table.setVariables();
        System.out.println(insertStr);
        PreparedStatement stmt = mySQL.connection.prepareStatement(insertStr);
        int index = 1;
        for (String i : attributeList) {
            stmt.setString(index, i);
            index += 1;
        }
        stmt.executeUpdate();
    }

};
