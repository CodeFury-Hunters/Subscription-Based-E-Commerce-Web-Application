package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Subscription;
import java.sql.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class SubscriptionDAO {

    private Connection connection;

    public SubscriptionDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new subscription
    public void saveSubscription(Subscription subscription) throws SQLException {
        String sql = "INSERT INTO subscription (customer_id, product_id, types, start_date, end_date, status, created_at) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setInt(2, subscription.getProductId());
            stmt.setString(3, convertEnumSetToString(subscription.getTypes()));
            stmt.setDate(4, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setString(6, subscription.getStatus());
            stmt.setDate(7, new java.sql.Date(subscription.getCreatedAt().getTime()));

            stmt.executeUpdate();

            // Retrieve the generated ID and set it in the subscription object
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    subscription.setId(generatedKeys.getInt(1));
                }
            }
        }
    }

    // Retrieve a subscription by ID
    public Subscription getSubscriptionById(int id) throws SQLException {
        String sql = "SELECT * FROM subscription WHERE id = ?";
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

    // Retrieve all subscriptions
    public List<Subscription> getAllSubscriptions() throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscription";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                subscriptions.add(extractSubscriptionFromResultSet(rs));
            }
        }
        return subscriptions;
    }

    // Update a subscription
    public void updateSubscription(Subscription subscription) throws SQLException {
        String sql = "UPDATE subscription SET customer_id = ?, product_id = ?, types = ?, start_date = ?, end_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setInt(2, subscription.getProductId());
            stmt.setString(3, convertEnumSetToString(subscription.getTypes()));
            stmt.setDate(4, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setString(6, subscription.getStatus());
            stmt.setInt(7, subscription.getId());

            stmt.executeUpdate();
        }
    }

    // Delete a subscription by ID
    public void deleteSubscription(int id) throws SQLException {
        String sql = "DELETE FROM subscription WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Helper method to extract a Subscription object from a ResultSet
    private Subscription extractSubscriptionFromResultSet(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getInt("id"));
        subscription.setCustomerId(rs.getInt("customer_id"));
        subscription.setProductId(rs.getInt("product_id"));
        subscription.setTypes(convertStringToEnumSet(rs.getString("types")));
        subscription.setStartDate(rs.getDate("start_date"));
        subscription.setEndDate(rs.getDate("end_date"));
        subscription.setStatus(rs.getString("status"));
        subscription.setCreatedAt(rs.getDate("created_at"));
        return subscription;
    }

    // Helper method to convert EnumSet<SubscriptionType> to a comma-separated string
    private String convertEnumSetToString(EnumSet<Subscription.SubscriptionType> types) {
        if (types == null || types.isEmpty()) {
            return "";
        }
        StringJoiner joiner = new StringJoiner(",");
        for (Subscription.SubscriptionType type : types) {
            joiner.add(type.name());
        }
        return joiner.toString();
    }

    // Helper method to convert a comma-separated string to EnumSet<SubscriptionType>
    private EnumSet<Subscription.SubscriptionType> convertStringToEnumSet(String typesString) {
        if (typesString == null || typesString.isEmpty()) {
            return EnumSet.noneOf(Subscription.SubscriptionType.class);
        }
        String[] typesArray = typesString.split(",");
        EnumSet<Subscription.SubscriptionType> types = EnumSet.noneOf(Subscription.SubscriptionType.class);
        for (String type : typesArray) {
            types.add(Subscription.SubscriptionType.valueOf(type));
        }
        return types;
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
