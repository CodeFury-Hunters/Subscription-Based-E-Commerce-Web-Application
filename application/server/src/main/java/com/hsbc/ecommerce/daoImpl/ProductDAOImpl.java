package com.hsbc.ecommerce.daoImpl;

import com.hsbc.ecommerce.dao.ProductDAO;
import com.hsbc.ecommerce.models.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {

    private final Connection connection;

    public ProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveProduct(Product product) throws SQLException {
        String sql = "INSERT INTO product (name, description, price, created_at) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setDate(4, new java.sql.Date(product.getCreatedAt().getTime()));

            stmt.executeUpdate();

            // Retrieve the generated ID and set it in the product object
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    product.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        String sql = "SELECT * FROM product WHERE id = ?";
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

    @Override
    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                products.add(extractProductFromResultSet(rs));
            }
        }
        return products;
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        String sql = "UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getId());

            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        String sql = "DELETE FROM product WHERE id = ?";
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
        product.setCreatedAt(rs.getDate("created_at"));
        return product;
    }
}
