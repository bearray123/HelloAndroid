/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello.memory;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import com.tayue.android.hello.R;
import java.util.ArrayList;

/**
 * Created by xionglei01@baidu.com on 2020-03-07.
 */
public class MockMemoryLeakActivity extends Activity {

    private ArrayList<String[]> data = new ArrayList<>();

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mock_leak_layout);
        mShakeHandler.sendEmptyMessage(0);

    }

    private Handler mShakeHandler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            // 频繁创建对象，模拟内存抖动
            for(int index = 0;index <= 100;index ++) {
                // 这个如果把1000调到100000一会就OOM，目前new 1000个的话的手机内存还可以承受，内存达到一定程度会触发GC
                String strArray[] = new String[1000];
                // 如果不add到数组里不会 OOM crash，会频繁触无限制发GC，因为是局部变量，走完for一次就GC释放了！
                data.add(strArray);
            }


            mShakeHandler.sendEmptyMessageDelayed(0,1000);
        }
    };

    @Override protected void onDestroy() {
        super.onDestroy();
        mShakeHandler.removeCallbacksAndMessages(null);
        data.clear();
        mShakeHandler = null;
    }
}
