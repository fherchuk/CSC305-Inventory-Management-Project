package com.csc350proj;

import java.text.DecimalFormat;
import java.util.Vector;

public class Inventory {
    //Inventory Class using Vector.
    // Includes functions for filling and printing...
    private final Vector<Product> inventoryItems = new Vector<>();
    private boolean found;

    // Getters Inventory Class
    public Vector<Product> getInventoryItems() {
        return inventoryItems;
    }
    public boolean isFound(){ return found;    }



    public void FillInventory(){
        //This is a Function to initially fill the database
        //To Be Referenced later in imports from Excel...
        // TO BE FINISHED

        //Placeholder data
        //Can be changed and altered to test values and new attributes...
        Product temp = new Product("Apple", 0.75, 3);
        inventoryItems.add(temp);
        temp = new Product("Banana", 1.25, 4);
        inventoryItems.add(temp);
        temp = new Product("Coconut", 4.00, 1);
        inventoryItems.add(temp);
    }

    // Prints the entirety of the Inventory Vector
    public void PrintInventory(){
        //Test Print Function...
        System.out.println("Here are the following Inventory Items: ");
        DecimalFormat formatPrice = new DecimalFormat("0.00");
        for (Product product : inventoryItems) {
            System.out.println(product.getName() + " :: $" + formatPrice.format(product.getPrice()) + " :: " + product.getQuantity());
        }
    }
// Search by name function w/ a found flag boolean in the Inventory Class
    public Integer NameSearch(String name){
        for (int i=0; i<inventoryItems.size(); i++) {
            if(inventoryItems.get(i).getName().equals(name)){
                found = true;
                return i;
            }
        }
        System.out.println("Product Not Found");
        found = false;
        return 0;
    }
}
