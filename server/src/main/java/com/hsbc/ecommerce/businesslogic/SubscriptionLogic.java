package com.hsbc.ecommerce.businesslogic;

import com.hsbc.ecommerce.dao.SubscriptionDAO;
import com.hsbc.ecommerce.models.Subscription;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionLogic {
    private SubscriptionDAO subscriptionDAO;

    public SubscriptionLogic(SubscriptionDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    public void defineSubscription(Subscription subscription) {
        // Business logic for defining a subscription
        try {
            subscriptionDAO.saveSubscription(subscription);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void activateSubscription(int subscriptionId) {
        // Business logic for activating a subscription
        try {
            subscriptionDAO.activateSubscription(subscriptionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deactivateSubscription(int subscriptionId) {
        // Business logic for deactivating a subscription
        try {
            subscriptionDAO.deactivateSubscription(subscriptionId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Subscription> getAllSubscriptions() {
        // Business logic for retrieving all subscriptions
        try {
            return subscriptionDAO.getAllSubscriptions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
