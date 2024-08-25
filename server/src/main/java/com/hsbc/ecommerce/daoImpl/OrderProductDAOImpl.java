package com.hsbc.ecommerce.daoImpl;

import com.hsbc.ecommerce.dao.OrderProductDAO;
import com.hsbc.ecommerce.models.OrderProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderProductDAOImpl implements OrderProductDAO {
    private final Connection connection;

    public OrderProductDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addOrderProduct(OrderProduct orderProduct) {
        String query = "INSERT INTO OrderProducts (orderId, productId, quantity, subscriptionId) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.setInt(4, orderProduct.getSubscriptionId());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderProduct.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public OrderProduct getOrderProductById(int id) {
        String query = "SELECT * FROM OrderProducts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return extractOrderProductFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderProduct> getAllOrderProducts() {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String query = "SELECT * FROM OrderProducts";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                orderProducts.add(extractOrderProductFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderProducts;
    }

    @Override
    public void updateOrderProduct(OrderProduct orderProduct) {
        String query = "UPDATE OrderProducts SET orderId = ?, productId = ?, quantity = ?, subscriptionId = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.setInt(4, orderProduct.getSubscriptionId());
            stmt.setInt(5, orderProduct.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteOrderProduct(int id) {
        String query = "DELETE FROM OrderProducts WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private OrderProduct extractOrderProductFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int orderId = rs.getInt("orderId");
        int productId = rs.getInt("productId");
        int quantity = rs.getInt("quantity");
        int subscriptionId = rs.getInt("subscriptionId");

        return new OrderProduct(id, orderId, productId, quantity, subscriptionId);
    }
}
