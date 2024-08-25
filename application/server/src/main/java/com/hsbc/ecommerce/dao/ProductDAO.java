package com.hsbc.ecommerce.dao;

import com.hsbc.ecommerce.models.Product;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {
    void saveProduct(Product product) throws SQLException;
    Product getProductById(int id) throws SQLException;
    List<Product> getAllProducts() throws SQLException;
    void updateProduct(Product product) throws SQLException;
    void deleteProduct(int id) throws SQLException;
}
