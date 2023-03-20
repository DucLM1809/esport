package com.example.esport.view;

import com.example.esport.model.ItemResponse;
import com.example.esport.model.OrderItem;
import com.example.esport.model.Product;

import java.util.List;

public interface CartView {
    void CartReady(List<OrderItem> items);
}
