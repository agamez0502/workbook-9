package com.pluralsight.NorthwindTradersSpringBoot.dao;

import com.pluralsight.NorthwindTradersSpringBoot.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Component
public class JdbcProductDao implements ProductDao {

    // attributes
    private DataSource dataSource;


    @Autowired
    // constructor
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Product product) {
        // This is the SQL INSERT statement we will run.
        // We are inserting the id, name, and price
        String sql = "INSERT INTO product (ProductID, ProductName, UnitPrice) VALUES (?, ?, ?)";

        // This is a "try-with-resources" block.
        // It ensures that the Connection and PreparedStatement are closed automatically after we are done.
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Set the first parameter (?) to the productID.
            stmt.setInt(1, product.getProductId());

            // Set the second parameter (?) to the product's name.
            stmt.setString(2, product.getName());

            // Set the third parameter (?) to the unit price.
            stmt.setInt(3, product.getPrice();

            // Execute the INSERT statement — this will add the row to the database.
            stmt.executeUpdate();

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            System.out.println("Unable to add product");
            //e.printStackTrace()
        }
    }

    @Override
    public List<Product> getAll() {

        return product;
    }
}