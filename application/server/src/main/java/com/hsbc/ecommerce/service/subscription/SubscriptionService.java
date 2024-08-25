package com.hsbc.ecommerce.service.subscription;

import com.hsbc.ecommerce.models.OrderProduct;
import com.hsbc.ecommerce.models.Subscription;

import java.sql.SQLException;
import java.util.List;

public interface SubscriptionService {
    void createSubscription(Subscription subscription) throws SQLException;
    Subscription getSubscriptionById(int id) throws SQLException;
    List<Subscription> getAllSubscriptions() throws SQLException;
    void updateSubscription(Subscription subscription) throws SQLException;
    void deleteSubscription(int id) throws SQLException;
    void activateSubscription(int subscriptionId) throws SQLException;
    void deactivateSubscription(int subscriptionId) throws SQLException;
    public List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException;
    public List<OrderProduct> getDailyDeliveryList() throws SQLException;
}

