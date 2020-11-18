/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.extension

import java.lang.StringBuilder

/**
 * Created by xionglei01@baidu.com on 2020/11/8.
 */

// 扩展方法
fun String.judgeIfLengthBeyond_10() = this.length > 10

// 运算符重载的函数名规则：minus（-）、plus（+）、times（*）、div（/）、
// compareTo（> < >= <=）、contains、dec（--）、inc（++）、、、

// 扩展方法且运算符重载
operator fun String.times(times: Int) : String {
    val sb = StringBuilder(this)
    for (i in 0 until times) {
        sb.append(this)
    }
    return sb.toString()
}


operator fun String.minus(matchStr: String) : String {
    return this.replace(matchStr, "")
}

fun main() {

    println("the string length > 10 : ${"abcdefth9xy".judgeIfLengthBeyond_10()}")

    println("xyz" * 10); // 运算符 * 重载

    println("abcdefghijklmn".minus("ilk")) // 去除中间包含ijk的字符串：不匹配失败
    println("abcdefghijklmn".minus("ijk")) // 去除中间包含ijk的字符串：匹配成功
    println("abcdefghijklmn" - "ijk") // 运算符（-）重载

}