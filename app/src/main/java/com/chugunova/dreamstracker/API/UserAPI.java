package com.chugunova.dreamstracker.API;

import com.chugunova.dreamstracker.users.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface UserAPI {

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);
}
