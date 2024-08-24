package com.hsbc.ecommerce.businesslogic;
import com.hsbc.ecommerce.dao.OrderProductDAO;
import com.hsbc.ecommerce.models.OrderProduct;

import java.sql.SQLException;

public class OrderProductLogic {
    private OrderProductDAO orderProductDAO;

    public OrderProductLogic(OrderProductDAO orderProductDAO) {
        this.orderProductDAO = orderProductDAO;
    }

    public void addOrderProduct(OrderProduct orderProduct) {
        // Business logic for adding an OrderProduct entry
        try {
            orderProductDAO.saveOrderProduct(orderProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrderProduct(OrderProduct orderProduct) {
        // Business logic for updating an OrderProduct entry
        try {
            orderProductDAO.updateOrderProduct(orderProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCartProduct(int orderId, int productId) {
        // Business logic for deleting an OrderProduct entry
        try {
            orderProductDAO.deleteOrderProduct(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
