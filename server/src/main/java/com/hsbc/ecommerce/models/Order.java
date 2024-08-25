package com.hsbc.ecommerce.models;

import com.hsbc.ecommerce.models.OrderProduct;

import java.util.Date;
import java.util.List;

public class Order {
    private int id;
    private int customerId;       // Foreign key to Customer
    private List<OrderProduct> orderProducts; // List of OrderProducts (both with and without subscriptions)
    private Date orderDate;
    private Date deliveryDate;
    private String status;
    private double totalAmount;

    public Order() {
        this.orderDate = new Date();
        this.status = "pending";
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}