package com.hsbc.ecommerce.service.customer;

import com.hsbc.ecommerce.dao.CustomerDAO;
import com.hsbc.ecommerce.models.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerManagementServiceImpl implements CustomerManagementService {

    private final CustomerDAO customerDAO;

    public CustomerManagementServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void createCustomer(Customer customer) throws SQLException {
        customerDAO.saveCustomer(customer);
    }

    @Override
    public Customer getCustomerById(int id) throws SQLException {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) throws SQLException {
        customerDAO.deleteCustomer(id);
    }
}