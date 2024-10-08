// Author Name: Dipak Bhul
// Student Number: 200564268

package com.example.comp1011finalexamsummer2024.Model;

import java.util.List;

public class Customer {
    private String id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private List<Product> purchases; // Variable to hold purchased products

    // Constructor
    public Customer(String id, String firstName, String lastName, String phoneNumber, List<Product> purchases) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.purchases = purchases;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public List<Product> getPurchases() {
        return purchases;
    }

    // Method to get total purchases
    public double getTotalPurchases() {
        return purchases.stream()
                .mapToDouble(Product::getSalePrice)
                .sum();
    }

//     Method to get total savings
    public double getTotalSavings() {
        return purchases.stream()
                .mapToDouble(p -> p.getRegularPrice() - p.getSalePrice())
                .sum();
    }

    // Method to check if saved $5 or more on all purchases
    public boolean savedFiveOrMore() {
        return purchases.stream()
                .allMatch(p -> p.getRegularPrice() - p.getSalePrice() >= 5.0);
    }
}

