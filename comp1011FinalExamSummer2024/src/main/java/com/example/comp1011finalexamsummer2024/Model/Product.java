package com.example.comp1011finalexamsummer2024.Model;

// Author: Dipak Bhul
// Student Number: 200564268

public class Product {
    private String sku;
    private String name;
    private double salePrice;
    private double regularPrice;
    private String imageUrl;

    // Constructor
    public Product(String sku, String name, double salePrice, double regularPrice, String imageUrl) {
        this.sku = sku;
        this.name = name;
        this.salePrice = salePrice;
        this.regularPrice = regularPrice;
        this.imageUrl = imageUrl;
    }

    // Getters
    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public double getRegularPrice() {
        return regularPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    // toString method
    @Override
    public String toString() {
        return String.format("%s-$%.2f", name, salePrice);
    }
}

