/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.bluetooth;

import android.bluetooth.BluetoothA2dp;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by xionglei01@baidu.com on 2020-05-18.
 */
public class BTBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "XL-BTBroadcastReceiver";


    @Override public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        switch (action) {
            case BluetoothDevice.ACTION_ACL_CONNECTED:
                break;
            case BluetoothDevice.ACTION_ACL_DISCONNECTED:
                break;
            case BluetoothAdapter.ACTION_STATE_CHANGED:
                break;
            case BluetoothA2dp.ACTION_CONNECTION_STATE_CHANGED:
                break;
            case BluetoothA2dp.ACTION_PLAYING_STATE_CHANGED:
                Log.d(TAG, "xl# a2dp receiver:: action=" + "ACTION_PLAYING_STATE_CHANGED");
                switch (intent.getIntExtra(BluetoothA2dp.EXTRA_STATE, -1)) {
                    case BluetoothA2dp.STATE_PLAYING:
                        Log.d(TAG, "xl# a2dp receiver:: EXTRA_STATE=" + "a2dp is playing...");
                        break;
                    case BluetoothA2dp.STATE_NOT_PLAYING:
                        Log.d(TAG, "xl# a2dp receiver:: EXTRA_STATE=" + "a2dp is not playing...");
                        break;
                    default:
                        break;
                }
                break;
            case BluetoothHeadset.ACTION_CONNECTION_STATE_CHANGED:

            case BluetoothHeadset.ACTION_AUDIO_STATE_CHANGED:

        }

    }
}
