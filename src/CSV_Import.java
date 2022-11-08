import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class CSV_Import {
public static Mapper map;
public static Scanner typeScan;

    /**
     * @param mapper : link to mapper class
     * @param fname : name of current file
     * @throws FileNotFoundException
     * @throws SQLException
     */
    public static void readFile(Mapper mapper, File fname) throws FileNotFoundException, SQLException {
        map = mapper;
        typeScan = new Scanner(fname);
        Scanner importScan = new Scanner(fname);
        typeScan.useDelimiter(",");
        importScan.useDelimiter(",");
        String[] headerLine = readLine(typeScan.nextLine().toLowerCase());
        String[] valuesLine = readLine(typeScan.nextLine());
        valuesLine = checkValues(valuesLine);
        Table product = new Table("product", List.of(headerLine), List.of(valuesLine));
        map.makeSQLTable(product);
        String itemLine = importScan.nextLine();
        while (importScan.hasNextLine()) {
                itemLine = importScan.nextLine();
            if(itemLine.length() > 0) {
                pushInsert(product, itemLine);
            }
        }
        map.getStocks("product");
        typeScan.close();
        importScan.close();
    }

    /**
     * Removes commas from CSV File;
     * @param line : current line
     * @return line.split
     */
    public static String [] readLine(String line){
        return line.split(",",-1);
    }

    /**
     * @param table : to insert into
     * @param attributeLine : string of attributes from current line to load into database
     * @throws SQLException
     */
    public static void pushInsert(Table table, String attributeLine) throws SQLException {
        String[] attributes = readLine(attributeLine);
        map.insertStatement(table, List.of(attributes));
    }
    public static String[] checkValues(String[] values) {
        String[] checkedValues = values;
        int i = 0;
        while (i < values.length) {
            if (Objects.equals(values[i], "") & typeScan.hasNextLine()) {
                values = readLine(typeScan.nextLine());
            } else {
                checkedValues[i] = values[i];
                i++;
            }
        }
        return checkedValues;
    }

}








