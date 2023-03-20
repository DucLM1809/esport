package com.example.esport.view;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.esport.R;
import com.example.esport.model.Cart;
import com.example.esport.model.CartResponse;
import com.example.esport.model.ItemResponse;
import com.example.esport.model.OrderItem;
import com.example.esport.model.Product;
import com.example.esport.presenter.CartPresenter;
import com.example.esport.service.CartRepository;
import com.example.esport.service.CartService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetails extends AppCompatActivity implements CartView {

    ImageView img, back, cart;
    TextView proName, proPrice, proDesc;
    Button btnAddToCart, btnCheckout, btnViewCart;
    TextView dialogName, dialogPrice;
    Button btnDialogCheckout, btnDialogViewCart;
    ImageView imgDialog, imgClose;
    List <OrderItem> orderItemList;
    String name, desc;
    long price,qty;
    String image;
    CartResponse cartResponse = new CartResponse();
    ArrayList <ItemResponse> itemResponseList;
    Product product;
    CartService cartService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        Intent i = getIntent();
        product = (Product) i.getSerializableExtra("product");

        name = product.getName();
        image = product.getImage();
        price = product.getPrice();
        desc = product.getDescription();
        qty = product.getQuantity();

        proName = findViewById(R.id.productName);
        proDesc = findViewById(R.id.prodDesc);
        proPrice = findViewById(R.id.prodPrice);
        img = findViewById(R.id.big_image);
        back = findViewById(R.id.back2);
        cart = findViewById(R.id.cart);
        btnAddToCart = findViewById(R.id.buttonAddToCart);

        proName.setText(name);
        proPrice.setText("$" +price);
        proDesc.setText(desc);


        Glide.with(ProductDetails.this).load(image).into(img);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProductDetails.this, HomeActivity.class);
                startActivity(i);
                finish();

            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ProductDetails.this, ViewCart.class);
                startActivity(i);
                finish();
            }
        });


        itemResponseList = new ArrayList<>();


        CartPresenter cartPresenter = new CartPresenter(this);
        cartService = CartRepository.getCartService();

        Call <Cart> call = cartService.getMyCart();
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart cart = response.body();

                if(cart == null){
                    return;
                }
                List<OrderItem> OrderItemList = new ArrayList<>();
                for(int i=0;i< cart.getProducts().size();i++){
                    OrderItemList.add(cart.getProducts().get(i));
                }
                for(OrderItem orderItem: OrderItemList ){
                    ItemResponse itemResponse = new ItemResponse(orderItem.getId(), orderItem.getCartQuantity());
                    itemResponseList.add(itemResponse);
                }

            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                long id, quantity;
                id =product.getId();
                quantity = 1;
                Log.d("Tungtungtung", itemResponseList.size() + "");

                if(itemResponseList.size() == 0){

                    ItemResponse newItem = new ItemResponse(id,quantity);
                    itemResponseList.add(newItem);
                }

                else{
                    boolean flag = false;
                    for(int i=0; i< itemResponseList.size(); i++){
                        ItemResponse item = itemResponseList.get(i);
                        if(item.getId() == id){
                            item.setQuantity(item.getQuantity()+1);
                            Log.d("IFTREENNENN", item.getId()+ "");
                            flag = true;
                        }
                    }
                    if(!flag){
                        ItemResponse newItem = new ItemResponse(id,1);
                        itemResponseList.add(newItem);
                    }

                }


                cartResponse = new CartResponse(itemResponseList);


                if(cartPresenter.updateMyCart(cartResponse)){
                    Toast.makeText(ProductDetails.this, "Update successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(ProductDetails.this, ViewCart.class);
                    startActivity(i);
                }


            }
        });



    }
    private void DialogAdded(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_added_to_cart);

        dialogName = dialog.findViewById(R.id.textViewNameDialog);
        dialogPrice  = dialog.findViewById(R.id.textViewPriceDialog);
        btnDialogCheckout = dialog.findViewById(R.id.buttonCheckout);
        btnDialogViewCart = dialog.findViewById(R.id.buttonViewCart);
        imgDialog = dialog.findViewById(R.id.imageViewProduct);
        imgClose = dialog.findViewById(R.id.imageViewClose);
        dialogName.setText(name);
        dialogPrice.setText("$"+price);
        Glide.with(ProductDetails.this).load(image).into(imgDialog);
        btnDialogViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProductDetails.this, ViewCart.class);
                startActivity(i);
                finish();
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    @Override
    public void CartReady(List<OrderItem> items) {

        orderItemList = items;


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }


    // this tutorial has been completed
    // see you in the next.
}