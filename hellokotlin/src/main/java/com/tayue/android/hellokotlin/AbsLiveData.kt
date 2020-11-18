/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin

/**
 * Created by xionglei01@baidu.com on 2020/6/12.
 */
abstract class AbsLiveData<T, K> {
    fun observer (observer: TObserver1<T>) {

    }
    fun observer2 (observer: TObserver2<T, K>) {

    }
}