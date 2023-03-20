package com.example.esport.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.model.TokenResponse;
import com.example.esport.model.UserResponse;
import com.example.esport.presenter.ProductPresenter;
import com.example.esport.presenter.UserPresenter;


import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ProductView,UserAuthView  {

    GridView gvProduct;
    HomeProductAdapter adapter;
    ArrayList<Product> productList;
    ProductPresenter productPresenter;
    UserPresenter userPresenter;
    UserResponse userRes;
    TextView tvHello;
    ImageView iconAbout,iconProfile;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        gvProduct = (GridView) findViewById(R.id.gridProduct);
        productList = new ArrayList<>();
        adapter = new HomeProductAdapter(this, R.layout.home_product_grid_item, productList);
        gvProduct.setAdapter(adapter);
        tvHello = (TextView) findViewById(R.id.tvHello) ;
        productPresenter = new ProductPresenter(this);
        productPresenter.getAllProducts();
        userPresenter = new UserPresenter(this);
        userPresenter.getUser();
        iconAbout = (ImageView) findViewById(R.id.iconAbout);
        iconProfile = (ImageView) findViewById(R.id.iconProfile);

        iconProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(intent);
            }
        });

        iconAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,AboutActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void productsReady(List<Product> products) {

        productList.clear();
        for (Product product : products) {
            productList.add(product);
        }
        adapter = new HomeProductAdapter(this, R.layout.home_product_grid_item, productList);
        gvProduct.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void userAuthReady(UserResponse user) {

    }

    @Override
    public void loginReady(TokenResponse token) {

    }

    @Override
    public void userReady(UserResponse user) {
        userRes = user;
        tvHello.setText("Hello "+user.getEmail());

    }

    @Override
    public void userError(String err) {

    }


}