package com.example.esport.model;

import java.util.List;

public class CartResponse {
    private List <ItemResponse> products;

    public CartResponse() {
    }

    public CartResponse(List<ItemResponse> products) {
        this.products = products;
    }

    public List<ItemResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ItemResponse> products) {
        this.products = products;
    }
}
