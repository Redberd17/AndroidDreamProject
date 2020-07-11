package com.chugunova.dreamstracker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class UserSecurity {

    @SerializedName("username")
    private String userName;

    @SerializedName("token")
    private String token;

    public UserSecurity(String userName, String token) {
        this.userName = userName;
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @NonNull
    @Override
    public String toString() {
        return "UserSecurity{" +
                "userName='" + userName + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
