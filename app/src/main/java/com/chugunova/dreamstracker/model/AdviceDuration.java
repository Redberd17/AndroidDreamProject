package com.chugunova.dreamstracker.model;

import com.google.gson.annotations.SerializedName;

import androidx.annotation.NonNull;

public class AdviceDuration {
    @SerializedName("adviceDurName")
    private String adviceDurName;

    @SerializedName("adviceDurValue")
    private Integer adviceDurValue;

    @SerializedName("adviceDurText")
    private String adviceDurText;

    @SerializedName("adviceDurGrade")
    private Integer adviceDurGrade;


    public AdviceDuration(String adviceDurName, Integer adviceDurValue, String adviceDurText, Integer adviceDurGrade) {
        this.adviceDurName = adviceDurName;
        this.adviceDurValue = adviceDurValue;
        this.adviceDurText = adviceDurText;
        this.adviceDurGrade = adviceDurGrade;
    }

    public String getAdviceDurName() {
        return adviceDurName;
    }

    public void setAdviceDurName(String adviceDurName) {
        this.adviceDurName = adviceDurName;
    }

    public Integer getAdviceDurValue() {
        return adviceDurValue;
    }

    public void setAdviceDurValue(Integer adviceDurValue) {
        this.adviceDurValue = adviceDurValue;
    }

    public String getAdviceDurText() {
        return adviceDurText;
    }

    public void setAdviceDurText(String adviceDurText) {
        this.adviceDurText = adviceDurText;
    }

    public Integer getAdviceDurGrade() {
        return adviceDurGrade;
    }

    public void setAdviceDurGrade(Integer adviceDurGrade) {
        this.adviceDurGrade = adviceDurGrade;
    }

    @NonNull
    @Override
    public String toString() {
        return "AdviceDuration{" +
                "adviceDurName='" + adviceDurName + '\'' +
                ", adviceDurValue=" + adviceDurValue +
                ", adviceDurText='" + adviceDurText + '\'' +
                ", adviceDurGrade=" + adviceDurGrade +
                '}';
    }
}
