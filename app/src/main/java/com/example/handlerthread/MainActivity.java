package com.example.handlerthread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;

import java.util.concurrent.atomic.AtomicBoolean;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private final ExampleHandlerThread handlerThread = new ExampleHandlerThread();
    public static AtomicBoolean threadStarted = new AtomicBoolean(false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startThread(View view) {
        Log.d(TAG, "startThread: button clicked");
        if (threadStarted.get()) {
            Log.d(TAG, "startThread: thread is already started");
            return;
        }
        threadStarted = new AtomicBoolean(true);
        handlerThread.start();
    }

    public void stopThread(View view) {
        Log.d(TAG, "stopThread: button clicked");
        if (!threadStarted.get()) {
            Log.d(TAG, "stopThread: thread is already stopped");
            return;
        }
        handlerThread.quit();
        threadStarted.set(false);
    }

    public void executeTaskFoo(View view) {
        Log.d(TAG, "executeTaskFoo: button clicked");
        if (!threadStarted.get()) {
            Log.d(TAG, "executeTaskFoo: fail due to thread not started");
            return;
        }
        Message msg = Message.obtain();
        msg.what = ExampleHandler.EVENT_EXECUTE_TASK_FOO;
        handlerThread.handler.sendMessage(msg);
    }

    public void executeTaskBar(View view) {
        Log.d(TAG, "executeTaskBar: button clicked");
        if (!threadStarted.get()) {
            Log.d(TAG, "executeTaskBar: fail due to thread not started");
            return;
        }
        Message msg = handlerThread.handler.obtainMessage();
        msg.what = ExampleHandler.EVENT_EXECUTE_TASK_BAR;
        msg.sendToTarget();
    }

    public void clearTasks(View view) {
        Log.d(TAG, "clearTasks: button clicked");
        if (!threadStarted.get()) {
            Log.d(TAG, "clearTasks: fail due to thread not started");
            return;
        }
        handlerThread.handler.removeCallbacksAndMessages(null);
        Log.d(TAG, "clearTasks: ended");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (threadStarted.get()) {
            handlerThread.quit();
            threadStarted.set(false);
        }
    }
}