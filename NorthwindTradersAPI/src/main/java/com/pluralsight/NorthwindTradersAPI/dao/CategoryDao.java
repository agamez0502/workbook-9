package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;

import java.util.List;

public interface CategoryDao {

    // should return a list of all categories
    List<Category> getAll();

    // should return a specific category
    Category getById(int id);
}