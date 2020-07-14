package com.chugunova.dreamstracker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class Dream {
    @SerializedName("dreamId")
    private Integer dreamId;

    @SerializedName("dreamName")
    private String dreamName;

    @SerializedName("dreamDate")
    private String dreamDate;

    @SerializedName("dreamText")
    private String dreamText;

    @SerializedName("dreamDuration")
    private Double dreamDuration;

    public Dream(Integer dreamId, String dreamName, String dreamDate, String dreamText, Double dreamDuration) {
        this.dreamId = dreamId;
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

    public Integer getDreamId() {
        return dreamId;
    }

    public void setDreamId(Integer dreamId) {
        this.dreamId = dreamId;
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
        return "Dream{" +
                "dreamId='" + dreamId + '\'' +
                ", dreamName='" + dreamName + '\'' +
                ", dreamDate='" + dreamDate + '\'' +
                ", dreamText='" + dreamText + '\'' +
                ", dreamDuration=" + dreamDuration +
                '}';
    }
}
