package com.chugunova.dreamstracker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class User {

    @SerializedName("username")
    private String userName;

    @SerializedName("password")
    private String userPassword;

    public User(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @NonNull
    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                '}';
    }
}
