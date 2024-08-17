package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new product
    public void saveProduct(Product product) throws SQLException {
        String sql = "INSERT INTO products (name, description, price, weekly, biWeekly, monthly, custom, is_active, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setBoolean(4, product.isWeekly());
            stmt.setBoolean(5, product.isBiWeekly());
            stmt.setBoolean(6, product.isMonthly());
            stmt.setBoolean(7, product.isCustom());
            stmt.setBoolean(8, product.isActive());
            stmt.setTimestamp(9, new Timestamp(product.getCreatedAt().getTime()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Retrieve a product by ID
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractProductFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // Retrieve all products
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        }
        return products;
    }

    // Update a product
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, weekly = ?, biWeekly = ?, monthly = ?, custom = ?, is_active = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setBoolean(4, product.isWeekly());
            stmt.setBoolean(5, product.isBiWeekly());
            stmt.setBoolean(6, product.isMonthly());
            stmt.setBoolean(7, product.isCustom());
            stmt.setBoolean(8, product.isActive());
            stmt.setInt(9, product.getId());

            stmt.executeUpdate();
        }
    }

    // Delete a product by ID
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Helper method to extract a Product object from a ResultSet
    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setWeekly(rs.getBoolean("weekly"));
        product.setBiWeekly(rs.getBoolean("biWeekly"));
        product.setMonthly(rs.getBoolean("monthly"));
        product.setCustom(rs.getBoolean("custom"));
        product.setActive(rs.getBoolean("is_active"));
        product.setCreatedAt(rs.getTimestamp("created_at"));
        return product;
    }
}
