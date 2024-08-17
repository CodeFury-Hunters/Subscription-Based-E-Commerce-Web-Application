package com.hsbc.ecommerce.models;

import java.util.Date;
import java.util.List;

public class Customer {
    private int id;
    private String name;
    private String email;
    private String password;
    private String address;
    private String phone;
    private Date createdAt;
    private List<Integer> subscriptions; // List of Subscription IDs
    private List<Integer> orderHistory;  // List of Order IDs

    public Customer() {
        this.createdAt = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Integer> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(List<Integer> subscriptions) {
        this.subscriptions = subscriptions;
    }

    public List<Integer> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Integer> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
