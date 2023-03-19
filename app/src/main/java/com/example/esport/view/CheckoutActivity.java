package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.esport.R;
import com.example.esport.model.OrderItem;

import java.util.ArrayList;

public class CheckoutActivity extends AppCompatActivity {

    private WebView webviewmap;
    ListView lvCheckoutList;
    TextView tvShippingAddress,tvCheckoutTotalQuantity, tvCheckoutShippingFee, tvCheckoutTotalPrice, tvCheckoutTotalPriceDown;
    Button btnBuy;
    CheckoutProductAdapter adapter;
    ArrayList<OrderItem> orderItemArrayList;

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
        setTextData();


    };



    private void getIntentData(){
        Intent intent = getIntent();
        orderItemArrayList =(ArrayList<OrderItem>) intent.getSerializableExtra("OrderList");

        adapter = new CheckoutProductAdapter(this, R.layout.checkout_item, orderItemArrayList);
        lvCheckoutList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void setTextData(){
        int totalQuantity=0;
        long totalPrice=0;

        for(OrderItem orderItem : orderItemArrayList){
            totalQuantity += orderItem.getQuantity();
            totalPrice += orderItem.getProduct().getPrice()*orderItem.getQuantity();
            tvCheckoutTotalQuantity.setText(""+totalQuantity);
            tvCheckoutTotalPrice.setText("$"+totalPrice);
            tvCheckoutTotalPriceDown.setText("$"+totalPrice);
            if(totalQuantity!=0) tvCheckoutShippingFee.setText("$7.5");
            else tvCheckoutShippingFee.setText("$0");
        }
    }
}