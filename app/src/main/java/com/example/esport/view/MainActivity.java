package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.presenter.UserPresenter;
import com.example.esport.service.ProductRepository;
import com.example.esport.service.ProductService;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecentlyViewedAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ImageView btnSignIn;
    TextInputEditText etEmail, etPassword;
    UserPresenter userPresenter;
    RecyclerView  recentlyViewedRecycler;
    RecentlyViewedAdapter recentlyViewedAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recentlyViewedRecycler = findViewById(R.id.recently_item);
        setRecentlyViewedRecycler();


    }
    private void setRecentlyViewedRecycler() {
        ProductService productService;
        List<Product> arrayProduct;
        productService = ProductRepository.getProductService();

        arrayProduct = new ArrayList<>();

        Call<Product[]> call = productService.getAllProducts();
        try{


            call.enqueue(new Callback<Product[]>() {
                @Override
                public void onResponse(Call<Product[]> call, Response<Product[]> response) {
                    Product[] products = response.body();
                    if(products == null){
                        return;
                    }
                    for(Product product: products){
                        arrayProduct.add(product);
                    }

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
                    recentlyViewedRecycler.setLayoutManager(layoutManager);
                    recentlyViewedAdapter = new RecentlyViewedAdapter(MainActivity.this,R.layout.recently_viewd_items,arrayProduct);
                    recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);

                    Log.d("AG", arrayProduct.size() + "");

                }

                @Override
                public void onFailure(Call<Product[]> call, Throwable t) {

                }

            });

        }catch(Exception e){
            Log.d("loi", e.getMessage());
        }


    }
}