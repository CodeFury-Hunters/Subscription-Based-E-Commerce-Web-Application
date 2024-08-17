package com.hsbc.ecommerce.businesslogic;

import com.hsbc.ecommerce.dao.AdminDAO;
import com.hsbc.ecommerce.dao.ProductDAO;
import com.hsbc.ecommerce.dao.SubscriptionDAO;
import com.hsbc.ecommerce.models.Admin;
import com.hsbc.ecommerce.models.Product;
import com.hsbc.ecommerce.models.Subscription;

import java.sql.SQLException;
import java.util.List;

public class AdminLogic {

    private AdminDAO adminDAO;
    private SubscriptionDAO subscriptionDAO;
    private ProductDAO productDAO;

    public AdminLogic(AdminDAO adminDAO, SubscriptionDAO subscriptionDAO,ProductDAO productDAO) {
        this.adminDAO = adminDAO;
        this.subscriptionDAO = subscriptionDAO;
        this.productDAO = productDAO;
    }

    // Handle admin login
    public boolean login(String email, String password) throws SQLException {
        Admin admin = adminDAO.getAdminByEmail(email);
        return admin != null && admin.getPassword().equals(password);
    }

    // Manage products
    public void addProduct(Product product) throws SQLException {
         productDAO.saveProduct(product);
    }

    public void updateProduct(Product product) throws SQLException {
         productDAO.updateProduct(product);
    }

    public void deleteProduct(int productId) throws SQLException {
         productDAO.deleteProduct(productId);
    }

    // Manage subscriptions
    public void defineSubscription(Subscription subscription) throws SQLException {
        subscriptionDAO.saveSubscription(subscription);
    }

    public void activateSubscription(int subscriptionId) throws SQLException {
        Subscription subscription = subscriptionDAO.getSubscriptionById(subscriptionId);
        if (subscription != null) {
            subscription.setStatus("active");
            subscriptionDAO.updateSubscription(subscription);
        }
    }

    public void deactivateSubscription(int subscriptionId) throws SQLException {
        Subscription subscription = subscriptionDAO.getSubscriptionById(subscriptionId);
        if (subscription != null) {
            subscription.setStatus("inactive");
            subscriptionDAO.updateSubscription(subscription);
        }
    }

    // View active/inactive subscriptions
    public List<Subscription> viewSubscriptions() throws SQLException {
        return subscriptionDAO.getAllSubscriptions();
    }
}
