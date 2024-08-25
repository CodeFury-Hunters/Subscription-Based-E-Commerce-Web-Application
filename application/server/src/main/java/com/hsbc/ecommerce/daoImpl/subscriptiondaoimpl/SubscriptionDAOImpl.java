package com.hsbc.ecommerce.daoImpl.subscriptiondaoimpl;

import com.hsbc.ecommerce.dao.SubscriptionsDAO.SubscriptionCrudDAO;
import com.hsbc.ecommerce.models.OrderProduct;
import com.hsbc.ecommerce.models.Subscription;
import java.sql.*;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class SubscriptionDAOImpl implements SubscriptionCrudDAO {

    private final Connection connection;

    public SubscriptionDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveSubscription(Subscription subscription) throws SQLException {
        String sql = "INSERT INTO subscription (customerId, productId, types, startDate, endDate, status) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setInt(2, subscription.getProductId());
            stmt.setString(3, convertSubscriptionTypesToString(subscription.getTypes()));
            stmt.setDate(4, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setString(6, subscription.getStatus());
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

    @Override
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

    @Override
    public void updateSubscription(Subscription subscription) throws SQLException {
        String sql = "UPDATE subscription SET customerId = ?, productId = ?, types = ?, startDate = ?, endDate = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, subscription.getCustomerId());
            stmt.setInt(2, subscription.getProductId());
            stmt.setString(3, convertSubscriptionTypesToString(subscription.getTypes()));
            stmt.setDate(4, new java.sql.Date(subscription.getStartDate().getTime()));
            stmt.setDate(5, new java.sql.Date(subscription.getEndDate().getTime()));
            stmt.setString(6, subscription.getStatus());
            stmt.setInt(7, subscription.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteSubscription(int id) throws SQLException {
        String sql = "DELETE FROM subscription WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Subscription> getSubscriptionsByCustomerId(int customerId) throws SQLException {
        List<Subscription> subscriptions = new ArrayList<>();
        String sql = "SELECT * FROM subscription WHERE customerId = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    subscriptions.add(extractSubscriptionFromResultSet(rs));
                }
            }
        }
        return subscriptions;
    }

    private Subscription extractSubscriptionFromResultSet(ResultSet rs) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setId(rs.getInt("id"));
        subscription.setCustomerId(rs.getInt("customerId"));
        subscription.setProductId(rs.getInt("productId"));
        subscription.setTypes(convertStringToSubscriptionTypes(rs.getString("types")));
        subscription.setStartDate(rs.getDate("startDate"));
        subscription.setEndDate(rs.getDate("endDate"));
        subscription.setStatus(rs.getString("status"));
        subscription.setCreatedAt(rs.getDate("createdAt"));
        return subscription;
    }

    private String convertSubscriptionTypesToString(EnumSet<Subscription.SubscriptionType> types) {
        StringBuilder sb = new StringBuilder();
        for (Subscription.SubscriptionType type : types) {
            if (sb.length() > 0) sb.append(",");
            sb.append(type.name());
        }
        return sb.toString();
    }

    private EnumSet<Subscription.SubscriptionType> convertStringToSubscriptionTypes(String typesStr) {
        EnumSet<Subscription.SubscriptionType> types = EnumSet.noneOf(Subscription.SubscriptionType.class);
        if (typesStr != null && !typesStr.isEmpty()) {
            String[] typeArray = typesStr.split(",");
            for (String type : typeArray) {
                types.add(Subscription.SubscriptionType.valueOf(type));
            }
        }
        return types;
    }

    @Override
    public List<OrderProduct> getDailyDeliveryList() throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String sql = "SELECT op.* " +
                "FROM order_products op " +
                "JOIN subscriptions s ON op.subscriptionId = s.id " +
                "WHERE CURRENT_DATE BETWEEN s.startDate AND s.endDate";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs = statement.executeQuery()) {

            while (rs.next()) {
                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setId(rs.getInt("id"));
                orderProduct.setOrderId(rs.getInt("orderId"));
                orderProduct.setProductId(rs.getInt("productId"));
                orderProduct.setQuantity(rs.getInt("quantity"));
                orderProduct.setSubscriptionId(rs.getInt("subscriptionId"));
                orderProducts.add(orderProduct);
            }
        }
        return orderProducts;
    }
}