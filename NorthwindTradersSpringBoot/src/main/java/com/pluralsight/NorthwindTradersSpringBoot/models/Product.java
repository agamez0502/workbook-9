package com.pluralsight.NorthwindTradersSpringBoot.models;

public class Product {

    // attributes
    private int productId;
    private String name;
    private String categoryId;
    private double price;

    // constructor
    public Product(int productId, String name, String categoryId, double price) {
        this.productId = productId;
        this.name = name;
        this.categoryId = categoryId;
        this.price = price;
    }

    // getters and setters
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}