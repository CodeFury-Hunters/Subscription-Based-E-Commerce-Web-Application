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
        String sql = "INSERT INTO orders (customerId, orderDate, deliveryDate, status, totalAmount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDate(3, new java.sql.Date(order.getDeliveryDate().getTime()));
            stmt.setString(4, order.getStatus());
            stmt.setDouble(5, order.getTotalAmount());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        String sql = "SELECT * FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractOrderFromResultSet(rs);
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
                orders.add(extractOrderFromResultSet(rs));
            }
        }
        return orders;
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        String sql = "UPDATE orders SET customerId = ?, orderDate = ?, deliveryDate = ?, status = ?, totalAmount = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, order.getCustomerId());
            stmt.setDate(2, new java.sql.Date(order.getOrderDate().getTime()));
            stmt.setDate(3, new java.sql.Date(order.getDeliveryDate().getTime()));
            stmt.setString(4, order.getStatus());
            stmt.setDouble(5, order.getTotalAmount());
            stmt.setInt(6, order.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        String sql = "DELETE FROM orders WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<OrderProduct> getOrderProducts(int orderId) throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String sql = "SELECT * FROM OrderProducts WHERE orderId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderProduct orderProduct = extractOrderProductFromResultSet(rs);
                    orderProducts.add(orderProduct);
                }
            }
        }
        return orderProducts;
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orders.add(extractOrderFromResultSet(rs));
                }
            }
        }
        return orders;
    }

    private Order extractOrderFromResultSet(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setId(rs.getInt("id"));
        order.setCustomerId(rs.getInt("customerId"));
        order.setOrderDate(rs.getDate("orderDate"));
        order.setDeliveryDate(rs.getDate("deliveryDate"));
        order.setStatus(rs.getString("status"));
        order.setTotalAmount(rs.getDouble("totalAmount"));
        // Note: Handle subProducts and withoutSubProducts if needed
        return order;
    }

    private OrderProduct extractOrderProductFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int orderId = rs.getInt("orderId");
        int productId = rs.getInt("productId");
        int quantity = rs.getInt("quantity");
        int subscriptionId = rs.getInt("subscriptionId");

        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(id);
        orderProduct.setOrderId(orderId);
        orderProduct.setProductId(productId);
        orderProduct.setQuantity(quantity);
        orderProduct.setSubscriptionId(subscriptionId);

        return orderProduct;
    }
}
