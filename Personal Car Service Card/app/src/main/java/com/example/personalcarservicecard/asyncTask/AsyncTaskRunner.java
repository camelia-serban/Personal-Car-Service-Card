package com.example.personalcarservicecard.asyncTask;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncTaskRunner {
    //the Handler is the object that takes care of the mainThread
    private final Handler handler = new Handler(Looper.getMainLooper());

    //the Executor decides when a tread should start
    private final Executor executor = Executors.newCachedThreadPool();

    public <R> void executeAsync(Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        try {
            //specific the Executor that the Thread should be of type RunnableTask class
            executor.execute(new RunnableTask<>(handler, asyncOperation, mainThreadOperation));
        } catch (Exception ex) {
            Log.i("AsyncTaskRunner", "failed call executeAsync " + ex.getMessage());
        }
    }
}
