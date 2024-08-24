package com.hsbc.ecommerce.businesslogic.CustomerLogic;

import com.hsbc.ecommerce.dao.CustomerDAO.CustomerCrudDAO;
import com.hsbc.ecommerce.models.Customer;

import java.sql.SQLException;

public class CustomerCrudLogic {

    private CustomerCrudDAO customerDAO;

    public CustomerCrudLogic(CustomerCrudDAO customerDAO) {
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
