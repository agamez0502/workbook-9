package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.ProductDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.aot.hint.annotation.RegisterReflection;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductsController {

    // attributes
    private ProductDao productDao;

    // constructor
    public ProductsController(ProductDao productDao) {
        this.productDao = productDao;
    }

    // should return a list of all products
    @GetMapping("/api/products")
    public List<Product> getAll() {
        return productDao.getAll();
    }

    // should return a specific product
    @GetMapping("/api/products/{id}")
    public Product getById(@PathVariable int id) {
        return productDao.getById(id);
    }

    // should allow users to add products
    @PostMapping("/api/products")
    public Product insert(@RequestBody Product product) {
        return productDao.insert(product);
    }

    // should allow users to update products
    @PutMapping("/api/products/{id}")
    public void update(@PathVariable int id, @RequestBody Product product) {
        productDao.update(id, product);
    }

    // should allow users to delete products
    @DeleteMapping("/api/products/{id}")
    public void delete(@PathVariable int id) {
        productDao.delete(id);
    }
}