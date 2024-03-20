package com.example.esport.model.orderRequest;

import java.util.ArrayList;

public class OrderRequest {
    private long id, userId;
    private String shippingAddress, city;
    private ArrayList<OrderItemRequest> orderItems;

    public OrderRequest(long id, long userId, String shippingAddress, String city, ArrayList<OrderItemRequest> orderItems) {
        this.id = id;
        this.userId = userId;
        this.shippingAddress = shippingAddress;
        this.city = city;
        this.orderItems = orderItems;
    }

    public OrderRequest(String shippingAddress, String city, ArrayList<OrderItemRequest> orderItems) {
        this.shippingAddress = shippingAddress;
        this.city = city;
        this.orderItems = orderItems;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<OrderItemRequest> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItemRequest> orderItems) {
        this.orderItems = orderItems;
    }
}
