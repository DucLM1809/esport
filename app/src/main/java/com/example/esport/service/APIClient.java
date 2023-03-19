package com.example.esport.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.esport.App;
import com.example.esport.view.MainActivity;
import com.example.esport.view.SigninActivity;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static String baseURL = "https://esport.ttq186.dev/";
    private static Retrofit retrofit;
    static Context context = App.getInstance().getApplicationContext();


    public static Retrofit getClient() {
        SharedPreferences preferences = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String accessToken = preferences.getString("accessToken", "");

        Log.d("AccessToken", accessToken);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        if (accessToken == "") {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        } else {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseURL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }



        return retrofit;
    }
}
