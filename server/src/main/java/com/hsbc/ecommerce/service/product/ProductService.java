package com.hsbc.ecommerce.service.product;

import com.hsbc.ecommerce.models.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductService {
    Product getProductById(int id) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    String addProductToCart(int productId, int quantity) throws SQLException;
    void addProduct(Product product) throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int id) throws SQLException;
    boolean simulatePaymentProcessing();
    boolean simulateShippingProcessing();
}
