package com.example.esport.service;

import com.example.esport.model.Cart;
import com.example.esport.model.CartResponse;
import com.example.esport.model.ItemResponse;
import com.example.esport.model.Product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;

public interface CartService {
    String CART = "carts";

    @GET(CART + "/me")
    Call<Cart> getMyCart();

    @PUT(CART + "/me")
    Call<Cart> updateMyCart( @Body CartResponse cartResponse);

    @DELETE(CART + "/me")
    Call<Cart> deleteMyCart();
}
