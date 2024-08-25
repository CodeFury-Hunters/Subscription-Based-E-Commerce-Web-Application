package com.hsbc.ecommerce.models;

public class OrderProduct {
    private int id;                // Unique identifier for the order product
    private int orderId;           // Foreign key to Order
    private int productId;         // Foreign key to Product
    private int quantity;          // Quantity of the product in the order
    private int subscriptionId;    // Foreign key to Subscription (0 if not applicable)

    public OrderProduct() {
        this.subscriptionId = 0; // Default value indicating no subscription
    }

    public OrderProduct(int id, int orderId, int productId, int quantity, int subscriptionId) {
        this.id = id;
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.subscriptionId = subscriptionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "id=" + id +
                ", orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", subscriptionId=" + subscriptionId +
                '}';
    }
}