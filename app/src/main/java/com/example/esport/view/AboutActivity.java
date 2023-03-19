package com.example.esport.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;

import com.example.esport.R;

public class AboutActivity extends AppCompatActivity {

    WebView webviewmap;
    ImageView iconHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        map();
        iconHome = (ImageView) findViewById(R.id.iconHome);
        iconHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AboutActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
    }

    public void map(){
        String iframe = "<iframe src=https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d79453.90043103982!2d106.69374545098233!3d10.776080630193222!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31752efb4f824593%3A0x686bc7c7c6a41382!2zSOG7pyB0w611IG3DrCBCYSDEkMO0!5e0!3m2!1sen!2s!4v1679261068482!5m2!1sen!2s width=600 height=450 style=border:0; allowfullscreen= loading=lazy referrerpolicy=no-referrer-when-downgrade></iframe>";
        webviewmap = (WebView) findViewById(R.id.webViewMap);
        webviewmap.getSettings().setJavaScriptEnabled(true);
        webviewmap.loadData(iframe, "text/html", "utf-8");
    }
}