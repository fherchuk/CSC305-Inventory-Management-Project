package com.csc350proj;

public class Product {
    private String name;
    private Double price;
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
}
