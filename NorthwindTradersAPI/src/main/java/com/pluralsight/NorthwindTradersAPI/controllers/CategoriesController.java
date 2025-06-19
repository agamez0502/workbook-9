package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {

    // attributes
    private CategoryDao categoryDao;

    // constructor
    public CategoriesController(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    // should return a list of all categories
    @GetMapping("/api/categories")
    public List<Category> getAll() {
        return categoryDao.getAll();
    }

    // should return a specific category
    @GetMapping("/api/categories/{id}")
    public Category getById(@PathVariable int id) {
        return categoryDao.getById(id);
    }

    // should allow users to add categories
    @PostMapping("/api/categories")
    public Category insert(@RequestBody Category category) {
        return categoryDao.insert(category);
    }
}