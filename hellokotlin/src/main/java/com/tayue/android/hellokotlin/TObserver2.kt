/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin

/**
 * Created by xionglei01@baidu.com on 2020/6/12.
 */
interface TObserver2<T, K> {
    fun onChanged(t: T, k: K)
}