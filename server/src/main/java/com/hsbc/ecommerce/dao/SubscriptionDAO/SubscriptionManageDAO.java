package com.hsbc.ecommerce.dao.SubscriptionDAO;

import java.sql.SQLException;

public interface SubscriptionManageDAO {
    void activateSubscription(int subscriptionId) throws SQLException;
    void deactivateSubscription(int subscriptionId) throws SQLException;
}
