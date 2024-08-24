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
    public void saveOrderProduct(OrderProduct orderProduct) throws SQLException {
        String sql = "INSERT INTO order_products (orderId, productId, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    orderProduct.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
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

    @Override
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

    @Override
    public void updateOrderProduct(OrderProduct orderProduct) throws SQLException {
        String sql = "UPDATE order_products SET orderId = ?, productId = ?, quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderProduct.getOrderId());
            stmt.setInt(2, orderProduct.getProductId());
            stmt.setInt(3, orderProduct.getQuantity());
            stmt.setInt(4, orderProduct.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteOrderProduct(int id) throws SQLException {
        String sql = "DELETE FROM order_products WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private OrderProduct extractOrderProductFromResultSet(ResultSet rs) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setId(rs.getInt("id"));
        orderProduct.setOrderId(rs.getInt("orderId"));
        orderProduct.setProductId(rs.getInt("productId"));
        orderProduct.setQuantity(rs.getInt("quantity"));
        return orderProduct;
    }
}
