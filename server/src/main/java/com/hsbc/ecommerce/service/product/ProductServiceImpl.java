package com.hsbc.ecommerce.service.product;

import com.hsbc.ecommerce.dao.ProductDAO;
import com.hsbc.ecommerce.models.Product;
import com.hsbc.ecommerce.service.product.ProductService;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    public ProductServiceImpl(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void addProduct(Product product) throws SQLException {
        productDAO.saveProduct(product);
    }

    @Override
    public Product getProductById(int id) throws SQLException {
        return productDAO.getProductById(id);
    }

    @Override
    public List<Product> getAllProducts() throws SQLException {
        return productDAO.getAllProducts();
    }

    @Override
    public void updateProduct(Product product) throws SQLException {
        productDAO.updateProduct(product);
    }

    @Override
    public void deleteProduct(int id) throws SQLException {
        productDAO.deleteProduct(id);
    }
}
