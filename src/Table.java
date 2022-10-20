import java.util.List;

public class Table {
    public String name;
    public List<String> headers;
    public List<String> types;
    public Integer headerSize;

    public Table(String name, List<String> headers, List<String> types){
        this.name = name;
        this.headers = headers;
        this.headerSize = 0;
        this.types = types;
    }
    public String setVariables(){
        StringBuilder statement = new StringBuilder();
        statement.append(" (");
        int h = 0;
        while (h<headerSize){
            statement.append("?, ");
            h++;
        }
        statement.delete(statement.length() - 2, statement.length());
        statement.append(");");
        return String.valueOf(statement);
    }
  public String iterateHeaders(){
          StringBuilder statement = new StringBuilder();
          statement.append(" (");
          for (String i:headers){
              statement.append(i).append(", ");
          }
          statement.delete(statement.length() - 2, statement.length());
          statement.append(")");
          return statement.toString();
      }

    public String makeTableHeaders(){
        StringBuilder statement = new StringBuilder();
        statement.append(" (");
        int index = 0;
        for (String i:headers){
            statement.append(i).append(" ");
            statement.append(typeReturn(types.get(index)));
            statement.append(", ");
            index++;

            headerSize += 1;
        }

        statement.delete(statement.length() - 2, statement.length());
        statement.append(");");
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
