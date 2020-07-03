package com.chugunova.dreamstracker.API;

import com.chugunova.dreamstracker.users.User;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Path;

public class ConfigRetrofit {

    private static ConfigRetrofit mInstance;
    private static final String BASE_URL = "http://172.20.10.8:8080";
    private UserAPI mRetrofit;


    private ConfigRetrofit() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(2, TimeUnit.SECONDS)
                .connectTimeout(2, TimeUnit.SECONDS)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(UserAPI.class);
    }


    public static ConfigRetrofit getInstance() {
        if (mInstance == null) {
            mInstance = new ConfigRetrofit();
        }
        return mInstance;
    }

    public Call<User> getUser(@Path("username") String username) {
        return mRetrofit.getUser(username);
    }
}
