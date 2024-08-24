package com.hsbc.ecommerce.businesslogic.ProductLogic;
import com.hsbc.ecommerce.dao.ProductDAO.ProductCrudDAO;
import com.hsbc.ecommerce.models.Product;

import java.sql.SQLException;
import java.util.List;

<<<<<<< HEAD:server/src/main/java/com/hsbc/ecommerce/businesslogic/ProductLogic.java
public class ProductLogic {
    private final ProductDAO productDAO;
=======
public class ProductCrudLogic {
    private ProductCrudDAO productDAO;
>>>>>>> d6e911ab4b1a2e222c2682d34d37a6cd36cff2dc:server/src/main/java/com/hsbc/ecommerce/businesslogic/ProductLogic/ProductCrudLogic.java

    public ProductCrudLogic(ProductCrudDAO productDAO) {
        this.productDAO = productDAO;
    }

    public void addProduct(Product product) {
        try {
            productDAO.saveProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateProduct(Product product) {
        try {
            productDAO.updateProduct(product);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteProduct(int productId) {
        try {
            productDAO.deleteProduct(productId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
