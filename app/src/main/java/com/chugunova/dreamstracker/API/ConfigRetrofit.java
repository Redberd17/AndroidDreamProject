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
    //private static final String BASE_URL = "http://10.0.2.2:8080";
    private static final String BASE_URL = "http://192.168.43.40:8080";

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

    public Call<ResponseBody> registrationUser(@Body User user) {
        return mRetrofit.registration(user);
    }

    public Call<List<Dream>> getDreams(@Header("Authorization") String token) {
        return mRetrofit.getDreams(token);
    }

    public Call<ResponseBody> sendDream(@Header("Authorization") String token, @Body Dream dream) {
        return mRetrofit.sendDream(token, dream);
    }

    public Call<AdviceDuration> getAdviceDuration(@Header("Authorization") String token, @Path("adviceDuration") Double dreamDuration) {
        return mRetrofit.getAdviceDuration(token, dreamDuration);
    }

    public Call<ResponseBody> deleteUserDreams(@Header("Authorization") String token, @Path("dreamId") Integer dreamId) {
        return mRetrofit.deleteUserDreams(token, dreamId);
    }

    public Call<ResponseBody> sendMessage(@Header("Authorization") String token, @Body Message message) {
        return mRetrofit.sendMessage(token, message);
    }

    public Call<List<Message>> getAllMessage(@Header("Authorization") String token) {
        return mRetrofit.getAllMessage(token);
    }
}
