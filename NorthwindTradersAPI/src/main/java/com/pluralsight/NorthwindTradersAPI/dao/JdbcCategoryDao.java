package com.pluralsight.NorthwindTradersAPI.dao;

import com.pluralsight.NorthwindTradersAPI.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
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

        // create an empty list to hold the all the Categories
        List<Category> categories = new ArrayList<>();

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("SELECT CategoryID, CategoryName FROM categories");

             // execute the query
             ResultSet allResults = prepStatement.executeQuery()) {

            // loop through each row in the ResultSet
            while (allResults.next()) {

                // add and create the Categories object to our list
                categories.add(new Category(
                        allResults.getInt("CategoryID"),
                        allResults.getString("CategoryName")));
            }

        } catch (SQLException e) {
            // if something goes wrong (SQL error), print the stack trace to help debug
            System.out.println("❌ Error fetching categories: " + e.getMessage());
        }

        // return the list of Categories
        return categories;
    }

    // should return a specific category
    @Override
    public Category getById(int id) {

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("SELECT CategoryID, CategoryName FROM categories WHERE CategoryID = ?")) {

            // set parameters
            prepStatement.setInt(1, id);

            // execute the query
            ResultSet idResults = prepStatement.executeQuery();

            // loop through each row in the ResultSet
            if (idResults.next()) {

                // return and create the Category object to our list
                return new Category(
                        idResults.getInt("CategoryID"),
                        idResults.getString("CategoryName"));
            }

        } catch (SQLException e) {
            // if something goes wrong (SQL error), print the stack trace to help debug
            System.out.println("❌ Error fetching category by ID: " + e.getMessage());
        }
        // if no category is found
        return null;
    }

    // should allow users to add categories
    @Override
    public Category insert(Category category) {

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("INSERT INTO categories (CategoryName) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {

            // set parameters
            prepStatement.setString(1, category.getCategoryName());

            // execute the update to the query - inserts a row to the db
            prepStatement.executeUpdate();

            // grab the auto generated id
            ResultSet keys = prepStatement.getGeneratedKeys();
            if (keys.next()) {
                category.setCategoryId(keys.getInt(1));
            }

            return category;


        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            System.out.println("❌ Error inserting category: " + e.getMessage());
            return null;
        }
    }

    // should allow users to update categories
    @Override
    public void update(int id, Category category) {

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("UPDATE categories SET CategoryName = ? WHERE CategoryID = ?")) {

            // set parameters
            prepStatement.setString(1, category.getCategoryName());
            prepStatement.setInt(2, id);

            // execute the update to the query - updates a row in the db
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            System.out.println("❌ Error updating category: " + e.getMessage());
        }
    }

    // should allow users to delete categories
    @Override
    public void delete(int id) {

        // this is a "try-with-resources" block
        // it ensures that the Connection, Statement, and ResultSet are closed automatically after we are done
        try (Connection conn = dataSource.getConnection();

             // start prepared statement - tied to the open connection
             PreparedStatement prepStatement = conn.prepareStatement("DELETE FROM categories WHERE CategoryID = ?")) {

            // set parameters
            prepStatement.setInt(1, id);

            // execute the update to the query - deletes a row in the db
            prepStatement.executeUpdate();

        } catch (SQLException e) {
            // If something goes wrong (SQL error), print the stack trace to help debug.
            System.out.println("❌ Error deleting category: " + e.getMessage());
        }
    }
}