package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.presenter.ProductPresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class CreateProductActivity extends AppCompatActivity implements ProductView {
    TextInputEditText etName, etDescription, etPrice, etImage, etTag;
    ImageView btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);

        etName = findViewById(R.id.name);
        etDescription = findViewById(R.id.description);
        etTag = findViewById(R.id.tag);
        etPrice = findViewById(R.id.price);
        etImage = findViewById(R.id.imageURL);
        btnSave = findViewById(R.id.btnSaveProduct);
        ProductPresenter productPresenter = new ProductPresenter(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Click", "click");
                String name = etName.getText().toString();
                String description = etDescription.getText().toString();
                long price = Long.parseLong(etPrice.getText().toString());
                String tag = etTag.getText().toString();
                String image = etImage.getText().toString();
                Product product = new Product(name, description, tag, image, price);
                if (productPresenter.createProduct(product)) {
                    Toast.makeText(CreateProductActivity.this, "Create Product Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateProductActivity.this, AdminActivity.class);
                    startActivity(intent);
                };
            }
        });

    }

    @Override
    public void productsReady(List<Product> products) {

    }
}