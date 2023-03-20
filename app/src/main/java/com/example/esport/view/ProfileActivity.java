package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.esport.R;
import com.example.esport.model.TokenResponse;
import com.example.esport.model.UserResponse;
import com.example.esport.model.orderResponse.OrderResponse;
import com.example.esport.presenter.OrderPresenter;
import com.example.esport.presenter.UserPresenter;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity implements OrderView,UserAuthView {

    TextView tvUsername;
    ListView lvOrder;
    ProfileAdapter adapter;
    OrderPresenter orderPresenter;
    UserPresenter userPresenter;
    ArrayList<OrderResponse> orderResponseList = new ArrayList<>();
    ImageView iconHome,iconCart,iconAbout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvUsername=(TextView) findViewById(R.id.tvProfileName);
        lvOrder =(ListView) findViewById(R.id.lvProfileOrders);
        orderPresenter = new OrderPresenter(this);
        userPresenter = new UserPresenter(this);
        iconHome=(ImageView)findViewById(R.id.iconHome);
        iconCart=(ImageView)findViewById(R.id.iconCart);
        iconAbout=(ImageView)findViewById(R.id.iconAbout);

        orderPresenter.getAllOrders();
        userPresenter.getUser();

        iconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, HomeActivity.class);
                startActivity(i);
                finish();

            }
        });

        iconAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(ProfileActivity.this, AboutActivity.class);
                startActivity(i);
                finish();

            }
        });

        iconCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileActivity.this, CartView.class);
                startActivity(i);
                finish();

            }
        });


    }

    @Override
    public void orderReady(List<OrderResponse> orders) {
        orderResponseList.clear();
        for(OrderResponse orderResponse : orders){
            orderResponseList.add(orderResponse);
        }
        Log.d("TAG", "Profile" + orderResponseList.size());
        adapter = new ProfileAdapter(this,R.layout.profile_orders_item,orderResponseList);
        lvOrder.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void userAuthReady(UserResponse user) {

    }

    @Override
    public void loginReady(TokenResponse token) {

    }

    @Override
    public void userReady(UserResponse user) {
        UserResponse userRes = user;
        tvUsername.setText(userRes.getEmail());
    }

    @Override
    public void userError(String err) {

    }
}