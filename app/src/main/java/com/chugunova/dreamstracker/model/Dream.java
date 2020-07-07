package com.chugunova.dreamstracker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Dream {
    @SerializedName("dreamName")
    private String dreamName;

    @SerializedName("dreamDate")
    private String dreamDate;

    @SerializedName("dreamText")
    private String dreamText;

    @SerializedName("dreamDuration")
    private Double dreamDuration;

    public Dream(String dreamName, String dreamDate, String dreamText, Double dreamDuration) {
        this.dreamName = dreamName;
        this.dreamDate = dreamDate;
        this.dreamText = dreamText;
        this.dreamDuration = dreamDuration;
    }

    public Dream(String dreamName, String dreamText, Double dreamDuration) {
        this.dreamName = dreamName;
        this.dreamText = dreamText;
        this.dreamDuration = dreamDuration;
    }

    public String getDreamName() {
        return this.dreamName;
    }

    public void setDreamName(String dreamName) {
        this.dreamName = dreamName;
    }

    public String getDreamDate() {
        return this.dreamDate;
    }

    public void setDreamDate(String dreamDate) {
        this.dreamDate = dreamDate;
    }

    public String getDreamText() {
        return this.dreamText;
    }

    public void setDreamText(String dreamText) {
        this.dreamText = dreamText;
    }

    public Double getDreamDuration() {
        return this.dreamDuration;
    }

    public void setDreamDuration(Double dreamDuration) {
        this.dreamDuration = dreamDuration;
    }

    @NonNull
    @Override
    public String toString() {
        return "Dream{dreamName='" + this.dreamName + '\'' + ", dreamDate='" + this.dreamDate + '\'' + ", dreamText='" + this.dreamText + '\'' + ", dreamDuration=" + this.dreamDuration + '}';
    }
}
