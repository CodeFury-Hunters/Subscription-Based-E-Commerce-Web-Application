package com.hsbc.ecommerce.businesslogic.CustomerLogic;

import com.hsbc.ecommerce.dao.CustomerDAO.CustomerSubsDAO;
import com.hsbc.ecommerce.models.Customer;

import java.sql.SQLException;
import java.util.List;

public class CustomerSubsLogic {
    private final CustomerSubsDAO customerSubsDAO;

    public CustomerSubsLogic(CustomerSubsDAO customerSubsDAO) {
        this.customerSubsDAO = customerSubsDAO;
    }

    public void saveCustomerSubscriptions(Customer customer) {
        try {
            customerSubsDAO.saveSubscriptions(customer);
        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer subscriptions", e);
        }
    }

    public List<Integer> getCustomerSubscriptions(int customerId) {
        try {
            return customerSubsDAO.getCustomerSubscriptions(customerId);
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving customer subscriptions", e);
        }
    }

    public void addSubscriptionToCustomer(int customerId, int subscriptionId) {
        try {
            List<Integer> currentSubscriptions = customerSubsDAO.getCustomerSubscriptions(customerId);
            currentSubscriptions.add(subscriptionId);
            Customer customer = new Customer();
            customer.setId(customerId);
            customer.setSubscriptions(currentSubscriptions);
            customerSubsDAO.saveSubscriptions(customer);
        } catch (SQLException e) {
            throw new RuntimeException("Error adding subscription to customer", e);
        }
    }

    public void removeSubscriptionFromCustomer(int customerId, int subscriptionId) {
        try {
            List<Integer> currentSubscriptions = customerSubsDAO.getCustomerSubscriptions(customerId);
            currentSubscriptions.remove(Integer.valueOf(subscriptionId));
            Customer customer = new Customer();
            customer.setId(customerId);
            customer.setSubscriptions(currentSubscriptions);
            customerSubsDAO.saveSubscriptions(customer);
        } catch (SQLException e) {
            throw new RuntimeException("Error removing subscription from customer", e);
        }
    }
}
