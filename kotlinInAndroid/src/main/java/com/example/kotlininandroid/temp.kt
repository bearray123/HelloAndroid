/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.example.kotlininandroid

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Created by xionglei01@baidu.com on 2020/11/15.
 */
fun somethingFun(){

    println("test some fun in any kt file.")

//    MainScope().launch (Dispatchers.IO) {
//
//        withContext(EmptyCoroutineContext) {
//
//        }
//
//    }


}

fun main() {
    somethingFun()
}
