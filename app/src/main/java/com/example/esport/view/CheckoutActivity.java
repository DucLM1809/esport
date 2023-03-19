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
        map();
    };

    public void map(){

            String iframe = "<iframe src=https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d10916.294500899437!2d106.69465322282875!3d10.810847197658997!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3175289144e07023%3A0x2dc7c386cc4469dc!2zS0lDSEkgS0lDSEkgTmd1eeG7hW4gWMOt!5e0!3m2!1sen!2s!4v1679241231621!5m2!1sen!2s width=600 height=450 style=border:0; allowfullscreen= loading=lazy referrerpolicy=no-referrer-when-downgrade></iframe>";
            webviewmap = (WebView) findViewById(R.id.webViewMap);
            webviewmap.getSettings().setJavaScriptEnabled(true);
            webviewmap.loadData(iframe, "text/html", "utf-8");

    }

    private void getIntentData(){
        Intent intent = getIntent();
        orderItemArrayList =(ArrayList<OrderItem>) intent.getSerializableExtra("OrderList");
        Log.d("TAG", " 5555///"+orderItemArrayList.get(0).getProduct().getQuantity());
        adapter = new CheckoutProductAdapter(this, R.layout.checkout_item, orderItemArrayList);
        lvCheckoutList.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}