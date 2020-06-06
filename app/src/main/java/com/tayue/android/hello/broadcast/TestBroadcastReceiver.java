/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.broadcast;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 *
 * 静态注册：在 manifest 清单里注册
 * 动态注册：在代码比如 activity 中进行注册Context.registerReceiver
 *
 * PS: 从3.1开始，系统发出的广播都是添加了 FLAG=FLAG_EXCLUDE_STOPPED_PACKAGES
 *     即从这个版本开始 处于 STOPPED 的应用无法再收到系统发出的广播，除非是其他应用自定义的广播并且把
 *     FLAG 设置成FLAG_INCLUDE_STOPPED_PACKAGES才行
 *
 * Created by xionglei01@baidu.com on 2020-02-06.
 */
public class TestBroadcastReceiver extends BroadcastReceiver {
    @Override public void onReceive(Context context, Intent intent) {

        Log.d("xl", "TestBroadcastReceiver#onReceive:::");
        if (intent != null) {
            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(intent.getAction())) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                    BluetoothAdapter.ERROR);
                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                        Log.d("xl" ,"TestBroadcastReceiver#onReceive::: BluetoothAdapter.STATE_OFF");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d("xl" ,"TestBroadcastReceiver#onReceive::: BluetoothAdapter.STATE_TURNING_OFF");
                        break;
                    case BluetoothAdapter.STATE_ON:
                        Log.d("xl" ,"TestBroadcastReceiver#onReceive::: BluetoothAdapter.STATE_ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d("xl" ,"TestBroadcastReceiver#onReceive::: BluetoothAdapter.STATE_TURNING_ON");
                        break;
                    default:
                        break;
                }
            }
        }

    }
}
