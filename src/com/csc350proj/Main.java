package com.csc350proj;

import javax.naming.Name;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Iterator;
import java.util.Vector;

public class Main {


    public static void main(String[] args) throws FileNotFoundException, ParseException {
        //Main Function
        //Testing Filling an inventory object with products
        Inventory inv1 = new Inventory();
        CSV_Import.readFile(inv1);


        //Test print statements for both individual Products and the entire inventory
        inv1.printInventory();

    }
}
