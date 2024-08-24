package com.hsbc.ecommerce.daoImpl;

import com.hsbc.ecommerce.dao.OrderDAO;
import com.hsbc.ecommerce.models.Order;
import com.hsbc.ecommerce.models.OrderProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    private final Connection connection;

    public OrderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveOrder(Order order) throws SQLException {
        String sql = "INSERT INTO orders (customerId, totalAmount) VALUES ( ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setDouble(3, order.getTotalAmount());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                }
            }
            saveOrderProducts(order);
        }
    }

    @Override
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

    @Override
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

    @Override
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET customerId = ?, totalAmount = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setDouble(3, order.getTotalAmount());
            stmt.setInt(4, order.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        deleteOrderProducts(id);
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private void saveOrderProducts(Order order) throws SQLException {
        String sql = "INSERT INTO order_products (orderId, productId, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (OrderProduct product : order.getProducts()) {
                stmt.setInt(1, order.getId());
                stmt.setInt(2, product.getProductId());
                stmt.setInt(3, product.getQuantity());
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    private void deleteOrderProducts(int orderId) throws SQLException {
        String sql = "DELETE FROM order_products WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }

    private List<OrderProduct> getOrderProducts(int orderId) throws SQLException {
        List<OrderProduct> products = new ArrayList<>();
        String sql = "SELECT * FROM order_products WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderProduct product = new OrderProduct();
                    product.setProductId(rs.getInt("productId"));
                    product.setQuantity(rs.getInt("quantity"));
                    products.add(product);
                }
            }
        }
        return products;
    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customerId"));
        order.setTotalAmount(rs.getDouble("totalAmount"));
        return order;
    }
}
