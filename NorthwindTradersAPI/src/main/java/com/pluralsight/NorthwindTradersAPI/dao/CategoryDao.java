package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;

import java.util.List;

public interface CategoryDao {

    // should return a list of all categories
    List<Category> getAll();

    // should return a specific category
    Category getById(int id);

    // should allow users to add categories
    Category insert(Category category);

    // should allow users to update categories
    void update(int id, Category category);
}