package com.hsbc.ecommerce.models;
import java.util.Date;
import java.util.EnumSet;

public class Subscription {

    private int id;
    private int customerId; // Foreign key to Customer
    private int productId;  // Foreign key to Product
    private EnumSet<SubscriptionType> types; // Set of subscription types
    private Date startDate;
    private Date endDate;
    private String status;
    private Date createdAt;

    public enum SubscriptionType {
        WEEKLY, BIWEEKLY, MONTHLY ,CUSTOM ,SINGLE
    }

    public Subscription() {
        this.types = EnumSet.noneOf(SubscriptionType.class);
        this.createdAt = new Date();
        this.status = "active";
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

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public EnumSet<SubscriptionType> getTypes() {
        return types;
    }

    public void setTypes(EnumSet<SubscriptionType> types) {
        this.types = types;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}

