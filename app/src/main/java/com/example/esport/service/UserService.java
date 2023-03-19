package com.example.esport.service;

import com.example.esport.model.UserAuthen;
import com.example.esport.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    String REGISTER = "auth/users";

    @POST(REGISTER)
    Call<UserResponse> register(@Body UserAuthen user);
}
