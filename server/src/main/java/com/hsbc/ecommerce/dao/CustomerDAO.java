package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    // Create a new customer
    void saveCustomer(Customer customer) throws SQLException;

    // Retrieve a customer by ID
    Customer getCustomerById(int id) throws SQLException;

    // Retrieve all customers
    List<Customer> getAllCustomers() throws SQLException;

    // Update an existing customer
    void updateCustomer(Customer customer) throws SQLException;

    // Delete a customer by ID
    void deleteCustomer(int id) throws SQLException;
}
