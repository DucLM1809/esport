package com.example.esport.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.esport.App;
import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.presenter.ProductPresenter;

import java.util.ArrayList;
import java.util.List;

public class AdminActivity extends AppCompatActivity implements ProductView {
    ListView lvAdminProducts;
    AdminProductAdapter adminProductAdapter;
    ArrayList<Product> arrayProducts;
    ImageView btnLogout, btnCreate;
    ProductPresenter productPresenter;
    Context context = App.getInstance().getApplicationContext();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        btnCreate = (ImageView) findViewById(R.id.adminBtnCreate);
        btnLogout = (ImageView) findViewById(R.id.adminBtnLogout);
        lvAdminProducts = (ListView) findViewById(R.id.adminListViewProduct);
        arrayProducts = new ArrayList<>();
        adminProductAdapter = new AdminProductAdapter(this, R.layout.admin_product_item, arrayProducts);
        lvAdminProducts.setAdapter(adminProductAdapter);
        productPresenter = new ProductPresenter(this);
        productPresenter.getAllProducts();

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(AdminActivity.this, CreateProductActivity.class);
               startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("accessToken");
                editor.apply();
                Intent intent = new Intent(AdminActivity.this, SigninActivity.class);
                startActivity(intent);
                Toast.makeText(AdminActivity.this, "Logout Successfully", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public void productsReady(List<Product> products) {
        arrayProducts.clear();
        for (Product product : products) {
            arrayProducts.add(product);
        }
        adminProductAdapter = new AdminProductAdapter(this, R.layout.admin_product_item, arrayProducts);
        lvAdminProducts.setAdapter(adminProductAdapter);
        adminProductAdapter.notifyDataSetChanged();
    }

    private void deleteProduct(long id) {
        if (productPresenter.deleteProduct(id)) {
            Toast.makeText(this, "Delete Product Successfully", Toast.LENGTH_SHORT).show();
        };
    }

    public void DialogDelete(long id) {
        AlertDialog.Builder dialogDelete = new AlertDialog.Builder(this);
        dialogDelete.setMessage("Are you sure to delete this product?");
        dialogDelete.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                deleteProduct(id);
            }
        });

        dialogDelete.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialogDelete.show();
    }
}