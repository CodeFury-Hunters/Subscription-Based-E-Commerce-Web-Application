package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.OrderProduct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAO {

    private Connection connection;

    public OrderProductDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new order product
    public void saveOrderProduct(OrderProduct orderProduct) throws SQLException {
        String sql = "INSERT INTO order_products (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.executeUpdate();
        }
    }

    // Retrieve an order product by ID
    public OrderProduct getOrderProductById(int id) throws SQLException {
        String sql = "SELECT * FROM order_products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractOrderProductFromResultSet(rs);
                }
            }
        }
        return null;
    }

    // Retrieve all order products
    public List<OrderProduct> getAllOrderProducts() throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String sql = "SELECT * FROM order_products";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                orderProducts.add(extractOrderProductFromResultSet(rs));
            }
        }
        return orderProducts;
    }

    // Update an order product
    public void updateOrderProduct(OrderProduct orderProduct) throws SQLException {
        String sql = "UPDATE order_products SET quantity = ? WHERE order_id = ? AND product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderProduct.getQuantity());
            stmt.setInt(2, orderProduct.getOrderId());
            stmt.setInt(3, orderProduct.getProductId());
            stmt.executeUpdate();
        }
    }

    // Delete an order product
    public void deleteOrderProduct(int orderId, int productId) throws SQLException {
        String sql = "DELETE FROM order_products WHERE order_id = ? AND product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }

    // Retrieve all OrderProduct objects by orderId
    public List<OrderProduct> getOrderProductsByOrderId(int orderId) throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String sql = "SELECT * FROM order_products WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderProducts.add(extractOrderProductFromResultSet(rs));
                }
            }
        }
        return orderProducts;
    }

    // Helper method to extract an OrderProduct object from a ResultSet
    private OrderProduct extractOrderProductFromResultSet(ResultSet rs) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(rs.getInt("order_id"));
        orderProduct.setProductId(rs.getInt("product_id"));
        orderProduct.setQuantity(rs.getInt("quantity"));
        return orderProduct;
    }
}