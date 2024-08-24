package com.hsbc.ecommerce.models;

// Step 1: Define the OrderProduct Interface
public interface OrderProduct {
    int getId();
    void setId(int id);

    int getOrderId();
    void setOrderId(int orderId);

    int getProductId();
    void setProductId(int productId);

    int getQuantity();
    void setQuantity(int quantity);
}

