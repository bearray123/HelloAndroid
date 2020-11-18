/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.network;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.support.annotation.Nullable;
import android.util.Log;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

/**
 * Created by xionglei01@baidu.com on 2020/7/19.
 */
public class TestNetworkActivity extends Activity {

    private static final String TAG = "network_test";

    //private Handler mMainHandler = new Handler();
    //private HandlerThread handlerThread = new HandlerThread("my_handle_thread");
    //private Handler myHandler ;



    //private Runnable mRunnable = new Runnable() {
    //    @Override public void run() {
    //        getDataSync();
    //        Log.d(TAG, "before postDelayed::");
    //        //mMainHandler.postDelayed(mRunnable, 1000*3);
    //        myHandler.postDelayed(mRunnable, 1000*3);
    //
    //    }
    //};


    Timer timer = new Timer("my_timer");

    TimerTask timerTask = new TimerTask() {
        @Override public void run() {
            getDataSync();
            Log.d(TAG, "before postDelayed::");
        }
    };


    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override public void run() {
                timer.schedule(timerTask, 3000, 5000);
            }
        }).start();



        //new Thread(new Runnable() {
        //    @Override public void run() {
        //        while (true) {
        //            try {
        //                Thread.sleep(1000*3);
        //                getDataSync();
        //                Log.d(TAG, "before postDelayed::");
        //
        //            } catch (Exception e) {
        //
        //            }
        //        }
        //    }
        //}).start();


        //handlerThread.start();
        //
        //myHandler = new Handler(handlerThread.getLooper());
        //
        //myHandler.post(mRunnable);


    }

    private void getDataSync() {
        Log.d(TAG, "TestNetworkActivity# getDataSync::");
        //OkHttpClient client = new OkHttpClient();
        //Request request = new Request.Builder()
        //                        .url("https://www.baidu.com")
        //                        .build();
        //client.newCall(request).enqueue(new Callback() {
        //    @Override public void onFailure(@NotNull Call call, @NotNull IOException e) {
        //        Log.d(TAG, "TestNetworkActivity# network onFailure:: e" + e.getMessage());
        //
        //    }
        //
        //    @Override public void onResponse(@NotNull Call call, @NotNull Response response)
        //        throws IOException {
        //            if (response.isSuccessful()) {
        //                Log.d(TAG, "TestNetworkActivity# network SUCCESS, CODE=" + response.code());
        //
        //            } else {
        //                Log.d(TAG, "TestNetworkActivity# network FAILED~~~~ CODE=" + response.code());
        //            }
        //    }
        //});
        Log.d(TAG, "getDataSync  end::");

    }


}
