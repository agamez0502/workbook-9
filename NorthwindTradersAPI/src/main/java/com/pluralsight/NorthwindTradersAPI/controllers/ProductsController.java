package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductsController {

    @Autowired
    @Qualifier("jdbcProductDao")
    private ProductDao productDao;

    // should return a list of all products
    @GetMapping("/api/products")
    public List<Product> getProducts() {
        return productDao.getAll();
    }

    // should return a specific product
    @GetMapping("/api/products/{id}")
    public Product getProductById(@PathVariable int id) {
        return productDao.findById(id);
    }
}