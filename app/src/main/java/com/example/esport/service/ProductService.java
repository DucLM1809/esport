package com.example.esport.service;

import com.example.esport.model.Product;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductService {

    String PRODUCT = "products";


    @GET(PRODUCT)
    Call<Product[]> getAllProducts();

    @GET(PRODUCT + "/{id}")
    Call<Product> getProduct(@Path("id") Object id);

    @POST(PRODUCT)
    Call<Product> createProduct(@Body Product product);

    @PUT(PRODUCT + "/{id}")
    Call<Product> updateProduct(@Path("id") Object id, @Body Product product);

    @DELETE(PRODUCT + "/{id}")
    Call<Product> deleteProduct(@Path("id") Object id);
}
