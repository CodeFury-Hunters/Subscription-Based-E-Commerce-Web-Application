package com.hsbc.ecommerce.service.order;

import com.hsbc.ecommerce.dao.OrderDAO;
import com.hsbc.ecommerce.models.Order;
import com.hsbc.ecommerce.models.OrderProduct;

import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void createOrder(Order order) throws SQLException {
        orderDAO.saveOrder(order);
    }

    @Override
    public Order getOrderById(int id) throws SQLException {
        return orderDAO.getOrderById(id);
    }

    @Override
    public List<Order> getAllOrders() throws SQLException {
        return orderDAO.getAllOrders();
    }

    @Override
    public void updateOrder(Order order) throws SQLException {
        orderDAO.updateOrder(order);
    }

    @Override
    public void deleteOrder(int id) throws SQLException {
        orderDAO.deleteOrder(id);
    }


    @Override
    public List<OrderProduct> getOrderProducts(int orderId) throws SQLException {
        return orderDAO.getOrderProducts(orderId);
    }

    @Override
    public List<Order> getOrdersByCustomerId(int customerId) throws SQLException {
        return orderDAO.getOrdersByCustomerId(customerId);
    }

}
