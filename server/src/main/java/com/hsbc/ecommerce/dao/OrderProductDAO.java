package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.OrderProduct;
import java.sql.SQLException;
import java.util.List;

public interface OrderProductDAO {
    void saveOrderProduct(OrderProduct orderProduct) throws SQLException;
    OrderProduct getOrderProductById(int id) throws SQLException;
    List<OrderProduct> getAllOrderProducts() throws SQLException;
    void updateOrderProduct(OrderProduct orderProduct) throws SQLException;
    void deleteOrderProduct(int id) throws SQLException;
}
