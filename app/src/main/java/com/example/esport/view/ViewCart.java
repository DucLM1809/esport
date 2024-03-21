package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esport.R;
import com.example.esport.model.Cart;
import com.example.esport.model.CartResponse;
import com.example.esport.model.ItemResponse;
import com.example.esport.model.OrderItem;
import com.example.esport.presenter.CartPresenter;
import com.example.esport.service.CartRepository;
import com.example.esport.service.CartService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCart extends AppCompatActivity implements CartView {
    CartResponse cartResponse;
    ArrayList <ItemResponse> itemResponseList;
    CartService cartService;
    Button btnCheckout;
    RecyclerView ViewCartItem;
    ViewCartAdapter viewCartAdapter;
    ImageView back, iconHome, iconAbout, iconProfile;
    List <OrderItem> orderItemList;
    TextView tvTotal;
    long total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        orderItemList = new ArrayList<>();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        ViewCartItem = findViewById(R.id.recyclerViewCart);
        back = findViewById(R.id.backCart);
        tvTotal = findViewById(R.id.textViewTotal);
        btnCheckout = findViewById(R.id.buttonCheckoutCart);
        iconHome = (ImageView) findViewById(R.id.iconHome);
        iconAbout = (ImageView) findViewById(R.id.iconAbout);
        iconProfile = (ImageView) findViewById(R.id.iconProfile);

        iconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ViewCart.this, HomeActivity.class);
                startActivity(i);
                finish();

            }
        });

        iconAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ViewCart.this, AboutActivity.class);
                startActivity(i);
                finish();

            }
        });

        iconProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ViewCart.this, ProfileActivity.class);
                startActivity(i);
                finish();

            }
        });

        viewMyCart();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ViewCart.this, HomeActivity.class);
                startActivity(i);
                finish();

            }
        });

    }

    private void viewMyCart() {


        CartPresenter cartPresenter = new CartPresenter(this);
        cartPresenter.getMyCart();
        Log.d("orderItemList", orderItemList.size() + "");

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewCart.this, LinearLayoutManager.VERTICAL, false);
        ViewCartItem.setLayoutManager(layoutManager);


    }

    public void deleteCartItem(int i){
        CartPresenter cartPresenter = new CartPresenter(this);

        itemResponseList = new ArrayList<>();
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
                itemResponseList.remove(i);

                cartResponse = new CartResponse(itemResponseList);
                cartPresenter.updateMyCart(cartResponse);

            }



            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });



    }


    @Override
    public void CartReady(List<OrderItem> items) {
        total = 0;
        orderItemList = items;
        for (OrderItem orderItem: orderItemList){
            total = total + orderItem.getPrice()*orderItem.getCartQuantity();
        }
        tvTotal.setText("$"+total);


        viewCartAdapter = new ViewCartAdapter(ViewCart.this,R.layout.item_cart_row,orderItemList);
        ViewCartItem.setAdapter(viewCartAdapter);
        viewCartAdapter.notifyDataSetChanged();

        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ViewCart.this, CheckoutActivity.class);
                i.putExtra("OrderList", (ArrayList)orderItemList);
                startActivity(i);
            }
        });
    }

    public void setDataCartItem(int i, long qty) {
        CartPresenter cartPresenter = new CartPresenter(this);

        itemResponseList = new ArrayList<>();
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
                itemResponseList.get(i).setQuantity(qty);

                cartResponse = new CartResponse(itemResponseList);
                cartPresenter.updateMyCart(cartResponse);

            }



            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

}