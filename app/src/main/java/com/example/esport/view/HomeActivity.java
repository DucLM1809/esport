package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements ProductView  {

    GridView gvProduct;
    HomeProductAdapter adapter;
    ArrayList<Product> productList;
    ProductPresenter productPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        gvProduct = (GridView) findViewById(R.id.gridProduct);
        productList = new ArrayList<>();
        adapter = new HomeProductAdapter(this, R.layout.home_product_grid_item, productList);
        gvProduct.setAdapter(adapter);
        productPresenter = new ProductPresenter(this);
        productPresenter.getAllProducts();





    }

    @Override
    public void productsReady(List<Product> products) {
        productList.clear();
        Log.d("TAG", products.size()+"");
        for (Product product : products) {
            productList.add(product);
        }
        adapter = new HomeProductAdapter(this, R.layout.home_product_grid_item, productList);
        gvProduct.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}