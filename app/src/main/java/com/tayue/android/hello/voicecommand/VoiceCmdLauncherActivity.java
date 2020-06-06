/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.voicecommand;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

/**
 * Created by xionglei01@baidu.com on 2020-02-03.
 */
public class VoiceCmdLauncherActivity extends Activity {

    Handler mHandler = new Handler();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      Log.d("xl", "VoiceCmdLauncherActivity#onCreate------start");

      startDMAService();

      Log.d("xl", "VoiceCmdLauncherActivity#onCreate-------end");

      finish();

    }

    @TargetApi(26)
    private void startDMAService() {
        mHandler.postDelayed(new Runnable() {
            @Override public void run() {


                ///**
                // * 在 Android 8.0 之前，创建前台 Service 的方式通常是先创建一个后台 Service，然后将该 Service 推到前台。
                // * Android 8.0 有一项复杂功能：系统不允许后台应用创建后台 Service。
                // * 因此，Android 8.0 引入了一种全新的方法，即 startForegroundService()，以在前台启动新 Service。
                // * 在系统创建 Service 后，应用有五秒的时间来调用该 Service 的 startForeground() 方法以显示
                // * 新 Service 的用户可见通知。 如果应用在此时间限制内未调用 startForeground()，则系统将停止此 Service 并声明此应用为 ANR。
                // */
                //// 这里使用 startService也可以，甭管用startService，还是用startForegroundService
                //// 如果是应用在后台使用startForegroundService调用的话，
                //// 则启动的service里面必须在5S 内调用startForeground来将其设置成前台服务，否则就会 ANR 或 crash
                //startForegroundService(new Intent(VoiceCmdLauncherActivity.this, DMAService.class));

                // 8.0之前老的方式是 先启动后台 service，然后在 servie 里面调startForeground
                //startService(new Intent(VoiceCmdLauncherActivity.this, DMAService.class));


                startForgroundService();

                // 总结：
                // 甭管用那种方式启动前台服务， 都必须及时（5S内）在 Service 里面调用startForeground
            }
        }, 5*1000); // 这里delay  5s 是起到把应用切到后台，以达到后台启动service的目的！
    }

    private void startForgroundService() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            /**
             * 在 Android 8.0 之前，创建前台 Service 的方式通常是先创建一个后台 Service，然后将该 Service 推到前台。
             * Android 8.0 有一项复杂功能：系统不允许后台应用创建后台 Service。
             * 因此，Android 8.0 引入了一种全新的方法，即 startForegroundService()，以在前台启动新 Service。
             * 在系统创建 Service 后，应用有五秒的时间来调用该 Service 的 startForeground() 方法以显示
             * 新 Service 的用户可见通知。 如果应用在此时间限制内未调用 startForeground()，则系统将停止此 Service 并声明此应用为 ANR。
             */
            // 这里使用 startService也可以，甭管用startService，还是用startForegroundService
            // 如果是应用在后台使用startForegroundService调用的话，
            // 则启动的service里面必须在5S 内调用startForeground来将其设置成前台服务，否则就会 ANR 或 crash
            startForegroundService(new Intent(VoiceCmdLauncherActivity.this, DMAService.class));
            Log.d("xl", "start ForgroundService >> use new start [startForegroundService] methond! done");
        } else {
            // 8.0之前老的方式是 先启动后台 service，然后在 servie 里面调startForeground
            startService(new Intent(VoiceCmdLauncherActivity.this, DMAService.class));
            Log.d("xl", " ForgroundService >> use old start [startService] methond! done");
        }

    }

}
