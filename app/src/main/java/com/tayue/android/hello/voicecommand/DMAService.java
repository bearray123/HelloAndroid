/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.voicecommand;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import com.tayue.android.hello.R;

/**
 * Created by xionglei01@baidu.com on 2020-02-03.
 */
public class DMAService extends Service {

    private Handler mHandler = new Handler();

    @Override
    public IBinder onBind(Intent intent) {
        Log.d("xl", "DMAService# onBind");
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("xl", "DMAService# onStartCommand");

        setForegroundService();

        new Thread(new Runnable() {
            int count = 0;
            @Override public void run() {
                for (;;) {
                    count++;
                    Log.d("xl", "DMA Thread count++ # = " + count);
                    try {
                        // 每2分钟打印一次 log
                        Thread.sleep(1000 * 60 * 2);
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
    @TargetApi(Build.VERSION_CODES.O)
    public void setForegroundService() {
        //设定的通知渠道名称
        String channelName = "通知渠道的名称";
        //设置通知的重要程度
        int importance = NotificationManager.IMPORTANCE_LOW;
        //构建通知渠道
        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, channelName, importance);
        channel.setDescription("设置的 Description");
        //在创建的通知渠道上发送通知
        Notification.Builder builder = new Notification.Builder(this, CHANNEL_ID);
        builder.setSmallIcon(R.drawable.icon_beta) //设置通知图标
            .setContentTitle("通知的标题")//设置通知标题
            .setContentText("设置通知的内容")//设置通知内容
            .setAutoCancel(true) //用户触摸时，自动关闭
            .setOngoing(true);//设置处于运行状态
        //向系统注册通知渠道，注册后不能改变重要性以及其他通知行为
        NotificationManager notificationManager =
            ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        notificationManager.createNotificationChannel(channel);
        //将服务置于启动状态 NOTIFICATION_ID指的是创建的通知的ID
        startForeground(101, builder.build());
    }
}

