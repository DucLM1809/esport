package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements ProductView {
    ListView lvAdminProducts;
    AdminProductAdapter adminProductAdapter;
    ArrayList<Product> arrayProducts;
    ImageView btnHome, btnCreate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnCreate = (ImageView) findViewById(R.id.adminBtnCreate);
        lvAdminProducts = (ListView) findViewById(R.id.adminListViewProduct);
        arrayProducts = new ArrayList<>();
        adminProductAdapter = new AdminProductAdapter(this, R.layout.admin_product_item, arrayProducts);
        lvAdminProducts.setAdapter(adminProductAdapter);
        ProductPresenter productPresenter = new ProductPresenter(this);
        productPresenter.getAllProducts();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(AdminActivity.this, CreateProductActivity.class);
               startActivity(intent);
            }
        });
    }

    @Override
    public void productsReady(List<Product> products) {
        for (Product product : products) {
            arrayProducts.add(product);
        }
        adminProductAdapter = new AdminProductAdapter(this, R.layout.admin_product_item, arrayProducts);
        lvAdminProducts.setAdapter(adminProductAdapter);
        adminProductAdapter.notifyDataSetChanged();
    }
}