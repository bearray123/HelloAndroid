/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax;

/**
 * Created by xionglei01@baidu.com on 2020/12/6.
 */
interface ObserveJava<T> {
    void onChanged(T data);

    void onSuccess();

    void onError(String msg);

}
