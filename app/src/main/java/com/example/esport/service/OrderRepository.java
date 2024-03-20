package com.example.esport.service;

public class OrderRepository {
    public static OrderService getOrderService() {
        return APIClient.getClient().create(OrderService.class);
    }
}
