package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.esport.R;
import com.example.esport.model.OrderItem;
import com.example.esport.model.orderRequest.OrderItemRequest;
import com.example.esport.model.orderRequest.OrderRequest;
import com.example.esport.model.orderResponse.OrderResponse;
import com.example.esport.presenter.OrderPresenter;

import java.util.ArrayList;
import java.util.List;

public class CheckoutActivity extends AppCompatActivity implements OrderView{

    private WebView webviewmap;
    ListView lvCheckoutList;
    TextView tvCheckoutTotalQuantity, tvCheckoutShippingFee, tvCheckoutTotalPrice, tvCheckoutTotalPriceDown;
    ImageView iconback;
    EditText etShippingAddress, etCity;
    Button btnBuy;
    CheckoutProductAdapter adapter;
    ArrayList<OrderItem> orderItemArrayList;
    int totalQuantity=0;
    long totalPrice=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        orderItemArrayList = new ArrayList<>();
        lvCheckoutList = (ListView) findViewById(R.id.lvCheckOutList);
        getIntentData();

        tvCheckoutShippingFee=(TextView) findViewById(R.id.tvCheckoutShippingFee);
        tvCheckoutTotalQuantity=(TextView) findViewById(R.id.tvCheckoutTotalQuantity);
        tvCheckoutTotalPrice=(TextView) findViewById(R.id.tvCheckoutTotalPrice);
        tvCheckoutTotalPriceDown=(TextView) findViewById(R.id.tvCheckoutTotalPriceDown);
        etShippingAddress = (EditText) findViewById(R.id.edShipAddress);
        etCity = (EditText) findViewById(R.id.etCity) ;
        iconback = (ImageView) findViewById(R.id.iconback);

        btnBuy = (Button) findViewById(R.id.tvCheckoutBuy) ;

        iconback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CheckoutActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });



        setTextData();

        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkInput()) return;
                String shippingAddress = etShippingAddress.getText().toString();
                String city = etCity.getText().toString();
                ArrayList<OrderItemRequest> orderItemRequestArrayList = new ArrayList<>();
                for(OrderItem item: orderItemArrayList){
                    orderItemRequestArrayList.add(new OrderItemRequest(item.getId(),item.getCartQuantity(), item.getPrice()*item.getCartQuantity()));
                }
                OrderRequest order = new OrderRequest(shippingAddress,city,orderItemRequestArrayList);
                OrderPresenter orderPresenter = new OrderPresenter(CheckoutActivity.this);
                orderPresenter.createOrder(order);
                Intent intent = new Intent(CheckoutActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


    };

    private boolean checkInput(){
        if(TextUtils.isEmpty(etShippingAddress.getText().toString())){
            etShippingAddress.setError("REQUIRE");
            return false;
        }

        if(TextUtils.isEmpty((etCity.getText().toString()))){
            etCity.setError("REQUIRE");
            return false;
        }

        return true;
    }

    public void setEnableButton(){
        if(totalQuantity==0){
            btnBuy.setEnabled(false);
        }else {
            btnBuy.setEnabled(true);
        }
    }



    private void getIntentData(){
        Intent intent = getIntent();
        orderItemArrayList =(ArrayList<OrderItem>) intent.getSerializableExtra("OrderList");

        adapter = new CheckoutProductAdapter(this, R.layout.checkout_item, orderItemArrayList);
        lvCheckoutList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setTextData(){


        for(OrderItem orderItem : orderItemArrayList){
            totalQuantity += orderItem.getCartQuantity();

            totalPrice += orderItem.getPrice()*orderItem.getCartQuantity();

            tvCheckoutTotalQuantity.setText(""+totalQuantity);
            tvCheckoutTotalPrice.setText("$"+totalPrice);
            tvCheckoutTotalPriceDown.setText("$"+totalPrice);
            if(totalQuantity!=0) tvCheckoutShippingFee.setText("$7.5");
            else tvCheckoutShippingFee.setText("$0");
        }
    }

    @Override
    public void orderReady(List<OrderResponse> orders) {

    }
}