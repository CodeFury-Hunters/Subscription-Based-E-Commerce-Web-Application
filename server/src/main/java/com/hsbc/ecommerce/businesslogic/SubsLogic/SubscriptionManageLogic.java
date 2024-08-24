package com.hsbc.ecommerce.businesslogic.SubsLogic;

import java.sql.SQLException;

import com.hsbc.ecommerce.dao.SubscriptionsDAO.SubscriptionManageDAO;

public class SubscriptionManageLogic {
    private final SubscriptionManageDAO subscriptionDAO;

    public SubscriptionManageLogic(SubscriptionManageDAO subscriptionDAO) {
        this.subscriptionDAO = subscriptionDAO;
    }


    public void activateSubscription(int subscriptionId) {
        try {
            subscriptionDAO.activateSubscription(subscriptionId);
        } catch (SQLException e) {
            throw new RuntimeException("Error activating subscription", e);
        }
    }

    public void deactivateSubscription(int subscriptionId) {
        try {
            subscriptionDAO.deactivateSubscription(subscriptionId);
        } catch (SQLException e) {
            throw new RuntimeException("Error deactivating subscription", e);
        }
    }
}
