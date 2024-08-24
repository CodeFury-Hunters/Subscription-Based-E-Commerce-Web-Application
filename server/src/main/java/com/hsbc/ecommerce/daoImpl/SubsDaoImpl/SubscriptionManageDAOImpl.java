package com.hsbc.ecommerce.daoImpl.SubsDaoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.hsbc.ecommerce.dao.SubscriptionsDAO.SubscriptionManageDAO;

public class SubscriptionManageDAOImpl implements SubscriptionManageDAO{

    private final Connection connection;

    public SubscriptionManageDAOImpl(Connection connection) {
        this.connection = connection;
    }

    public void deactivateSubscription(int subscriptionId) throws SQLException {
        String sql = "UPDATE subscription SET status = 'inactive' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            stmt.executeUpdate();
        }
    }

    public void activateSubscription(int subscriptionId) throws SQLException {
        String sql = "UPDATE subscription SET status = 'active' WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscriptionId);
            stmt.executeUpdate();
        }
    }
}
