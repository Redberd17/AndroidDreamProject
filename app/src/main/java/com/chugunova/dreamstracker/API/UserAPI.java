package com.chugunova.dreamstracker.API;

import com.chugunova.dreamstracker.model.Dream;
import com.chugunova.dreamstracker.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserAPI {

    @GET("users/{username}")
    Call<User> getUser(@Path("username") String username);

    @GET("users/dreams/{username}")
    Call<List<Dream>> getDreams(@Path("username") String username);

    @POST("users/dreams/dream/{username}")
    Call<ResponseBody> sendDream(@Path("username") String username, @Body Dream dream);
}
