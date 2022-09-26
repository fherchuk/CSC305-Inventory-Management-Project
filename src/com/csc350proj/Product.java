package com.csc350proj;

public class Product {
    //Product Class for individual Items
    // More Attributes to be added ...
    public String name;
    public Double price;
    public Integer quantity;

    //constructor
    public Product(String name, Double price, Integer quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }


    //print statement
    public void printProduct(){
        System.out.println(this.name +" "+ this.price +" "+ this.quantity);
    }

}
