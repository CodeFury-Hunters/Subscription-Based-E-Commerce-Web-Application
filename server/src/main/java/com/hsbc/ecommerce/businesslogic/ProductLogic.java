package com.hsbc.ecommerce.businesslogic;

import com.hsbc.ecommerce.dao.ProductDAO;
import com.hsbc.ecommerce.models.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductLogic {
    private ProductDAO productDAO;

    public ProductLogic(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Product product) {
        // Business logic for adding a product
        try {
            productDAO.saveProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product product) {
        // Business logic for updating a product
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int productId) {
        // Business logic for deleting a product
        try {
            productDAO.deleteProduct(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        // Business logic for retrieving all products
        try {
            return productDAO.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
