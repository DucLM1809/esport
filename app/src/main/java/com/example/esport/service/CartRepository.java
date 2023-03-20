package com.example.esport.service;

public class CartRepository {
    public static CartService getCartService() {
        return APIClient.getClient().create(CartService.class);
    }
}
