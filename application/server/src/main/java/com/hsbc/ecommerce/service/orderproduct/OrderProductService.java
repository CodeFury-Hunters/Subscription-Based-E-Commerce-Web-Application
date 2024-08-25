package com.hsbc.ecommerce.service.orderproduct;

import com.hsbc.ecommerce.models.OrderProduct;
import java.sql.SQLException;
import java.util.List;

public interface OrderProductService {
    void addOrderProduct(OrderProduct orderProduct) throws SQLException;
    OrderProduct getOrderProductById(int id) throws SQLException;
    List<OrderProduct> getAllOrderProducts() throws SQLException;
    void updateOrderProduct(OrderProduct orderProduct) throws SQLException;
    void deleteOrderProduct(int id) throws SQLException;
}
