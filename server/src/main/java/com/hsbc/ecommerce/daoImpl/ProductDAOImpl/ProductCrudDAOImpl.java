package com.hsbc.ecommerce.daoImpl.ProductDAOImpl;

import com.hsbc.ecommerce.dao.ProductDAO.ProductCrudDAO;
import com.hsbc.ecommerce.models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductCrudDAOImpl implements ProductCrudDAO {

    private final Connection connection;

    public ProductCrudDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveProduct(Product product) throws SQLException {
<<<<<<< HEAD:server/src/main/java/com/hsbc/ecommerce/dao/ProductDAO.java
        String sql = "INSERT INTO product (name, description, price, weekly, biWeekly, monthly, custom, is_active, created_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
=======
        String sql = "INSERT INTO products (name, description, price, stock, createdAt) VALUES (?, ?, ?, ?, ?)";
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc:server/src/main/java/com/hsbc/ecommerce/daoImpl/ProductDAOImpl/ProductCrudDAOImpl.java
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setTimestamp(5, new Timestamp(product.getCreatedAt().getTime()));
            stmt.executeUpdate();
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
<<<<<<< HEAD:server/src/main/java/com/hsbc/ecommerce/dao/ProductDAO.java
        String sql = "UPDATE product SET name = ?, description = ?, price = ?, weekly = ?, biWeekly = ?, monthly = ?, custom = ?, is_active = ? WHERE id = ?";
=======
        String sql = "UPDATE products SET name = ?, description = ?, price = ?, stock = ?, createdAt = ? WHERE id = ?";
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc:server/src/main/java/com/hsbc/ecommerce/daoImpl/ProductDAOImpl/ProductCrudDAOImpl.java
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getStock());
            stmt.setTimestamp(5, new Timestamp(product.getCreatedAt().getTime()));
            stmt.setInt(6, product.getId());
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

    private Product extractProductFromResultSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setId(rs.getInt("id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setPrice(rs.getDouble("price"));
        product.setStock(rs.getInt("stock"));
        product.setCreatedAt(rs.getTimestamp("createdAt"));
        return product;
    }
}
