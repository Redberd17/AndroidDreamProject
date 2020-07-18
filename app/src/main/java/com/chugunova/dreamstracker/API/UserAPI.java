package com.chugunova.dreamstracker.API;

import com.chugunova.dreamstracker.model.*;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserAPI {

    @POST("/auth/login")
    Call<UserSecurity> authorization(@Body User user);

    @POST("/auth/registration")
    Call<ResponseBody> registration(@Body User user);

    @GET("/users/dreams")
    Call<List<Dream>> getDreams(@Header("Authorization") String token);

    @POST("/users/dreams/dream")
    Call<ResponseBody> sendDream(@Header("Authorization") String token, @Body Dream dream);

    @GET("/users/adviceDurations/{dreamDuration}")
    Call<AdviceDuration> getAdviceDuration(@Header("Authorization") String token, @Path("dreamDuration") Double dreamDuration);

    @DELETE("/users/dreams/dream/{dreamId}")
    Call<ResponseBody> deleteUserDreams(@Header("Authorization") String token, @Path("dreamId") Integer dreamId);

    @GET("/users/messages")
    Call<List<Message>> getAllMessage(@Header("Authorization") String token);

    @POST("/users/messages/message")
    Call<ResponseBody> sendMessage(@Header("Authorization") String token, @Body Message message);
}
