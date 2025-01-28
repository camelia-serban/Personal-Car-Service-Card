package com.example.personalcarservicecard.asyncTask;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.Callable;

public class RunnableTask<R> implements Runnable {
    private final Handler handler;

    //they are send from the mainThread
    private final Callable<R> asyncOperation;
    private final Callback<R> mainThreadOperation;


    //implements Runnable in order to consider it a Thread
    public RunnableTask(Handler handler, Callable<R> asyncOperation, Callback<R> mainThreadOperation) {
        this.handler = handler;
        this.asyncOperation = asyncOperation;
        this.mainThreadOperation = mainThreadOperation;
    }

    @Override
    public void run() {
        try {
            //apelam declansarea operatiei asincrone, care se realizeaza pe acest thread
            final R result = asyncOperation.call();

            //rezultatul obtinut mai sus este trimis catre handler prin intermediul unui obiect
             handler.post(new HandlerMessage<>(mainThreadOperation, result));
        } catch (Exception e) {
            Log.i("RunnableTask", "failed call runnable " + e.getMessage());
        }
    }
}
