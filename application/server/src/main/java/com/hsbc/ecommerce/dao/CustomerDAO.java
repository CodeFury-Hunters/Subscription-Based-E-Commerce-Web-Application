package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    void saveCustomer(Customer customer) throws SQLException;
    Customer getCustomerById(int id) throws SQLException;
    List<Customer> getAllCustomers() throws SQLException;
    void updateCustomer(Customer customer) throws SQLException;
    void deleteCustomer(int id) throws SQLException;
}
