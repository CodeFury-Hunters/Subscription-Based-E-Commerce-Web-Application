package com.hsbc.ecommerce.businesslogic.SubsLogic;

import com.hsbc.ecommerce.dao.SubscriptionDAO.SubscriptionCrudDAO;
import com.hsbc.ecommerce.models.Subscription;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionCrudLogic {
    private final SubscriptionCrudDAO subscriptionDAO;

    public SubscriptionCrudLogic(SubscriptionCrudDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }

    public void defineSubscription(Subscription subscription) {
        try {
            subscriptionDAO.saveSubscription(subscription);
        } catch (SQLException e) {
            // Custom exception could be used here
            throw new RuntimeException("Error defining subscription", e);
        }
    }

    public List<Subscription> getAllSubscriptions() {
        try {
            return subscriptionDAO.getAllSubscriptions();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving subscriptions", e);
        }
    }
}
