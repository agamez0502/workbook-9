package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;

import java.util.List;

public interface ProductDao {

    // should return a list of all products
    List<Product> getAll();

    // should return a specific product
    Product getById(int id);

    // should allow users to add products
    Product insert(Product product);
}