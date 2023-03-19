package com.example.esport.presenter;


import com.example.esport.model.Product;
import com.example.esport.service.ProductRepository;
import com.example.esport.service.ProductService;
import com.example.esport.view.ProductView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductPresenter {
    private ProductView productView;
    private ProductService productService;

    public ProductPresenter(ProductView view) {
        this.productView = view;
        if (this.productService == null) {
            this.productService = ProductRepository.getProductService();
        }
    }

    public void getAllProducts() {
        Call<Product[]> call = productService.getAllProducts();
        call.enqueue(new Callback<Product[]>() {
            @Override
            public void onResponse(Call<Product[]> call, Response<Product[]> response) {
                Product[] products = response.body();

                if (products == null) {
                    return;
                }

                List<Product> productList = new ArrayList<>();
                for (Product product : products) {
                    productList.add(product);
                }

                productView.productsReady(productList);
            }

            @Override
            public void onFailure(Call<Product[]> call, Throwable t) {
                try {
                    throw new InterruptedException("Something went wrong!");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public boolean createProduct(Product product) {
        try {
            Call<Product> call = productService.createProduct(product);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    if (response.body() != null) {
                        getAllProducts();
                    }
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean updateProduct(Product product, long id) {
        try {
            Call<Product> call = productService.updateProduct(id, product);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    getAllProducts();
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteProduct (long id) {
        try {
            Call<Product> call = productService.deleteProduct(id);
            call.enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Call<Product> call, Response<Product> response) {
                    getAllProducts();
                }

                @Override
                public void onFailure(Call<Product> call, Throwable t) {

                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
