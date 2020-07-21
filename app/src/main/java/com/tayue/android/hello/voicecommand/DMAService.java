/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.voicecommand;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;
import com.tayue.android.hello.R;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

/**
 * Created by xionglei01@baidu.com on 2020-02-03.
 */
public class DMAService extends Service {

    private static final String TAG = "network_test";

    ////private Handler mMainHandler = new Handler();
    //private HandlerThread handlerThread = new HandlerThread("my_handle_thread");
    //
    //private Handler myHandler;
    //
    //@Override public void onCreate() {
    //    super.onCreate();
    //    handlerThread.start();
    //
    //    myHandler = new Handler(handlerThread.getLooper());
    //
    //    myHandler.post(mRunnable);
    //}

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("xl", "DMAService# onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("xl", "DMAService# onStartCommand");

        // 模拟ANR
        // vivo Android 9系统是6s ， 华为Android 10 手机是10s
        // android.app.RemoteServiceException: Context.startForegroundService() did not then call Service.startForeground()
        //mHandler.postDelayed(new Runnable() {
        //    @Override public void run() {
        //        setForegroundService();
        //    }
        //}, 10*1000);


        setForegroundService();

        new Thread(new Runnable() {
            int count = 0;
            @Override public void run() {
                for (;;) {
                    count++;
                    Log.d("xl", "DMA Thread count++ # = " + count);
                    try {
                        // 每2分钟打印一次 log
                        Thread.sleep(1000 * 5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    //Channel ID 必须保证唯一
    private static final String CHANNEL_ID = "com.appname.notification.channel";

    /**
     * 通过通知启动服务
     */
    //@TargetApi(Build.VERSION_CODES.O)
    public void setForegroundService() {

        RemoteViews remoteViews = new RemoteViews(getPackageName(),R.layout.notification_remote_views);

        Intent intentJumpApp = new Intent("jumpApp");
        PendingIntent jumpPendngIntent = PendingIntent.getBroadcast(this, 100, intentJumpApp, PendingIntent.FLAG_CANCEL_CURRENT);
        remoteViews.setOnClickPendingIntent(R.id.notification_root_view, jumpPendngIntent);

        Notification.Builder builder;
        builder = new Notification.Builder(this);
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);

        //
        //设定的通知渠道名称
        String channelName = "通知渠道的名称";
        //设置通知的重要程度
        int importance = NotificationManager.IMPORTANCE_LOW;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 8.0以上版本必须设置channel，否则报错
            Log.d("xl", "on Android O, must create notification channel");
            //构建通知渠道
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
            channel.setDescription("设置的 Description");
            //在创建的通知渠道上发送通知
            builder.setChannelId(CHANNEL_ID)
                .setSmallIcon(R.drawable.icon_beta) //设置通知图标
                .setContentTitle("标题: DMA服务已连接")//设置通知标题
                .setContentText("内容：正在为您提供小度助手服务")//设置通知内容
                .setAutoCancel(true) //用户触摸时，自动关闭
                .setOngoing(true)//设置处于运行状态
                .setLargeIcon(largeIcon)
                .setCustomContentView(remoteViews);
            //向系统注册通知渠道，注册后不能改变重要性以及其他通知行为
            NotificationManager notificationManager =
                ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
            notificationManager.createNotificationChannel(channel);

        } else {
            Log.d("xl", "on below Android O, do not create notification channel");
            builder.setSmallIcon(R.drawable.icon_beta) //设置通知图标
                .setContentTitle("标题: DMA服务已连接")//设置通知标题
                .setContentText("内容：正在为您提供小度助手服务")//设置通知内容
                .setAutoCancel(true) //用户触摸时，自动关闭
                .setOngoing(true);//设置处于运行状态
        }
        //将服务置于启动状态 NOTIFICATION_ID指的是创建的通知的ID
        startForeground(101, builder.build());

    }




    //private Runnable mRunnable = new Runnable() {
    //    @Override public void run() {
    //        getDataSync();
    //        Log.d(TAG, "before postDelayed::");
    //        //mMainHandler.postDelayed(mRunnable, 1000*3);
    //        myHandler.postDelayed(mRunnable, 1000*3);
    //
    //    }
    //};
    //
    //private void getDataSync() {
    //    Log.d(TAG, "TestNetworkActivity# getDataSync::");
    //    OkHttpClient client = new OkHttpClient();
    //    Request request = new Request.Builder()
    //                            .url("https://www.baidu.com")
    //                            .build();
    //    client.newCall(request).enqueue(new Callback() {
    //        @Override public void onFailure(@NotNull Call call, @NotNull IOException e) {
    //            Log.d(TAG, "TestNetworkActivity# network onFailure:: e" + e.getMessage());
    //
    //        }
    //
    //        @Override public void onResponse(@NotNull Call call, @NotNull Response response)
    //            throws IOException {
    //                if (response.isSuccessful()) {
    //                    Log.d(TAG, "TestNetworkActivity# network SUCCESS, CODE=" + response.code());
    //
    //                } else {
    //                    Log.d(TAG, "TestNetworkActivity# network FAILED~~~~ CODE=" + response.code());
    //                }
    //        }
    //    });
    //    Log.d(TAG, "getDataSync  end::");
    //
    //}


}

