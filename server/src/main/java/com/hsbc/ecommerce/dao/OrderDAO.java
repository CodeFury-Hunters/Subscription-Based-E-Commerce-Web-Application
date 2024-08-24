package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Order;
import com.hsbc.ecommerce.models.OrderProduct;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    private Connection connection;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new order
    public void saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customer_id, subscription_id, order_date, delivery_date, status, total_amount) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getSubscriptionId());
            stmt.setDate(3, new Date(order.getOrderDate().getTime()));
            stmt.setDate(4, new Date(order.getDeliveryDate().getTime()));
            stmt.setString(5, order.getStatus());
            stmt.setDouble(6, order.getTotalAmount());

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                }
            }

            saveOrderProducts(order);
        }
    }

    // Retrieve an order by ID
    public Order getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Order order = extractOrderFromResultSet(rs);
                    order.setProducts(getOrderProducts(order.getId()));
                    return order;
                }
            }
        }
        return null;
    }

    // Retrieve all orders
    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Order order = extractOrderFromResultSet(rs);
                order.setProducts(getOrderProducts(order.getId()));
                orders.add(order);
            }
        }
        return orders;
    }

    // Update an order
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET customerId = ?, subscriptionId = ?, orderDate = ?, deliveryDate = ?, status = ?, totalAmount = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setInt(2, order.getSubscriptionId());
            stmt.setDate(3, new Date(order.getOrderDate().getTime()));
            stmt.setDate(4, new Date(order.getDeliveryDate().getTime()));
            stmt.setString(5, order.getStatus());
            stmt.setDouble(6, order.getTotalAmount());
            stmt.setInt(7, order.getId());

            stmt.executeUpdate();

            updateOrderProducts(order);
        }
    }

    // Delete an order by ID
    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            deleteOrderProducts(id);
        }
    }

    // Save order products
    private void saveOrderProducts(Order order) throws SQLException {
        String sql = "INSERT INTO order_products (orderId, productId, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (OrderProduct op : order.getProducts()) {
                stmt.setInt(1, order.getId());
                stmt.setInt(2, op.getProductId());
                stmt.setInt(3, op.getQuantity());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    // Update order products
    private void updateOrderProducts(Order order) throws SQLException {
        // Remove existing products and add updated ones
        deleteOrderProducts(order.getId());
        saveOrderProducts(order);
    }

    // Delete order products
    private void deleteOrderProducts(int orderId) throws SQLException {
        String sql = "DELETE FROM order_products WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }

    // Get products for an order
    private List<OrderProduct> getOrderProducts(int orderId) throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String sql = "SELECT * FROM order_products WHERE order_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderProduct orderProduct = new OrderProduct();
                    orderProduct.setProductId(rs.getInt("product_id"));
                    orderProduct.setQuantity(rs.getInt("quantity"));
                    orderProducts.add(orderProduct);
                }
            }
        }
        return orderProducts;
    }

    // Helper method to extract an Order object from a ResultSet
    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customer_id"));
        order.setSubscriptionId(rs.getInt("subscription_id"));
        order.setOrderDate(rs.getDate("order_date"));
        order.setDeliveryDate(rs.getDate("delivery_date"));
        order.setStatus(rs.getString("status"));
        order.setTotalAmount(rs.getDouble("total_amount"));
        return order;
    }
}
