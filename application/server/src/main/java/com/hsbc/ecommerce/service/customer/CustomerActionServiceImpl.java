package com.hsbc.ecommerce.service.customer;

import com.hsbc.ecommerce.models.Order;
import com.hsbc.ecommerce.models.Subscription;
import com.hsbc.ecommerce.service.order.OrderService;
import com.hsbc.ecommerce.service.subscription.SubscriptionService;

import java.sql.SQLException;
import java.util.List;

public class CustomerActionServiceImpl implements CustomerActionService {

    private final OrderService orderService;  // Dependency inversion principle
    private final SubscriptionService subscriptionService;

    public CustomerActionServiceImpl(OrderService orderService, SubscriptionService subscriptionService) {
        this.orderService = orderService;
        this.subscriptionService = subscriptionService;
    }

    @Override
    public void placeOrder(Order order) throws SQLException {
        orderService.createOrder(order);
    }

    @Override
    public List<Order> viewOrderHistory(int customerId) throws SQLException {
        return orderService.getOrdersByCustomerId(customerId);
    }

    @Override
    public List<Subscription> viewSubscriptions(int customerId) throws SQLException {
        return subscriptionService.getSubscriptionsByCustomerId(customerId);
    }
}