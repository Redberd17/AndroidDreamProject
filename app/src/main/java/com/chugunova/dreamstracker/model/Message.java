package com.chugunova.dreamstracker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Message {

    @SerializedName("mesFrom")
    private String mesFrom;

    @SerializedName("mesText")
    private String mesText;

    @SerializedName("mesDateTime")
    private String mesDateTime;

    public Message(String mesFrom, String mesText, String mesDateTime) {
        this.mesFrom = mesFrom;
        this.mesText = mesText;
        this.mesDateTime = mesDateTime;
    }

    public Message(String mesText) {
        this.mesText = mesText;
    }

    public Message() {
    }

    public String getMesFrom() {
        return mesFrom;
    }

    public void setMesFrom(String mesFrom) {
        this.mesFrom = mesFrom;
    }

    public String getMesText() {
        return mesText;
    }

    public void setMesText(String mesText) {
        this.mesText = mesText;
    }

    public String getMesDateTime() {
        return mesDateTime;
    }

    public void setMesDateTime(String mesDateTime) {
        this.mesDateTime = mesDateTime;
    }

    @NonNull
    @Override
    public String toString() {
        return "Message{" +
                "mesFrom='" + mesFrom + '\'' +
                ", mesText='" + mesText + '\'' +
                ", mesDateTime='" + mesDateTime + '\'' +
                '}';
    }
}
