/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hello;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

/**
 * Created by xionglei01@baidu.com on 2020/11/4.
 */
public class PhonePropertyActivity extends Activity {

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.phone_property_layout);

        ((TextView) findViewById(R.id.phone_brand)).setText("Brand: " + Build.BRAND);
        ((TextView) findViewById(R.id.phone_model)).setText("Model: " + Build.MODEL);

    }

}
