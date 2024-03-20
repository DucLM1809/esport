package com.example.esport.service;



import com.example.esport.model.orderRequest.OrderRequest;
import com.example.esport.model.orderResponse.OrderResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
public interface OrderService {
    String ORDER = "orders";


    @GET(ORDER)
    Call<OrderResponse[]> getAllOrders();

    @GET(ORDER + "/{id}")
    Call<OrderResponse> getOrder(@Path("id") Object id);

    @POST(ORDER)
    Call<OrderResponse> createOrder(@Body OrderRequest orderRequest);

    @DELETE(ORDER + "/{id}")
    Call<OrderResponse> deleteOrder(@Path("id") Object id);
}
