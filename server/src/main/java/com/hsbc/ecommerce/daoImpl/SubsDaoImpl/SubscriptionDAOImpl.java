package com.hsbc.ecommerce.daoImpl.SubsDaoImpl;

import com.hsbc.ecommerce.dao.SubscriptionDAO.SubscriptionCrudDAO;
import com.hsbc.ecommerce.models.Subscription;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionCrudDAO{

    private final Connection connection;

    public SubscriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveSubscription(Subscription subscription) throws SQLException {
        String sql = "INSERT INTO subscriptions (customerId, startDate, endDate, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setDate(2, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setString(4, subscription.getStatus());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subscription.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    @Override
    public Subscription getSubscriptionById(int id) throws SQLException {
        String sql = "SELECT * FROM subscriptions WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractSubscriptionFromResultSet(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Subscription> getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscriptions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                subscriptions.add(extractSubscriptionFromResultSet(rs));
            }
        }
        return subscriptions;
    }

    @Override
    public void updateSubscription(Subscription subscription) throws SQLException {
        String sql = "UPDATE subscriptions SET customerId = ?, startDate = ?, endDate = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setDate(2, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(3, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setString(4, subscription.getStatus());
            stmt.setInt(5, subscription.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteSubscription(int id) throws SQLException {
        String sql = "DELETE FROM subscriptions WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    private Subscription extractSubscriptionFromResultSet(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getInt("id"));
        subscription.setCustomerId(rs.getInt("customerId"));
        subscription.setStartDate(rs.getDate("startDate"));
        subscription.setEndDate(rs.getDate("endDate"));
        subscription.setStatus(rs.getString("status"));
        return subscription;
    }

}
