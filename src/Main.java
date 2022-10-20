import java.io.FileNotFoundException;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        //Main Function
        //Initialize the SQL Connection and Mapper Class
        mySQL SQL = new mySQL();
        Mapper mapper = new Mapper(SQL);
        //Importation of CSV File into Database
        CSV_Import.readFile(mapper,"C:\\Users\\veteran\\Desktop\\InventoryManager\\temp\\template.csv");
        //*NOTE: FILEPATH SHOULD BE CHANGED TO DIRECTORY OF CSV FILE. THIS WILL BE UPDATED LATER TO A FILE EXPLORER.
    }
}
