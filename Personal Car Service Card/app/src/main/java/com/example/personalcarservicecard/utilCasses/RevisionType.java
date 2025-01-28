package com.example.personalcarservicecard.utilClasses;

import android.app.DatePickerDialog;

import androidx.annotation.NonNull;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RevisionType implements Serializable {

    private String revisionType;
    private String serviceName;
    private String dateTime;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public RevisionType(String revisionType, String serviceName, String dateTime) {
        this.revisionType = revisionType;
        this.serviceName = serviceName;
        this.dateTime = dateTime;
    }



    public String getRevisionType() {return revisionType; }

    public void setRevisionType(String revType) {
        this.revisionType = revType;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String name) {
        this.serviceName = name;
    }

    public String getDateTime() { return dateTime.toString(); }

    public void setDateTime(String dateTime) { this.dateTime = dateTime; }

    @NonNull
    @Override
    public String toString() {
        //return revisionType + " at " + serviceName + " on " + new DateConverter().toString(dateTime);
        return revisionType + " at " + serviceName + " on " + dateTime;
    }
}
