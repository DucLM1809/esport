package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.esport.R;
import com.example.esport.model.Cart;
import com.example.esport.model.CartResponse;
import com.example.esport.model.OrderItem;
import com.example.esport.service.CartRepository;
import com.example.esport.service.CartService;

import java.util.ArrayList;
import java.util.List;

import Adapter.RecentlyViewedAdapter;
import Adapter.ViewCartAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewCart extends AppCompatActivity {
    RecyclerView ViewCartItem;
    ViewCartAdapter viewCartAdapter;
    ImageView back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        ViewCartItem = findViewById(R.id.recyclerViewCart);
        back = findViewById(R.id.backCart);
        viewMyCart();
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ViewCart.this, MainActivity.class);
                startActivity(i);
                finish();

            }
        });
    }

    private void viewMyCart() {
        CartService cartService;
        List<OrderItem> arrayItem;
        cartService = CartRepository.getCartService();
        arrayItem = new ArrayList<>();

        Call<Cart> call = cartService.getMyCart();
        try{


            call.enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    Cart cart = response.body();
                    if(cart == null){
                        return;
                    }
                    for(int i=0; i<cart.getProducts().size();i++){
                        arrayItem.add(cart.getProducts().get(i));
                    }

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ViewCart.this, LinearLayoutManager.HORIZONTAL, false);
                    ViewCartItem.setLayoutManager(layoutManager);
                    viewCartAdapter = new ViewCartAdapter(ViewCart.this,R.layout.item_cart_row,arrayItem);
                    ViewCartItem.setAdapter(viewCartAdapter);


                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {
                    Log.d("TAaaaaaG", "nhucut: ");
                }

            });

        }catch(Exception e){
            Log.d("loi", e.getMessage());
        }
    }
}