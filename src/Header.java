import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

@SuppressWarnings("rawtypes")
public class Header {
    public Vector row;
    private String name;
    private String type;


    private String keyIndex, stockIndex, priceIndex, categoryIndex;

    Header(String name, String type){
        this.name = name;
        this.type = type;
        this.row = new Vector();
        removeChars();
        findIndexes();
    }

    public void addRow(String ... item){    row.add(item);  }
    public void findIndexes(){
        if (name.matches("stock|inventory|in_stock|available")){
            this.stockIndex = name; }
        if (name.matches("sku|variantsku|variant_sku|id_number|id|google_shopping_/_mpn")){
            this.keyIndex = name; }
        if (name.matches("price|variant_price|cost|value")){
            this.priceIndex = name; }
        if (name.matches("category|product_category")){
            this.categoryIndex = name; }
    }

    public void removeChars(){
        name = name.replace(" ","_");
        name = name.replace("/","_");
        name = name.replace("(","");
        name = name.replace(")","");
    }


    public String getName(){
        return name; }
    public String getType(){
        return type; }

    public String getKeyIndex(){
        return keyIndex;    }
    public String getStockIndex(){
        return stockIndex;  }
    public String getPriceIndex(){
        return priceIndex;  }
    public String getCategoryIndex(){
        return categoryIndex;}
}
