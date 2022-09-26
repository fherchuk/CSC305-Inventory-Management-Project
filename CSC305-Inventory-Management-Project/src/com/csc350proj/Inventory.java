package com.csc350proj;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
    //Inventory Class using HashMap
    // Includes functions for filling and printing...
    public final HashMap<String, Product> inventoryItems = new HashMap<>();


    public void fillInventory() {
        //This is a Function to initially fill the database
        //To Be Referenced later in imports from Excel...
        // TO BE FINISHED

        //Placeholder data
        //Can be changed and altered to test values and new attributes...
        Product temp = new Product("Apple", 0.75, 3);
        inventoryItems.put(temp.name, temp);
        temp = new Product("Banana", 1.25, 4);
        inventoryItems.put(temp.name, temp);
        temp = new Product("Coconut", 4.00, 1);
        inventoryItems.put(temp.name, temp);
    }

    // Prints the entirety of the Inventory Vector
    public void printInventory() {
        //Test Print Function...
        System.out.println("Here are the following Inventory Items: ");
        DecimalFormat formatPrice = new DecimalFormat("0.00");
        for (Map.Entry<String, Product> index : this.inventoryItems.entrySet()) {
            System.out.println(index.getKey() + " :: $" + formatPrice.format(index.getValue().price) + " :: " + index.getValue().quantity);
        }
    }

    // Creates a new item and adds to the inventory hashmap.
    public void addItem(String name, Double price, Integer quantity) {
        Product temp = new Product(name, price, quantity);
        this.inventoryItems.put(name, temp);
    }

    public void removeItem(String name) {
        this.inventoryItems.remove(name);
    }
// Replaces attributes of a single item. A new item is created if the key is changed.
    public void replaceItem(String key, String newName, Double newPrice, Integer newQuantity) {
        //This statement checks if the key is to be changed and adds a new item with the new key and deletes the old item.
        if (newName != null) {
            addItem(newName, this.inventoryItems.get(key).price, this.inventoryItems.get(key).quantity);
            this.inventoryItems.remove(key);
            key = newName;
        }
        if (newPrice != null) { this.inventoryItems.get(key).price = newPrice; }
        if (newQuantity != null) { this.inventoryItems.get(key).quantity = newQuantity; }
    }
}




