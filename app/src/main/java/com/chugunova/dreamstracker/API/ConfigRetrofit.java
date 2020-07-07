package com.chugunova.dreamstracker.API;

import com.chugunova.dreamstracker.model.Dream;
import com.chugunova.dreamstracker.model.User;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Path;

public class ConfigRetrofit {

    private static ConfigRetrofit mInstance;
    private static final String BASE_URL = "http://10.0.2.2:8080";
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

    public Call<List<Dream>> getDreams(@Path("username") String username) {
        return mRetrofit.getDreams(username);
    }

    public Call<ResponseBody> sendDream(@Path("username") String username, @Body Dream dream) {
        return mRetrofit.sendDream(username, dream);
    }
}
