package com.example.handlerthread;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;

public class ExampleHandlerThread extends HandlerThread {

    private static final String TAG = "ExampleLooperThread";

    public Handler handler;
    public Looper looper;

    public ExampleHandlerThread() {
        super("ExampleLooperThread");
    }

    @Override
    public void run() {
        Log.d(TAG, "run() called");

        Looper.prepare();

        looper = Looper.myLooper();

        handler = new Handler(Looper.getMainLooper());

        Looper.loop();
    }
}
