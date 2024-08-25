package com.hsbc.ecommerce.service.subscription;

import com.hsbc.ecommerce.dao.SubscriptionsDAO.SubscriptionCrudDAO;
import com.hsbc.ecommerce.dao.SubscriptionsDAO.SubscriptionManageDAO;
import com.hsbc.ecommerce.models.Subscription;

import java.sql.SQLException;
import java.util.List;

public class SubscriptionServiceImpl implements SubscriptionService {

    private final SubscriptionCrudDAO subscriptionCrudDAO;
    private final SubscriptionManageDAO subscriptionManageDAO;

    public SubscriptionServiceImpl(SubscriptionCrudDAO subscriptionCrudDAO, SubscriptionManageDAO subscriptionManageDAO) {
        this.subscriptionCrudDAO = subscriptionCrudDAO;
        this.subscriptionManageDAO = subscriptionManageDAO;
    }

    @Override
    public void createSubscription(Subscription subscription) throws SQLException {
        subscriptionCrudDAO.saveSubscription(subscription);
    }

    @Override
    public Subscription getSubscriptionById(int id) throws SQLException {
        return subscriptionCrudDAO.getSubscriptionById(id);
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws SQLException {
        return subscriptionCrudDAO.getAllSubscriptions();
    }

    @Override
    public void updateSubscription(Subscription subscription) throws SQLException {
        subscriptionCrudDAO.updateSubscription(subscription);
    }

    @Override
    public void deleteSubscription(int id) throws SQLException {
        subscriptionCrudDAO.deleteSubscription(id);
    }

    @Override
    public void activateSubscription(int subscriptionId) throws SQLException {
        subscriptionManageDAO.activateSubscription(subscriptionId);
    }

    @Override
    public void deactivateSubscription(int subscriptionId) throws SQLException {
        subscriptionManageDAO.deactivateSubscription(subscriptionId);
    }

    @Override
    public List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException {
        return subscriptionCrudDAO.getSubscriptionsByCustomerId(customerId);
    }
}

