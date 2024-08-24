package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Order;
import java.sql.SQLException;
import java.util.List;

public interface OrderDAO {
    void saveOrder(Order order) throws SQLException;
    Order getOrderById(int id) throws SQLException;
    List<Order> getAllOrders() throws SQLException;
    void updateOrder(Order order) throws SQLException;
    void deleteOrder(int id) throws SQLException;
}
