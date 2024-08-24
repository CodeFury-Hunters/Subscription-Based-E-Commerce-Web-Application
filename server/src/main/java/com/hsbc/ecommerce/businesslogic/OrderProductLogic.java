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
        try {
            orderProductDAO.saveOrderProduct(orderProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateOrderProduct(OrderProduct orderProduct) {
        try {
            orderProductDAO.updateOrderProduct(orderProduct);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

<<<<<<< HEAD
    public void deleteOrderProduct(int orderId, int productId) {
=======
    public void deleteCartProduct(int orderId, int productId) {
        // Business logic for deleting an OrderProduct entry
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
        try {
            orderProductDAO.deleteOrderProduct(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

<<<<<<< HEAD
    public List<OrderProduct> getOrderProductsByOrderId(int orderId) {
        try {
            return orderProductDAO.getOrderProductsByOrderId(orderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
=======
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
}
