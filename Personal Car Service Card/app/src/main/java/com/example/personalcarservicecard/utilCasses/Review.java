package com.example.personalcarservicecard.utilClasses;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;

public class Review implements Serializable {
    private String tietServiceName;
    private String msgRatingBar;

    public Review(String tietServiceName, String msgRatingBar) {
        this.tietServiceName = tietServiceName;
        this.msgRatingBar = msgRatingBar;
    }

    public String getTietServiceName() {return tietServiceName;  }
    public void setTietServiceName(String name) {this.tietServiceName = name; }

    public String getMsgRatingBar() { return msgRatingBar; }
    public void setMsgRatingBar(String msg) {this.msgRatingBar = msg; }


    @NonNull
    @Override
    public String toString() {
        return tietServiceName + " " + msgRatingBar ;
    }
}
