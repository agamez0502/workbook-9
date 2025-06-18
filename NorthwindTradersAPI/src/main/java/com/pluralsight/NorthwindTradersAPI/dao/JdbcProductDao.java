package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    // DataSource that we will use to connect to the database
    private DataSource dataSource;

    // constructor
    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // should return a list of all products
    @Override
    public List<Product> getAll() {
        return List.of();
    }

    // should return a specific product
    @Override
    public Product getById(int id) {
        return null;
    }
}