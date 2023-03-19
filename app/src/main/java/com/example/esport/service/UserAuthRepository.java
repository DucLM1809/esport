package com.example.esport.service;

public class UserAuthRepository {
    public static UserService getUserService() {
        return APIClient.getClient().create(UserService.class);
    }
}
