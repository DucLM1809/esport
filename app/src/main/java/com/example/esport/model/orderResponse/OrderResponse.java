package com.example.esport.model.orderResponse;

import java.util.ArrayList;

public class OrderResponse {
    private long id,userId,totalPrice;
    private String shippingAddress, city;
    private ArrayList<OrderItemResponse> orderItems;

    public OrderResponse(long id, long userId, long totalPrice, String shippingAddress, String city, ArrayList<OrderItemResponse> orderItem) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.shippingAddress = shippingAddress;
        this.city = city;
        this.orderItems = orderItem;
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

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
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

    public ArrayList<OrderItemResponse> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(ArrayList<OrderItemResponse> orderItem) {
        this.orderItems = orderItem;
    }
}
