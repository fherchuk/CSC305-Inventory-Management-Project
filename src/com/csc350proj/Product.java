package com.csc350proj;

public class Product {
    //Product Class for individual Items
    // More Attributes to be added ...
    private  String name;
    private  Double price;
    private Integer quantity;

    //constructor
    public Product(String name, Double price, Integer quantity){
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    //getters
    public String getName() {
        return name;
    }
    public Double getPrice() {
        return price;
    }
    public Integer getQuantity() {
        return quantity;
    }

    //setters
    public void setName(String n){ this.name = n; }
    public void setPrice(Double n){ this.price = n; }
    public void setQuantity(Integer n){ this.quantity = n; }


    //print statement
    public void printProduct(){
        System.out.println(this.getName() +" "+ this.getPrice() +" "+ this.getQuantity());
    }

}
