/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.mvvm.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import com.tayue.android.mvvm.model.NewsDataBean;

/**
 * Created by xionglei01@baidu.com on 2020-03-07.
 */
public class NewsViewModel extends ViewModel {


    private MutableLiveData<NewsDataBean> mCurrentNewsLiveData = new MutableLiveData<NewsDataBean>();

    public void getCurrentNews() {

        NewsDataBean news = new NewsDataBean();

        news.title = "宇宙最厉害的MT诞生";
        news.subTitle = "来自湖北的熊嘟嘟，1h 全通 MC";

        mCurrentNewsLiveData.postValue(news);

    }

    public MutableLiveData getNewsModel() {
        return mCurrentNewsLiveData;
    }







}
