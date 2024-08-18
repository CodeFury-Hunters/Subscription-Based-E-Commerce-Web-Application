package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    private final Connection connection;

    public CustomerDAO(Connection connection) {
        this.connection = connection;
    }

    // Create a new customer
    public void saveCustomer(Customer customer) throws SQLException {
        String sql = "INSERT INTO customer (name, email, password, address, phone, created_at) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhone());
            stmt.setTimestamp(6, new Timestamp(customer.getCreatedAt().getTime()));

            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
            }

            // Save subscriptions
            saveSubscriptions(customer);
        }
    }

    // Retrieve a customer by ID
    public Customer getCustomerById(int id) throws SQLException {
        String sql = "SELECT * FROM customer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Customer customer = extractCustomerFromResultSet(rs);
                    customer.setSubscriptions(getCustomerSubscriptions(customer.getId()));
                    customer.setOrderHistory(getCustomerOrderHistory(customer.getId()));
                    return customer;
                }
            }
        }
        return null;
    }

    // Retrieve all customers
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Customer customer = extractCustomerFromResultSet(rs);
                customer.setSubscriptions(getCustomerSubscriptions(customer.getId()));
                customer.setOrderHistory(getCustomerOrderHistory(customer.getId()));
                customers.add(customer);
            }
        }
        return customers;
    }

    // Update a customer
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "UPDATE customer SET name = ?, email = ?, password = ?, address = ?, phone = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhone());
            stmt.setInt(6, customer.getId());

            stmt.executeUpdate();
        }
    }

    // Delete a customer by ID
    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Save customer subscriptions
    private void saveSubscriptions(Customer customer) throws SQLException {
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

    // Get subscriptions for a customer
    private List<Integer> getCustomerSubscriptions(int customerId) throws SQLException {
        List<Integer> subscriptions = new ArrayList<>();
        String sql = "id FROM subscriptions WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    subscriptions.add(rs.getInt("subscription_id"));
                }
            }
        }
        return subscriptions;
    }

    // Get order history for a customer
    private List<Integer> getCustomerOrderHistory(int customerId) throws SQLException {
        List<Integer> orderHistory = new ArrayList<>();
        String sql = "SELECT id FROM orders WHERE customerId = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, customerId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    orderHistory.add(rs.getInt("id"));
                }
            }
        }
        return orderHistory;
    }

    // Helper method to extract a Customer object from a ResultSet
    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setAddress(rs.getString("address"));
        customer.setPhone(rs.getString("phone"));
        customer.setCreatedAt(rs.getTimestamp("created_at"));
        return customer;
    }
}
