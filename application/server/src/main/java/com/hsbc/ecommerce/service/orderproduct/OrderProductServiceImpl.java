package com.hsbc.ecommerce.service.orderproduct;

import com.hsbc.ecommerce.dao.OrderProductDAO;
import com.hsbc.ecommerce.models.OrderProduct;

import java.sql.SQLException;
import java.util.List;

public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductDAO orderProductDAO;

    public OrderProductServiceImpl(OrderProductDAO orderProductDAO) {
        this.orderProductDAO = orderProductDAO;
    }

    @Override
    public void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        orderProductDAO.addOrderProduct(orderProduct);
    }

    @Override
    public OrderProduct getOrderProductById(int id) throws SQLException {
        return orderProductDAO.getOrderProductById(id);
    }

    @Override
    public List<OrderProduct> getAllOrderProducts() throws SQLException {
        return orderProductDAO.getAllOrderProducts();
    }

    @Override
    public void updateOrderProduct(OrderProduct orderProduct) throws SQLException {
        orderProductDAO.updateOrderProduct(orderProduct);
    }

    @Override
    public void deleteOrderProduct(int id) throws SQLException {
        orderProductDAO.deleteOrderProduct(id);
    }
}