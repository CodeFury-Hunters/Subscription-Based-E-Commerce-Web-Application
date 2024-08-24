<<<<<<< HEAD
import com.hsbc.ecommerce.config.DatabaseConfig;
import com.hsbc.ecommerce.dao.CustomerDAO;
import com.hsbc.ecommerce.models.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;
=======
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
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc

public class CustomerDAOTest {

    private Connection connection;
<<<<<<< HEAD
    private CustomerDAO customerDAO;

    @Before
    public void setUp() throws SQLException {
        connection = DatabaseConfig.getConnection();
        customerDAO = new CustomerDAO(connection);
    }

    @After
    public void tearDown() {
=======
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

>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
        DatabaseConfig.closeConnection(connection);
    }

    @Test
    public void testSaveCustomer() throws SQLException {
<<<<<<< HEAD
        Customer newCustomer = new Customer();
        newCustomer.setName("John Doe");
        newCustomer.setEmail("johndoe125@example.com");
        newCustomer.setPassword("password123");
        newCustomer.setAddress("123 Main St");
        newCustomer.setPhone("555-1234");
        customerDAO.saveCustomer(newCustomer);
        assertNotNull("Customer ID should not be null after save", newCustomer.getId());
        System.out.println("Customer saved: " + newCustomer.getId());
=======
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
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
    }

    @Test
    public void testGetCustomerById() throws SQLException {
<<<<<<< HEAD
        Customer newCustomer = new Customer();
        newCustomer.setName("Jane Doe");
        newCustomer.setEmail("janedoe@example.com");
        newCustomer.setPassword("password123");
        newCustomer.setAddress("789 Oak St");
        newCustomer.setPhone("555-5678");
        customerDAO.saveCustomer(newCustomer);

        Customer retrievedCustomer = customerDAO.getCustomerById(newCustomer.getId());
        assertNotNull("Customer should be found by ID", retrievedCustomer);
        assertEquals("Customer name should match", newCustomer.getName(), retrievedCustomer.getName());
        System.out.println("Customer retrieved: " + retrievedCustomer.getName());
=======
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
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
    }

    @Test
    public void testGetAllCustomers() throws SQLException {
<<<<<<< HEAD
        List<Customer> customers = customerDAO.getAllCustomers();
        assertNotNull("Customer list should not be null", customers);
        assertTrue("There should be at least one customer", customers.size() > 0);
        System.out.println("All customers retrieved. Total: " + customers.size());
=======
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
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
    }

    @Test
    public void testUpdateCustomer() throws SQLException {
<<<<<<< HEAD
        Customer newCustomer = new Customer();
        newCustomer.setName("Jake Doe");
        newCustomer.setEmail("jakedoe@example.com");
        newCustomer.setPassword("password123");
        newCustomer.setAddress("456 Elm St");
        newCustomer.setPhone("555-9876");
        customerDAO.saveCustomer(newCustomer);

        newCustomer.setAddress("789 Pine St");
        customerDAO.updateCustomer(newCustomer);

        Customer updatedCustomer = customerDAO.getCustomerById(newCustomer.getId());
        assertEquals("Customer address should be updated", "789 Pine St", updatedCustomer.getAddress());
        System.out.println("Customer updated: " + updatedCustomer.getId());
=======
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
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
    }

    @Test
    public void testDeleteCustomer() throws SQLException {
<<<<<<< HEAD
        Customer newCustomer = new Customer();
        newCustomer.setName("Mark Doe");
        newCustomer.setEmail("markdoe@example.com");
        newCustomer.setPassword("password123");
        newCustomer.setAddress("789 Birch St");
        newCustomer.setPhone("555-8765");
        customerDAO.saveCustomer(newCustomer);

        customerDAO.deleteCustomer(newCustomer.getId());

        Customer deletedCustomer = customerDAO.getCustomerById(newCustomer.getId());
        assertNull("Customer should be deleted", deletedCustomer);
        System.out.println("Customer deleted: " + newCustomer.getId());
=======
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
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc
    }
}
