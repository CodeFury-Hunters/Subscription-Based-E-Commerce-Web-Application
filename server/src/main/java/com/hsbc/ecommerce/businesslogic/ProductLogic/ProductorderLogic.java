package com.hsbc.ecommerce.businesslogic.ProductLogic;

import java.util.List;

import com.hsbc.ecommerce.dao.ProductDAO.ProductorderDAO;
import com.hsbc.ecommerce.models.OrderProduct;

public class ProductorderLogic {
    private ProductorderDAO productorderDAO;

    public ProductorderLogic(ProductorderDAO productorderDAO) {
        this.productorderDAO = productorderDAO;
    }

    public void removeProductFromOrder(int orderId, int productId) {
        try {
            // Perform any necessary business logic before deletion
            productorderDAO.deleteOrderProduct(orderId, productId);
            System.out.println("Product with ID " + productId + " removed from order " + orderId);
        } catch (RuntimeException e) {
            System.err.println("Error occurred while removing product from order: " + e.getMessage());
            throw e;
        }
    }

    public List<OrderProduct> getProductsByOrder(int orderId) {
        try {
            // Perform any necessary business logic before retrieving products
            List<OrderProduct> orderProducts = productorderDAO.getOrderProductsByOrderId(orderId);
            if (orderProducts.isEmpty()) {
                System.out.println("No products found for order ID " + orderId);
            }
            return orderProducts;
        } catch (RuntimeException e) {
            System.err.println("Error occurred while retrieving products for order: " + e.getMessage());
            throw e;
        }
    }
}
