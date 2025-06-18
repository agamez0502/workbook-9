package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CategoriesController {

    @Autowired
    @Qualifier("jdbcCategoryDao")
    private CategoryDao categoryDao;

    // should return a list of all categories
    @GetMapping("/api/categories")
    public List<Category> getCategories() {
        return categoryDao.getAll();
    }

    // should return a specific category
    @GetMapping("/api/categories/{id}")
    public Product getCategoriesById(@PathVariable int id) {
        return categoryDao.findById(id);
    }
}