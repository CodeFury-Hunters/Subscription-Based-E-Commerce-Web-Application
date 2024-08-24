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

public class CustomerDAOTest {

    private Connection connection;
    private CustomerDAO customerDAO;

    @Before
    public void setUp() throws SQLException {
        connection = DatabaseConfig.getConnection();
        customerDAO = new CustomerDAO(connection);
    }

    @After
    public void tearDown() {
        DatabaseConfig.closeConnection(connection);
    }

    @Test
    public void testSaveCustomer() throws SQLException {
        Customer newCustomer = new Customer();
        newCustomer.setName("John Doe");
        newCustomer.setEmail("johndoe125@example.com");
        newCustomer.setPassword("password123");
        newCustomer.setAddress("123 Main St");
        newCustomer.setPhone("555-1234");
        customerDAO.saveCustomer(newCustomer);
        assertNotNull("Customer ID should not be null after save", newCustomer.getId());
        System.out.println("Customer saved: " + newCustomer.getId());
    }

    @Test
    public void testGetCustomerById() throws SQLException {
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
    }

    @Test
    public void testGetAllCustomers() throws SQLException {
        List<Customer> customers = customerDAO.getAllCustomers();
        assertNotNull("Customer list should not be null", customers);
        assertTrue("There should be at least one customer", customers.size() > 0);
        System.out.println("All customers retrieved. Total: " + customers.size());
    }

    @Test
    public void testUpdateCustomer() throws SQLException {
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
    }

    @Test
    public void testDeleteCustomer() throws SQLException {
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
    }
}
