/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.mvvm.view;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.tayue.android.mvvm.R;
import com.tayue.android.mvvm.lifecycle.MyObserver;
import com.tayue.android.mvvm.model.NewsDataBean;
import com.tayue.android.mvvm.viewmodel.NewsViewModel;

/**
 * Created by xionglei01@baidu.com on 2020-03-06.
 */
public class TestMVVMMainActivity extends AppCompatActivity {

    private static final String TAG = "TestMVVMMainActivity";

    private NewsViewModel mNewsViewModel;

    private TextView newsTitle;
    private TextView newsSubTitle;
    private Button fetchBtn;

    //public MyObserver myObserver = new MyObserver();


    @Override protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initView();

        mNewsViewModel = ViewModelProviders.of(this).get(NewsViewModel.class);

        mNewsViewModel.getNewsModel().observe(this, new Observer() {
            @Override public void onChanged(@Nullable Object o) {

                Log.d(TAG, "onChanged:: ");
                if (o instanceof NewsDataBean) {
                    NewsDataBean news = ((NewsDataBean) o);
                    newsTitle.setText(news.title);
                    newsSubTitle.setText(news.subTitle);
                }

            }
        });

    }

    private void initView() {
        setContentView(R.layout.mvvm_main_layout);
        newsTitle = findViewById(R.id.news_title);
        newsSubTitle = findViewById(R.id.news_subTitle);
        fetchBtn = findViewById(R.id.fetchNews);
        fetchBtn.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                if (mNewsViewModel != null) {
                    mNewsViewModel.getCurrentNews();
                }
            }
        });


    }

    @Override protected void onStart() {
        super.onStart();
        Log.d(TAG, "TestMVVMMainActivity:: onStart()");
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
