import java.io.FileNotFoundException;
import java.sql.SQLException;



public class Main {
    public static void main(String[] args) throws FileNotFoundException, SQLException {
        //Main Function
        //Initialize the SQL Connection and Mapper Class

        mySQL SQL = new mySQL("inventory"); //Creates SQL Connection
        Mapper mapper = new Mapper(SQL);//Initializes Mapper
        MyFrame frame1 = new MyFrame(mapper);//Opens Frame to connect to Java
        //SetPriorityClass set = new SetPriorityClass(15,5);
        //Importation of CSV File into Database
        //mapper.returnStock("product",2);
    }
}
