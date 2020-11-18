/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin

import android.app.KeyguardManager
import android.app.Service
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.util.Log

/**
 * Created by xionglei01@baidu.com on 2020/7/21.
 */
class PowerService : Service() {

    companion object {
        val TAG = "MyPowerService"
    }

    val screenStateReceiver = ScreenStateRecevier()


    override fun onCreate() {
        Log.d(TAG, "onCreate")
        super.onCreate()

        var screenIntentFilter = IntentFilter()
        screenIntentFilter.apply {
            addAction(Intent.ACTION_SCREEN_ON)    // 屏幕点亮就发
            addAction(Intent.ACTION_SCREEN_OFF)   // 屏幕熄屏就发
            addAction(Intent.ACTION_USER_PRESENT) // 用户划开锁屏界面回到桌面才发这个广播，仅仅面部解锁是不会发这个广播
        }
        registerReceiver(screenStateReceiver, screenIntentFilter)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d(TAG, "onStartCommand")

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind")
        TODO("Not yet implemented")
    }


    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
        unregisterReceiver(screenStateReceiver)
    }

}

class ScreenStateRecevier : BroadcastReceiver() {

    companion object {
        val TAG = "MyScreenStateRecevier"
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: ${intent?.action}")
        val keyguardManager = context?.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager

        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> Log.d(TAG, "亮屏了, 是否锁屏了：isDeviceLocked =${keyguardManager.isDeviceLocked}")
            Intent.ACTION_SCREEN_OFF -> Log.d(TAG, "熄屏了, 是否锁屏了：isDeviceLocked =${keyguardManager.isDeviceLocked}")
            Intent.ACTION_SCREEN_ON -> Log.d(TAG, "用户解锁了：isDeviceLocked =${keyguardManager.isDeviceLocked}")
        }
    }

}