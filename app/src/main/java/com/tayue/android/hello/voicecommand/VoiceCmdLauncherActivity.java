/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.voicecommand;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
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
                // 这里使用 startService也可以，甭管用startService，还是用startForegroundService
                // 如果是应用在后台使用startForegroundService调用的话，
                // 则启动的service里面必须在5S 内调用startForeground来将其设置成前台服务，否则就会 ANR 或 crash
                //startForegroundService(new Intent(VoiceCmdLauncherActivity.this, DMAService.class));

                // 8.0之前老的方式是 先启动后台 service，然后在 servie 里面调startForeground
                startService(new Intent(VoiceCmdLauncherActivity.this, DMAService.class));

                // 总结：
                // 甭管用那种方式启动前台服务， 都必须及时（5S内）在 Service 里面调用startForeground
            }
        }, 55*1000);
    }
}
