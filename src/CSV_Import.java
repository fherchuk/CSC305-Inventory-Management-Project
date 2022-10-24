import java.io.*;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CSV_Import {

    public static void readFile(Mapper map,File fname) throws FileNotFoundException, SQLException {

        Scanner sc = new Scanner(fname);
        sc.useDelimiter(",");
        String firstLine = sc.nextLine();
        String vars = sc.nextLine();
        Table product = new Table("product", List.of(readLine(firstLine)), List.of(readLine(vars)));
        map.makeSQLTable(product);
        while (sc.hasNextLine()) {
            String itemLine = sc.nextLine();
            String[] attributes = readLine(itemLine);
            if(attributes.length > 0) {
                map.insertStatement(product, List.of(attributes));
            }
        }
        sc.close();
    }
    public static String [] readLine(String line){
        return line.split(",");
    }

}






