package com.example.personalcarservicecard.utilClasses;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CarChanges implements Serializable {

    private String tietAddOther;
    private int tietAddMonth;
    private int tietAddYear;

    public CarChanges(String tietAddOther, int tietAddMonth, int tietAddYear) {
        this.tietAddOther = tietAddOther;
        this.tietAddMonth = tietAddMonth;
        this.tietAddYear = tietAddYear;
    }

    public String getOther() {
        return tietAddOther;
    }
    public int getMonth(){
        return tietAddMonth;
    }
    public int getYear(){
        return tietAddYear;
    }

    public void setOther(String other) {
        this.tietAddOther = other;
    }
    public void setMonth(int month) { this.tietAddMonth = month;}
    public void setYear(int year){ this.tietAddYear=year;}

    @NonNull
    @Override
    public String toString() {
        return  tietAddOther +
                " on " + tietAddMonth +
                ", " + tietAddYear ;
    }
}
