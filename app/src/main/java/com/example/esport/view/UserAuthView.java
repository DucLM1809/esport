package com.example.esport.view;

import com.example.esport.model.TokenResponse;
import com.example.esport.model.UserResponse;

public interface UserAuthView {
    void userAuthReady(UserResponse user);
    void loginReady(TokenResponse token);
    void userReady(UserResponse user);
}

