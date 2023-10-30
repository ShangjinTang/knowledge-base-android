package com.example.handlerthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private final ExampleHandlerThread handlerThread = new ExampleHandlerThread();
    public static boolean threadStarted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view) {
        Log.d(TAG, "startThread: button clicked");
        if (threadStarted) {
            Log.d(TAG, "startThread: thread is already started");
            return;
        }
        threadStarted = true;
        handlerThread.start();
    }

    public void stopThread(View view) {
        Log.d(TAG, "stopThread: button clicked");
        if (!threadStarted) {
            Log.d(TAG, "stopThread: thread is already stopped");
            return;
        }
        threadStarted = false;
        handlerThread.looper.quit();
    }

    public void executeTaskFoo(View view) {
        Log.d(TAG, "executeTaskFoo: button clicked");
        if (!threadStarted) {
            Log.d(TAG, "executeTaskFoo: fail due to thread not started");
            return;
        }
        Message msg = Message.obtain();
        msg.what = ExampleHandler.EVENT_EXECUTE_TASK_FOO;
        handlerThread.handler.sendMessage(msg);
    }

    public void executeTaskBar(View view) {
        Log.d(TAG, "executeTaskBar: button clicked");
        if (!threadStarted) {
            Log.d(TAG, "executeTaskBar: fail due to thread not started");
            return;
        }
        Message msg = Message.obtain();
        msg.what = ExampleHandler.EVENT_EXECUTE_TASK_BAR;
        handlerThread.handler.sendMessage(msg);
    }
}