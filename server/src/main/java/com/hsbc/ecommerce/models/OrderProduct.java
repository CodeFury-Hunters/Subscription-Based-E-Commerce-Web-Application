package com.hsbc.ecommerce.models;

public class OrderProduct {
    private int orderId;
    private int productId;  // Foreign key to Product
    private int quantity;

    public OrderProduct() {
        this.quantity = 1;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}

