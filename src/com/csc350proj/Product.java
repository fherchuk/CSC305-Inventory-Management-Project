package com.csc350proj;

public class Product {
    //Product Class for individual Items
    // More Attributes to be added ...
    private final String name;
    private final Double price;
    private final Integer quantity;

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
}
