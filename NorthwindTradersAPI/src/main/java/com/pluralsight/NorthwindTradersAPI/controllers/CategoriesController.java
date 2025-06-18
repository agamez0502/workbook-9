package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Category> getCategories() {
        return categoryDao.getAll();
    }

    // should return a specific category
    @GetMapping("/api/categories/{id}")
    public Category getCategoriesById(@PathVariable int id) {
        return categoryDao.getById(id);
    }
}