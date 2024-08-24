package com.hsbc.ecommerce.dao.CustomerDAO;

import java.sql.SQLException;
import java.util.List;

import com.hsbc.ecommerce.models.Customer;

public interface CustomerSubsDAO {
    void saveSubscriptions(Customer customer) throws SQLException;
    List<Integer> getCustomerSubscriptions(int customerId) throws SQLException;
}
