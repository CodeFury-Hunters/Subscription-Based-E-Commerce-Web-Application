//package com.hsbc.ecommerce.dao.*;


//need to change this according to the implemention changes

import com.hsbc.ecommerce.config.DatabaseConfig;
import com.hsbc.ecommerce.dao.CustomerDAO.CustomerCrudDAO;
import com.hsbc.ecommerce.models.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerDAOTest {

    private Connection connection;
    private CustomerCrudDAOImpl customerDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        // Set up the database connection and DAO
        connection = DatabaseConfig.getConnection();
        customerDAO = new CustomerCrudDAOImpl(connection);
    }

    @AfterEach
    public void tearDown() throws SQLException {
        // Clean up the database after each test

        DatabaseConfig.closeConnection(connection);
    }

    @Test
    public void testSaveCustomer() throws SQLException {
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setEmail("john.doe12@example.com");
        customer.setPassword("password123");
        customer.setAddress("123 Elm Street");
        customer.setPhone("123-456-7890");
        customer.setSubscriptions(new ArrayList<>());  // Ensure subscriptions list is initialized

        customerDAO.saveCustomer(customer);

        Customer fetchedCustomer = customerDAO.getCustomerById(customer.getId());

        assertNotNull(fetchedCustomer);
        assertEquals("John Doe", fetchedCustomer.getName());
        assertEquals("john.doe12@example.com", fetchedCustomer.getEmail());
        assertEquals("password123", fetchedCustomer.getPassword());
        assertEquals("123 Elm Street", fetchedCustomer.getAddress());
        assertEquals("123-456-7890", fetchedCustomer.getPhone());
        assertNotNull(fetchedCustomer.getSubscriptions());
        assertTrue(fetchedCustomer.getSubscriptions().isEmpty());
    }

    @Test
    public void testGetCustomerById() throws SQLException {
        // Insert a customer
        Customer customer = new Customer();
        customer.setName("Jane Doe");
        customer.setEmail("jane.doe@example.com");
        customer.setPassword("password123");
        customer.setAddress("456 Oak Street");
        customer.setPhone("987-654-3210");
        customer.setSubscriptions(new ArrayList<>());

        customerDAO.saveCustomer(customer);

        // Retrieve and test
        Customer fetchedCustomer = customerDAO.getCustomerById(customer.getId());

        assertNotNull(fetchedCustomer);
        assertEquals("Jane Doe", fetchedCustomer.getName());
        assertEquals("jane.doe@example.com", fetchedCustomer.getEmail());
        assertEquals("password123", fetchedCustomer.getPassword());
        assertEquals("456 Oak Street", fetchedCustomer.getAddress());
        assertEquals("987-654-3210", fetchedCustomer.getPhone());
        assertNotNull(fetchedCustomer.getSubscriptions());
    }

    @Test
    public void testGetAllCustomers() throws SQLException {
        // Insert multiple customers
        Customer customer1 = new Customer();
        customer1.setName("Alice Smith");
        customer1.setEmail("alice.smith@example.com");
        customer1.setPassword("password123");
        customer1.setAddress("789 Pine Street");
        customer1.setPhone("555-555-5555");
        customer1.setSubscriptions(new ArrayList<>());

        Customer customer2 = new Customer();
        customer2.setName("Bob Johnson");
        customer2.setEmail("bob.johnson@example.com");
        customer2.setPassword("password123");
        customer2.setAddress("321 Maple Street");
        customer2.setPhone("555-555-5555");
        customer2.setSubscriptions(new ArrayList<>());

        customerDAO.saveCustomer(customer1);
        customerDAO.saveCustomer(customer2);

        List<Customer> customers = customerDAO.getAllCustomers();

        assertTrue(customers.size() >= 2);
        assertTrue(customers.stream().anyMatch(c -> c.getName().equals("Alice Smith")));
        assertTrue(customers.stream().anyMatch(c -> c.getName().equals("Bob Johnson")));
    }

    @Test
    public void testUpdateCustomer() throws SQLException {
        // Insert a customer
        Customer customer = new Customer();
        customer.setName("Charlie Brown");
        customer.setEmail("charlie.brown@example.com");
        customer.setPassword("password123");
        customer.setAddress("123 Maple Street");
        customer.setPhone("555-123-4567");
        customer.setSubscriptions(new ArrayList<>());

        customerDAO.saveCustomer(customer);

        // Update customer details
        customer.setName("Charlie Green");
        customer.setAddress("456 Maple Street");

        customerDAO.updateCustomer(customer);

        Customer updatedCustomer = customerDAO.getCustomerById(customer.getId());

        assertNotNull(updatedCustomer);
        assertEquals("Charlie Green", updatedCustomer.getName());
        assertEquals("456 Maple Street", updatedCustomer.getAddress());
    }

    @Test
    public void testDeleteCustomer() throws SQLException {
        // Insert a customer
        Customer customer = new Customer();
        customer.setName("Dave Wilson");
        customer.setEmail("dave.wilson@example.com");
        customer.setPassword("password123");
        customer.setAddress("789 Oak Street");
        customer.setPhone("555-987-6543");
        customer.setSubscriptions(new ArrayList<>());

        customerDAO.saveCustomer(customer);

        // Delete the customer
        customerDAO.deleteCustomer(customer.getId());

        Customer deletedCustomer = customerDAO.getCustomerById(customer.getId());

        assertNull(deletedCustomer);
    }
}
