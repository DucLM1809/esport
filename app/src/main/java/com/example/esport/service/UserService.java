package com.example.esport.service;

import com.example.esport.model.TokenResponse;
import com.example.esport.model.UserAuthen;
import com.example.esport.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface UserService {
    String REGISTER = "auth/users";
    String LOGIN = "auth/users/tokens";
    String USER = "auth/users/me";

    @POST(REGISTER)
    Call<UserResponse> register(@Body UserAuthen user);

    @POST(LOGIN)
    Call<TokenResponse> login(@Body UserAuthen user);

    @GET(USER)
    Call<UserResponse> getUser();
}
