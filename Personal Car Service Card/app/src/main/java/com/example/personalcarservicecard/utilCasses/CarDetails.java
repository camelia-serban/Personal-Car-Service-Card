package com.example.personalcarservicecard.utilClasses;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CarDetails implements Serializable {
    private String tietAddModel;
    private int addFabYear;
    private String brand;

    public CarDetails(String brand, String tietAddModel, int addFabYear) {
        this.brand = brand;
        this.tietAddModel = tietAddModel;
        this.addFabYear = addFabYear;

    }

    public String getModel() {
        return tietAddModel;
    }
    public int getYear() {
        return addFabYear;
    }
    public String getBrand() {
        return brand;
    }

    public void setModel(String tietAddModel) {
        this.tietAddModel = tietAddModel;
    }
    public void setYear(String tietAddYear) {
        this.addFabYear = addFabYear;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }


    @NonNull
    @Override
    public String toString() {
        return brand + " , " + tietAddModel + " from " + addFabYear;
    }
}
