package com.example.esport.presenter;

import android.util.Log;

import com.example.esport.model.Cart;
import com.example.esport.model.CartResponse;
import com.example.esport.model.OrderItem;
import com.example.esport.service.CartRepository;
import com.example.esport.service.CartService;
import com.example.esport.view.CartView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartPresenter {
    private CartView cartView;
    private CartService cartService;

    public CartPresenter(CartView cartView) {
        this.cartView = cartView;
        if(this.cartService == null){
            this.cartService = CartRepository.getCartService();
        }
    }

    public void getMyCart(){
        Call <Cart> call = cartService.getMyCart();
        call.enqueue(new Callback<Cart>() {
            @Override
            public void onResponse(Call<Cart> call, Response<Cart> response) {
                Cart cart = response.body();

                if(cart == null){
                    return;
                }
                Log.d("getCart", "122246: ");
                List<OrderItem> itemList = new ArrayList<>();
                for(int i=0;i< cart.getProducts().size();i++){
                    itemList.add(cart.getProducts().get(i));
                }
                cartView.CartReady(itemList);

            }

            @Override
            public void onFailure(Call<Cart> call, Throwable t) {

            }
        });
    }

    public boolean updateMyCart(CartResponse cartResponse){
        try{
            Call <Cart> call = cartService.updateMyCart(cartResponse);
            call.enqueue(new Callback<Cart>() {
                @Override
                public void onResponse(Call<Cart> call, Response<Cart> response) {
                    getMyCart();
                    if(response.body()!=null){
                        Log.d("thanh cong ne", " tungtungtungtung " );
                    }
                }

                @Override
                public void onFailure(Call<Cart> call, Throwable t) {

                }
            });
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
