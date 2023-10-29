package com.example.handlerthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private final ExampleHandlerThread handlerThread = new ExampleHandlerThread();
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view) {
        Log.d(TAG, "startThread() called");
        handlerThread.start();
    }

    public void stopThread(View view) {
        Log.d(TAG, "stopThread() called");
        handlerThread.looper.quit();
    }

    public void executeTaskFoo(View view) {
        Log.d(TAG, "executeTaskFoo() called");
        Handler fooThreadHandler = new Handler(handlerThread.looper);
        fooThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    Log.d(TAG, "executeTaskFoo: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                Log.d(TAG, "executeTaskFoo: run: ended");
            }
        });
    }

    public void executeTaskBar(View view) {
        Log.d(TAG, "executeTaskBar() called");
        Handler barThreadHandler = new Handler(handlerThread.looper);
        barThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    Log.d(TAG, "executeTaskBar: " + i);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                Log.d(TAG, "executeTaskFoo: run: ended");
            }
        });
    }
}