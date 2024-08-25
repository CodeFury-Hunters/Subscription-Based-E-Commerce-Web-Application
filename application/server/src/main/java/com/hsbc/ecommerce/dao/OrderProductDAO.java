package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.OrderProduct;

import java.util.List;

public interface OrderProductDAO {
    void addOrderProduct(OrderProduct orderProduct);
    OrderProduct getOrderProductById(int id);
    List<OrderProduct> getAllOrderProducts();
    void updateOrderProduct(OrderProduct orderProduct);
    void deleteOrderProduct(int id);
}