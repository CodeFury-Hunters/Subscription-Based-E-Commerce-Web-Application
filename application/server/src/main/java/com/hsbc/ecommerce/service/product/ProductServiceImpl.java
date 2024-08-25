package com.hsbc.ecommerce.service.product;

import com.hsbc.ecommerce.CustomExceptions.ExceptionFactory;
import com.hsbc.ecommerce.dao.ProductDAO;
import com.hsbc.ecommerce.models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDao;

    public ProductServiceImpl(ProductDAO productDao) {
        this.productDao = productDao;
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        Product product = productDao.getProductById(id);
        if (product == null) {
            throw ExceptionFactory.createException("CustomerNotFoundException", "Product not found.");
        }
        return product;
    }

    @Override
    public List<Product> getAllProducts() throws SQLException{
        return productDao.getAllProducts();
    }

    @Override
    public String addProductToCart(int productId, int quantity) throws SQLException{
        Product product = productDao.getProductById(productId);
        if (product == null) {
            throw ExceptionFactory.createException("InvalidOrderException", "Product does not exist.");
        }
        if (product.getQuantity() < quantity) {
            throw ExceptionFactory.createException("OutOfStockException", "Product is out of stock.");
        }
        return "Product added to cart.";
    }

    public String processOrder(int orderId) {
        boolean isProcessing = simulateOrderProcessing();
        if (!isProcessing) {
            throw ExceptionFactory.createException("OrderProcessingException", "Order processing failed.");
        }
        return "Order processed.";
    }

    private boolean simulateOrderProcessing() {
        try {
            // Simulate a delay in order processing to mimic real-time processing delay
            Thread.sleep(1000); 
    
            double randomOutcome = Math.random();  // Generates a random number between 0 and 1
            if (randomOutcome > 0.7) {  // 30% chance of failure
                return false;  // Simulate a processing failure
            }
            return true;
    
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
    

    @Override
    public void addProduct(Product product) throws SQLException {
        if (productDao.getProductById(product.getId()) != null) {
            throw ExceptionFactory.createException("DuplicateEntityException", "Product already exists.");
        }
        productDao.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        productDao.deleteProduct(id);
    }

    public boolean simulatePaymentProcessing() {
        try {
            // Simulate a delay in order processing to mimic real-time processing delay
            Thread.sleep(1000); 
    
            double randomOutcome = Math.random();  // Generates a random number between 0 and 1
            if (randomOutcome > 0.7) {  // 30% chance of failure
                return false;  // Simulate a processing failure
            }
            return true;
    
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private void processPayment(double amount) {
        if (amount <= 0) {
            throw ExceptionFactory.createException("InvalidInputException", "Invalid payment amount.");
        }
        boolean paymentSuccess = simulatePaymentProcessing();
        if (!paymentSuccess) {
            throw ExceptionFactory.createException("PaymentFailedException", "Payment processing failed.");
        }
        System.out.println("Payment processed successfully.");
    }

    private void processShipping(String address) {
        if (address == null || address.trim().isEmpty()) {
            throw ExceptionFactory.createException("ShippingException", "Invalid shipping address.");
        }
        boolean shippingSuccess = simulateShippingProcessing();
        if (!shippingSuccess) {
            throw ExceptionFactory.createException("ShippingException", "Shipping failed.");
        }
        System.out.println("Shipping processed successfully.");
    }

    public boolean simulateShippingProcessing() {
        try {
            // Simulate a delay in order processing to mimic real-time processing delay
            Thread.sleep(1000); 
    
            double randomOutcome = Math.random();  // Generates a random number between 0 and 1
            if (randomOutcome > 0.7) {  // 30% chance of failure
                return false;  // Simulate a processing failure
            }
            return true;
    
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
    }
}