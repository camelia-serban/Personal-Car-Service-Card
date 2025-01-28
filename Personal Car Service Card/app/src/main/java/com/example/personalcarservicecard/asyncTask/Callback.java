package com.example.personalcarservicecard.asyncTask;

public interface Callback<R> {
    void runResultOnUiThread(R result);
}
