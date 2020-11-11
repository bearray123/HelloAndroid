/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin

import java.lang.Exception

/**
 *  kotlin里if else、when、try catch 均是表达式，不是语句
 *
 * Created by xionglei01@baidu.com on 2020/9/12.
 */



fun main() {
    println("test kotlin expression")
    var i = 1
    var c = when (i) {
        1 -> println("i is 1")
        2 -> println("i is 2")
        else -> println("i is null")
    }

    val result: Int
    val a: Int = 1
    val b: Int = 0
    result = try {
        a/b
    } catch (e: Exception) {
        e.printStackTrace()
        0
    }

}