/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax

/**
 * Created by xionglei01@baidu.com on 2020/11/14.
 */


class ABC {

}

fun funtionABC () = ""


fun main() {
    var s1 : String? = ""
    var s2 = ""

//    s1.toString()

    println(s1?.length)

    println(s1?.isNotEmpty())

    println("$s2")

    // 注意：如果采用java和kotlin混编的方式，这里是无法判断传进来的p1是否是null的，最终报如下异常
    // java.lang.IllegalStateException: JavaUtils.string1 must not be null
    nullFunc(JavaUtils.string1)

    // 若采用纯kotlin的方式编程，这里编译器就会报错，必须加上?非空判断
    println(s1?.isNotEmpty())

    println("end")

}


fun nullFunc(p1: String) {

    println(p1.isNotEmpty())

}