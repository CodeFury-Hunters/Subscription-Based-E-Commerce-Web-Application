package com.hsbc.ecommerce.daoImpl.CustomerDAOImpl;

import com.hsbc.ecommerce.dao.CustomerDAO.CustomerCrudDAO;
import com.hsbc.ecommerce.models.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerCrudDAOImpl implements CustomerCrudDAO {

    private final Connection connection;

    public CustomerCrudDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveCustomer(Customer customer) throws SQLException {
<<<<<<< HEAD:server/src/main/java/com/hsbc/ecommerce/dao/CustomerDAO.java
        String sql = "INSERT INTO customer (name, email, password, address, phone, created_at) VALUES (?, ?, ?, ?, ?, ?)";
=======
        String sql = "INSERT INTO customer (name, email, password, address, phone) VALUES (?, ?, ?, ?, ?)";
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc:server/src/main/java/com/hsbc/ecommerce/daoImpl/CustomerDAOImpl/CustomerCrudDAOImpl.java
        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.setString(3, customer.getPassword());
            stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhone());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    customer.setId(generatedKeys.getInt(1));
                }
            }
            saveSubscriptions(customer);
        }
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public void deleteCustomer(int id) throws SQLException {
        String sql = "DELETE FROM customer WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

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

    private List<Integer> getCustomerSubscriptions(int customerId) throws SQLException {
        List<Integer> subscriptions = new ArrayList<>();
<<<<<<< HEAD:server/src/main/java/com/hsbc/ecommerce/dao/CustomerDAO.java
        String sql = "id FROM subscriptions WHERE customerId = ?";
=======
        String sql = "SELECT productId FROM subscription WHERE id = ?";
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc:server/src/main/java/com/hsbc/ecommerce/daoImpl/CustomerDAOImpl/CustomerCrudDAOImpl.java
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

    private Customer extractCustomerFromResultSet(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setId(rs.getInt("id"));
        customer.setName(rs.getString("name"));
        customer.setEmail(rs.getString("email"));
        customer.setPassword(rs.getString("password"));
        customer.setAddress(rs.getString("address"));
        customer.setPhone(rs.getString("phone"));
        return customer;
    }
}
