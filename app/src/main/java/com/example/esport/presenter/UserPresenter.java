package com.example.esport.presenter;

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

    public boolean register(UserAuthen user) {
        try {
            Call<UserResponse> call = userService.register(user);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    UserResponse user = response.body();
                    if (user == null) {
                        return;
                    }

                    userAuthView.userAuthReady(user);
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {

                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
