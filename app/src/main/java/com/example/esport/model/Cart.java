package com.example.esport.model;

import java.util.ArrayList;

public class Cart {
    private long id;
    private long userId;
    private ArrayList<OrderItem> products;

    public Cart(long userId, ArrayList<OrderItem> products) {
        this.userId = userId;
        this.products = products;
    }

    public Cart(long id, long userId, ArrayList<OrderItem> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
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

    public ArrayList<OrderItem> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderItem> products) {
        this.products = products;
    }

}
