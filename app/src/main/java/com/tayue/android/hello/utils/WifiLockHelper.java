/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.utils;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Created by xionglei01@baidu.com on 2020/7/19.
 */
public class WifiLockHelper {

    private final String TAG = "WifiLockHelper";

    Context mContext;
    WifiManager mWifiManager;
    WifiManager.WifiLock mWifiLock;

    private WifiLockHelper() {
    }

    private static final class InstanceHolder {
        public static final WifiLockHelper mInstance = new WifiLockHelper();
    }

    WifiLockHelper getInstance() {
        return InstanceHolder.mInstance;
    }

    public void init(Context context) {
        Log.d(TAG, "test: init wifi lock");
        mContext = context;
        mWifiManager = (WifiManager) mContext.getSystemService(Context.WIFI_SERVICE);
        mWifiLock = mWifiManager.createWifiLock("my_test_wifilock");
        mWifiLock.setReferenceCounted(false);
    }


    public void acquireWifiLock() {
        Log.d(TAG, "test: acquireWifiLock");
        mWifiLock.acquire();
    }


    public  void releaseWifiLock() {
        Log.d(TAG, "test: releaseWifiLock");
        mWifiLock.release();
    }

}
