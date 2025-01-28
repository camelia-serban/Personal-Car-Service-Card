package com.example.personalcarservicecard.asyncTask;

public class HandlerMessage<R> implements Runnable {
    //RunnableTask is send after it was received from the MainThread
    private final Callback<R> mainThreadOperation;
    //it is send from RunnableTask
    private final R result;

    public HandlerMessage(Callback<R> mainThreadOperation, R result) {
        this.mainThreadOperation = mainThreadOperation;
        this.result = result;
    }

    @Override
    public void run() {
        //the result is send in the activity
        mainThreadOperation.runResultOnUiThread(result);
    }
}
