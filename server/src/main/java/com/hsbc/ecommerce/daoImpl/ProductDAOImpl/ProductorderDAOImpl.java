package com.hsbc.ecommerce.daoImpl.ProductDAOImpl;

import com.hsbc.ecommerce.dao.ProductDAO.ProductorderDAO;
import com.hsbc.ecommerce.models.OrderProduct;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductorderDAOImpl implements ProductorderDAO {

    private final Connection connection;

    public ProductorderDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void deleteOrderProduct(int orderId, int productId) {
        String sql = "DELETE FROM order_products WHERE orderId = ? AND productId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error deleting order product", e);
        }
    }

    @Override
    public List<OrderProduct> getOrderProductsByOrderId(int orderId) {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String sql = "SELECT * FROM order_products WHERE orderId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, orderId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    OrderProduct orderProduct = extractOrderProductFromResultSet(rs);
                    orderProducts.add(orderProduct);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving order products", e);
        }
        return orderProducts;
    }

    private OrderProduct extractOrderProductFromResultSet(ResultSet rs) throws SQLException {
        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setOrderId(rs.getInt("orderId"));
        orderProduct.setProductId(rs.getInt("productId"));
        orderProduct.setQuantity(rs.getInt("quantity"));
        return orderProduct;
    }
}
