package com.csc350proj;

import javax.naming.Name;
import java.util.Iterator;
import java.util.Vector;

public class Main {


    public static void main(String[] args) {
        //Main Function
        //Testing Filling an inventory object with products
        Inventory inv1 = new Inventory();
        inv1.FillInventory();


        //Testing Search Function
        Integer index = inv1.NameSearch("Coconut");

        //Testing changes to values at the index if it is found by search function based on boolean
        if (inv1.isFound()) {
            inv1.getInventoryItems().get(index).setName("Orange");
            inv1.getInventoryItems().get(index).setPrice(2.10);
        }
        //Test print statements for both individual Products and the entire inventory
        inv1.PrintInventory();
        //inv1.getInventoryItems().get(index).printProduct();
    }
}
