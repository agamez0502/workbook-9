package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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

        // create an empty list to hold the all the Products
        List<Product> products = new ArrayList<>();

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("SELECT ProductID, ProductName, CategoryID, UnitPrice FROM products");

             // execute the query
             ResultSet allResults = prepStatement.executeQuery()) {

            // loop through each row in the ResultSet
            while (allResults.next()) {

                // add and create the Product object to our list
                products.add(new Product(
                        allResults.getInt("ProductID"),
                        allResults.getString("ProductName"),
                        allResults.getInt("CategoryID"),
                        allResults.getDouble("UnitPrice")));
            }

        } catch (SQLException e) {
            // if something goes wrong (SQL error), print the stack trace to help debug
            System.out.println("❌ Error fetching products: " + e.getMessage());
        }

        // return the list of Products
        return products;
    }

    // should return a specific product
    @Override
    public Product getById(int id) {

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("SELECT ProductID, ProductName, CategoryID, UnitPrice FROM products WHERE ProductID = ?")) {

            // set parameters
            prepStatement.setInt(1, id);

            // execute the query
            ResultSet idResults = prepStatement.executeQuery();

            // loop through each row in the ResultSet
            if (idResults.next()) {

                // return and create the Product object to our list
                return new Product(
                        idResults.getInt("ProductID"),
                        idResults.getString("ProductName"),
                        idResults.getInt("CategoryID"),
                        idResults.getDouble("UnitPrice"));
            }

        } catch (SQLException e) {
            // if something goes wrong (SQL error), print the stack trace to help debug
            System.out.println("❌ Error fetching product by ID: " + e.getMessage());
        }
        // if no product is found
        return null;
    }

    // should allow users to add products
    @Override
    public Product insert(Product product) {

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("INSERT INTO products (ProductName, CategoryID, UnitPrice) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {

            // set parameters
            prepStatement.setString(1, product.getProductName());
            prepStatement.setInt(2, product.getCategoryId());
            prepStatement.setDouble(3, product.getUnitPrice());

            // execute the update to the query - inserts a row to the db
            prepStatement.executeUpdate();

            // grab the auto generated id
            ResultSet keys = prepStatement.getGeneratedKeys();
            if (keys.next()) {
                product.setProductId(keys.getInt(1));
            }

            return product;


        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            System.out.println("❌ Error inserting product: " + e.getMessage());
            return null;
        }
    }

    // should allow users to update products
    @Override
    public void update(int id, Product product) {

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("UPDATE products SET ProductName = ?, CategoryID = ?, UnitPrice = ? WHERE ProductID = ?")) {

            // set parameters
            prepStatement.setString(1, product.getProductName());
            prepStatement.setInt(2, product.getCategoryId());
            prepStatement.setDouble(3, product.getUnitPrice());
            prepStatement.setInt(4, id);

            // execute the update to the query - updates a row in the db
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            System.out.println("❌ Error updating product: " + e.getMessage());
        }
    }
}