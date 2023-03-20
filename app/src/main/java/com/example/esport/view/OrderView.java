package com.example.esport.view;

import com.example.esport.model.orderResponse.OrderResponse;

import java.util.List;

public interface OrderView {
    void orderReady(List<OrderResponse> orders);
}
