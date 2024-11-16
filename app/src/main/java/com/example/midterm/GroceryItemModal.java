package com.example.midterm;

/**
 * GroceryItemModal.java
 * This class defines the structure of a Grocery Item object with its attributes.
 * Author: Abdullahi Ali
 * Date: 11/13/24
 */

public class GroceryItemModal {
    //Fields
    private String itemName;
    private int quantity;
    private String category;
    private double price;


    //Constructor for GroceryItemModal

    public GroceryItemModal(String itemName, int quantity, String category, double price) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.category = category;
        this.price = price;
    }

    // Getters and Setters
    public String getItemName()
    { return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}


