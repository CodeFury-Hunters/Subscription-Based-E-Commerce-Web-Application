package com.hsbc.ecommerce.daoImpl.CustomerDAOImpl;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.ecommerce.dao.CustomerDAO.CustomerSubsDAO;
import com.hsbc.ecommerce.models.Customer;

public class CustomerSubsDaoImpl implements CustomerSubsDAO{

    private final Connection connection;

    public CustomerSubsDaoImpl(Connection connection) {
        this.connection = connection;
    }
        
    public void saveSubscriptions(Customer customer) throws SQLException {
        String sql = "INSERT INTO subscription (customerId, id) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            for (int subId : customer.getSubscriptions()) {
                stmt.setInt(1, customer.getId());
                stmt.setInt(2, subId);
                stmt.addBatch();
            }
            stmt.executeBatch();
        }
    }

    public List<Integer> getCustomerSubscriptions(int customerId) throws SQLException {
        List<Integer> subscriptions = new ArrayList<>();
        String sql = "SELECT productId FROM subscription WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    subscriptions.add(rs.getInt("id"));
                }
            }
        }
        return subscriptions;
    }
}