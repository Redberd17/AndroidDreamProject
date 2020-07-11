package com.chugunova.dreamstracker.API;

import com.chugunova.dreamstracker.model.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.*;

public class ConfigRetrofit {

    private static ConfigRetrofit mInstance;
    private static final String BASE_URL = "http://10.0.2.2:8080";
    //private static final String BASE_URL = "http://172.20.10.4:8080";

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


    public Call<UserSecurity> authorizationUser(@Body User user) {
        return mRetrofit.authorization(user);
    }

    /*public Call<User> getUser(@Path("username") String username) {
        return mRetrofit.getUser(username);
    }*/

    public Call<List<Dream>> getDreams(@Header("Authorization") String token) {
        return mRetrofit.getDreams(token);
    }

    public Call<ResponseBody> sendDream(@Header("Authorization") String token, @Body Dream dream) {
        return mRetrofit.sendDream(token, dream);
    }

    public Call<AdviceDuration> getAdviceDuration(@Header("Authorization") String token, @Path("adviceDuration") Double dreamDuration) {
        return mRetrofit.getAdviceDuration(token, dreamDuration);
    }
}
