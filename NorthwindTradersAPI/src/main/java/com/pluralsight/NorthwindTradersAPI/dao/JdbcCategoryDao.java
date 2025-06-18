package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcCategoryDao implements CategoryDao {

    // DataSource that we will use to connect to the database
    private DataSource dataSource;

    // constructor
    @Autowired
    public JdbcCategoryDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // should return a list of all categories
    @Override
    public List<Category> getAll() {
        return List.of();
    }

    // should return a specific category
    @Override
    public Category getById(int id) {
        return null;
    }
}