
import java.util.ArrayList;
import java.util.List;


public class Table {
    public String name;
    public List<Header> headers;
    public Header primary_key;


    public Table(String name, List<String> headers, List<String> types){
        this.name = name;
        this.headers = new ArrayList<>();
        for (String i : headers){
            int index = headers.indexOf(i);
            this.headers.add(new Header(i, typeReturn(types.get(index))));
        }
        for(Header j : this.headers){
            findIndexes(j);
        }
    }
    public void findIndexes(Header header){
        if (header.getName().matches("sku|variantsku|variant_sku|id_number|id|google_shopping_/_mpn")){
            this.primary_key = header; }
    }


    public void setRow(String ... item){
        for (Header i: this.headers){
            i.addRow(item);
        }
    }
    public String insertVariables(){
        StringBuilder statement = new StringBuilder();
        statement.append(" (");
        int h = 0;
        while (h<headers.size()){
            statement.append("?, ");
            h++;
        }
        statement.delete(statement.length() - 2, statement.length());
        statement.append(");");
        return String.valueOf(statement);
    }
  public String insertValues() {
      StringBuilder statement = new StringBuilder();
      statement.append(" (");
      for (Header i : headers) {
          statement.append(i.getName()).append(", ");
      }
      statement.delete(statement.length() - 2, statement.length());
      statement.append(")");
      return statement.toString();
  }
    public String createTableStatement(){
        StringBuilder statement = new StringBuilder();
        statement.append(" (");
        int index = 0;
        for (Header i:headers){
            statement.append(i.getName()).append(" ");
            statement.append(typeReturn(i.getType()));
            statement.append(", ");
            index++;
        }
        statement.append("PRIMARY KEY ("+primary_key.getName()+"));");
        return statement.toString();
    }


    // TYPE check Functions for importing into SQL
    public static String typeReturn(String data) {
        try {
            Double.parseDouble(data);
            try {
                Integer.parseInt(data);
                return "int";
            } catch(NumberFormatException e){//not an integer
                return "double";
                }
        } catch(NumberFormatException e) {//not a number
            return "varchar(45)";
        }
    }

}
