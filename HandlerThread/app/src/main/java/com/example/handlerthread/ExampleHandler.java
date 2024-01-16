package com.example.handlerthread;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class ExampleHandler extends Handler {

    private static final String TAG = "ExampleHandler";

    public static final int EVENT_EXECUTE_TASK_FOO = 1;
    public static final int EVENT_EXECUTE_TASK_BAR = 2;

    public ExampleHandler(@NonNull Looper looper) {
        super(looper);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
        switch (msg.what) {
            case EVENT_EXECUTE_TASK_FOO:
                Log.d(TAG, "handleMessage: EVENT_EXECUTE_TASK_FOO");
                executeTaskFoo();
                break;
            case EVENT_EXECUTE_TASK_BAR:
                Log.d(TAG, "handleMessage: EVENT_EXECUTE_TASK_BAR");
                executeTaskBar();
                break;
            default:
                break;
        }
    }

    private void executeTaskFoo() {
        for (int i = 0; i < 5; i++) {
            Log.d(TAG, "executeTaskFoo: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Log.d(TAG, "executeTaskFoo: ended");
    }

    private void executeTaskBar() {
        for (int i = 0; i < 3; i++) {
            Log.d(TAG, "executeTaskBar: " + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        Log.d(TAG, "executeTaskBar: ended");
    }
}
