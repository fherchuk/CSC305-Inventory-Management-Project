package com.csc350proj;

import javax.naming.Name;
import java.util.Iterator;
import java.util.Vector;

public class Main {


    public static void main(String[] args) {
        //Main Function
        //Testing Filling an inventory object with products
        Inventory inv1 = new Inventory();
        inv1.fillInventory();


        //Testing Search and Replace Functions
        // Takes in Index and all attributes of products. If attribute in function call is null, no change is made...

        inv1.replaceItem("Coconut", "Pebble", null, 2);
        inv1.addItem("Grape", 0.25, 20);
        inv1.removeItem("Purple");

        //Test print statements for both individual Products and the entire inventory
        inv1.printInventory();
        //inv1.getInventoryItems().get(index).printProduct();
    }
}
