/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by xionglei01@baidu.com on 2020/11/23.
 *
 *  对比测试的java 类
 *
 */
class JavaActivity extends Activity {


    private Button button;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        button.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {

            }
        });

        // java8 lambda表达式的写法
        button.setOnClickListener(vie -> {
            Log.d("","vie =" + vie);
            vie.post( () -> {

            });
        });

        // kotlin lambda的写法，和java不一样
        // button.setOnClickListener { view ->
        //    view.post {
        //
        //    }
        // }



    }

}
