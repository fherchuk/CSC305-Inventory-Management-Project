package com.csc350proj;

import javax.naming.Name;

public class Main {
    public static void main(String[] args) {
        //Main Function
        //Basic Shell for product Class
        Product product = new Product("Apple", 0.75, 10);

        System.out.println(product.getName()+ " " + product.getPrice()+ " " + product.getQuantity());
    }
}
