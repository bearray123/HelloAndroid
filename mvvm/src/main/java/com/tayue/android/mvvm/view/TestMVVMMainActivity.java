/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.mvvm.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import com.tayue.android.mvvm.lifecycle.MyObserver;

/**
 * Created by xionglei01@baidu.com on 2020-03-06.
 */
public class TestMVVMMainActivity extends AppCompatActivity {

    private static final String TAG = "TestMVVMMainActivity";


    @Override protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getLifecycle().addObserver(new MyObserver());

    }

    @Override protected void onResume() {
        super.onResume();
        Log.d(TAG, "TestMVVMMainActivity:: onResume()");

    }

    @Override protected void onPause() {
        super.onPause();
        Log.d(TAG, "TestMVVMMainActivity:: onPause()");
    }

    @Override protected void onStop() {
        super.onStop();
        Log.d(TAG, "TestMVVMMainActivity:: onStop()");
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "TestMVVMMainActivity:: onDestroy()");
    }

}
