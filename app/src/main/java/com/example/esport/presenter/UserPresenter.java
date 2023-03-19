package com.example.esport.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.example.esport.App;
import com.example.esport.model.TokenResponse;
import com.example.esport.model.UserAuthen;
import com.example.esport.model.UserResponse;
import com.example.esport.service.UserAuthRepository;
import com.example.esport.service.UserService;
import com.example.esport.view.UserAuthView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter {
    private UserAuthView userAuthView;
    private UserService userService;

    public UserPresenter(UserAuthView view) {
        this.userAuthView = view;
        if (this.userService == null) {
            this.userService = UserAuthRepository.getUserService();
        }
    }

    public void register(UserAuthen user) {
        Call<UserResponse> call = userService.register(user);
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse user = response.body();

                userAuthView.userAuthReady(user);
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

    public void login(UserAuthen user) {
        Call<TokenResponse> call = userService.login(user);
        call.enqueue(new Callback<TokenResponse>() {
            @Override
            public void onResponse(Call<TokenResponse> call, Response<TokenResponse> response) {
                TokenResponse tokenResponse = response.body();

                if (tokenResponse != null) {
                    userAuthView.loginReady(tokenResponse);
                }

            }

            @Override
            public void onFailure(Call<TokenResponse> call, Throwable t) {

            }
        });
    }

    public void getUser() {
        Call<UserResponse> call = userService.getUser();
        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                UserResponse userResponse = response.body();
                if (response.body() != null) {
                    Log.d("Test", userResponse.getEmail());
                    userAuthView.userReady(userResponse);
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });
    }
}
