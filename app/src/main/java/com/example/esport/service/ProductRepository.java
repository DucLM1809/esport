package com.example.esport.service;

import android.util.Log;

public class ProductRepository {
    public static ProductService getProductService() {
        return APIClient.getClient().create(ProductService.class);
    }
}
