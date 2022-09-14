package com.csc350proj;

import java.util.Vector;

public class Inventory {
    //Inventory Class using Vector.
    // Includes functions for filling and printing...
    private final Vector<Product> inventoryItems = new Vector<Product>();


    public void FillInventory(){
        //This is a Function to initially fill the database
        //To Be Referenced later in imports from Excel...

        //Placeholder data
        //Can be changed and altered to test values and new attributes...
        Product temp = new Product("Apple", 0.75, 3);
        inventoryItems.add(temp);
        temp = new Product("Banana", 1.25, 4);
        inventoryItems.add(temp);
        temp = new Product("Coconut", 4.00, 1);
        inventoryItems.add(temp);
    }
    public void PrintInventory(){
        //Test Print Function...
        System.out.println("Here are the following Inventory Items: ");
        for (Product product : inventoryItems) {
            System.out.println(product.getName() + " :: $" + product.getPrice() + " :: " + product.getQuantity());
        }

    }
}
