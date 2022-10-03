package com.csc350proj;
import java.io.*;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Scanner;

public class CSV_Import {

    public static String fileName = "DIRECTORY";
    // ENTER DIRECTORY PATH TO CSV FILE HERE!
    public static void readFile(Inventory inv) throws FileNotFoundException, ParseException {
        CSV_Import file = new CSV_Import();
        Scanner sc = new Scanner(new File(fileName));
        sc.useDelimiter(",");
        String firstLine = sc.nextLine();
        String[] headers = readLine(firstLine);
        readHeaders(headers, inv);
        while (sc.hasNextLine()) {
            String itemLine = sc.nextLine();
            String[] attributes = readLine(itemLine);
            if(attributes.length > 0) {
                inv.fillInventory(attributes);
            }
        }
        sc.close();
    }

    public static String [] readLine(String line){
        return line.split(",");
    }

    public static void readHeaders(String[] header, Inventory inventory) {
        for(int i=0; i<header.length; i++){
            header[i] = header[i].toLowerCase();
            if (header[i].contains("name")){
                inventory.nameIndex = i; }
            else if (header[i].contains("item price")){
                inventory.priceIndex = i; }
            else if (header[i].contains("stock")){
                inventory.stockIndex = i; }
        }
    }
}

