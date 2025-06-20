package com.pluralsight.NorthwindTradersAPI.controllers;

import com.pluralsight.NorthwindTradersAPI.dao.CategoryDao;
import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoriesController {

    // attributes
    @Autowired
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

    // should allow users to update categories
    @PutMapping("/api/categories/{id}")
    public void update(@PathVariable int id, @RequestBody Category category) {
        categoryDao.update(id, category);
    }

    // should allow users to delete categories
    @DeleteMapping("/api/categories/{id}")
    public void delete(@PathVariable int id) {
        categoryDao.delete(id);
    }
}