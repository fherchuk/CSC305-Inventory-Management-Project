package com.csc350proj;

import javax.naming.Name;
import java.util.Iterator;
import java.util.Vector;

public class Main {


    public static void main(String[] args) {
        //Main Function
        //Basic Shell for product Class
        Inventory inv1 = new Inventory();
        inv1.FillInventory();
        inv1.PrintInventory();
    }
}
