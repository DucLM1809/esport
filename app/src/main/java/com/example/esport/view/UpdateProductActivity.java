package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.esport.R;
import com.example.esport.model.Product;
import com.example.esport.presenter.ProductPresenter;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

public class UpdateProductActivity extends AppCompatActivity implements ProductView {
    TextInputEditText etName, etDescription, etPrice, etImage, etTag, etQuantity;
    ImageView btnSave, btnHome;
    private String name, description, tag, image;
    private long price, quantity, id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product);

        etName = findViewById(R.id.editName);
        etDescription = findViewById(R.id.editDescription);
        etTag = findViewById(R.id.editTag);
        etPrice = findViewById(R.id.editPrice);
        etImage = findViewById(R.id.editImageURL);
        etQuantity = findViewById(R.id.editQuantity);
        btnSave = findViewById(R.id.btnSaveEditProduct);
        btnHome = findViewById(R.id.adminBtnHomeEdit);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            name = bundle.get("name").toString();
            description = bundle.get("description").toString();
            image = bundle.get("image").toString();
            tag = bundle.get("tag").toString();
            quantity = Long.parseLong(bundle.get("quantity").toString());
            price = Long.parseLong(bundle.get("price").toString());
            id = Long.parseLong(bundle.get("id").toString());
        }

        etName.setText(name);
        etDescription.setText(description);
        etTag.setText(tag);
        etImage.setText(image);
        etPrice.setText(price + "");
        etQuantity.setText(quantity + "");

        ProductPresenter productPresenter = new ProductPresenter(this);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = etName.getText().toString();
                description = etDescription.getText().toString();
                tag = etTag.getText().toString();
                image = etImage.getText().toString();
                price = Long.parseLong(etPrice.getText().toString());
                quantity = Long.parseLong(etQuantity.getText().toString());

                Product product = new Product(name, description, tag, image, price, quantity);
                if (productPresenter.updateProduct(product, id)) {
                    Toast.makeText(UpdateProductActivity.this, "Update Product Successfully!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UpdateProductActivity.this, AdminActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdateProductActivity.this, AdminActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void productsReady(List<Product> products) {

    }
}