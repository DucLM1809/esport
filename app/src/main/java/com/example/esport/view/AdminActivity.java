package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        lvAdminProducts = (ListView) findViewById(R.id.adminListViewProduct);
        arrayProducts = new ArrayList<>();
        adminProductAdapter = new AdminProductAdapter(this, R.layout.admin_product_item, arrayProducts);
        lvAdminProducts.setAdapter(adminProductAdapter);
        ProductPresenter productPresenter = new ProductPresenter(this);
        productPresenter.getAllProducts();

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