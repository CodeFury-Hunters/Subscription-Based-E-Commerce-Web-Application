package com.hsbc.ecommerce.dao.SubscriptionDAO;

import com.hsbc.ecommerce.models.Subscription;
import java.sql.SQLException;
import java.util.List;

public interface SubscriptionCrudDAO {
    void saveSubscription(Subscription subscription) throws SQLException;
    Subscription getSubscriptionById(int id) throws SQLException;
    List<Subscription> getAllSubscriptions() throws SQLException;
    void updateSubscription(Subscription subscription) throws SQLException;
    void deleteSubscription(int id) throws SQLException;
}
