package com.hsbc.ecommerce.businesslogic;

import com.hsbc.ecommerce.dao.CustomerDAO;
import com.hsbc.ecommerce.models.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerLogic {
    private CustomerDAO customerDAO;

    public CustomerLogic(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    public void registerCustomer(Customer customer) {
        // Business logic for customer registration
        try {
            customerDAO.saveCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Customer getCustomerById(int id) {
        // Business logic for retrieving a customer by ID
        try {
            return customerDAO.getCustomerById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Customer> getAllCustomers() {
        // Business logic for retrieving all customers
        try {
            return customerDAO.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateCustomer(Customer customer) {
        // Business logic for updating a customer
        try {
            customerDAO.updateCustomer(customer);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteCustomer(int id) {
        // Business logic for deleting a customer
        try {
            customerDAO.deleteCustomer(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
