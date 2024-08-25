package com.hsbc.ecommerce.service.customer;

import com.hsbc.ecommerce.models.Order;
import com.hsbc.ecommerce.models.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface CustomerActionService {
    void placeOrder(Order order) throws SQLException;
    List<Order> viewOrderHistory(int customerId) throws SQLException;
    List<Subscription> viewSubscriptions(int customerId) throws SQLException;
}
