package com.hsbc.ecommerce.businesslogic;
import com.hsbc.ecommerce.dao.OrderDAO;
import com.hsbc.ecommerce.models.Order;

import java.sql.SQLException;
import java.util.List;

public class OrderLogic {
    private OrderDAO orderDAO;

    public OrderLogic(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    public void placeOrder(Order order) {
        try {
            orderDAO.saveOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrder(Order order) {
        try {
            orderDAO.updateOrder(order);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cancelOrder(int orderId) {
        try {
            orderDAO.deleteOrder(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Order getOrderById(int orderId) {
        try {
            return orderDAO.getOrderById(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderDAO.getAllOrders();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
