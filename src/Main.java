import java.io.FileNotFoundException;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        //Main Function
        //Initialize the SQL Connection and Mapper Class

        mySQL SQL = new mySQL();
        Mapper mapper = new Mapper(SQL);
        MyFrame frame1 = new MyFrame(mapper);
        //Importation of CSV File into Database
        //try{
        //}catch(Exception e){  System.out.println
          //      ("Table Already Exists");   }
        //*NOTE: FILEPATH SHOULD BE CHANGED TO DIRECTORY OF CSV FILE. THIS WILL BE UPDATED LATER TO A FILE EXPLORER.
    }
}
