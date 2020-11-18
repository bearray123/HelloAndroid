/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

/**
 * Created by xionglei01@baidu.com on 2020/11/15.
 */

interface Callback<T> {

    fun onResult(result : T)

}

class Student(var name: String, var age: Int) {

    override fun toString(): String {
        return "Student{ name : ${name}, age : ${age} }"
    }
}

fun getData(callback: (result: Student) -> Unit) { // lambda表达式

}

fun getDataByCallback(callback: Callback<Student>) { //

    println("[Callback] begin get data ----student ----")

    Thread {
        Thread.sleep(5000)
        val stu = Student("xionglei", 33)
        println("[Callback] end get data ----student ----")
        callback.onResult(result = stu)
    }.start()

}

suspend fun getDataByCoroutine() { //
    println("[Coroutine] begin get data ----student ----")

    MainScope().launch {
        suspendCancellableCoroutine<Student> {
            Thread {
                Thread.sleep(5000)
                val stu = Student("xionglei", 33)
                println("[Coroutine] end get data ----student ----")
    //            callback.onResult(result = stu)
                it.resume(stu)
            }.start().runCatching {
    //            it.resumeWith()
            }
        }
    }


}


fun main() {
    getData {
        it.name
        it.age
    }

    // 回调的方式
    getDataByCallback(object : Callback<Student> {
        override fun onResult(result: Student) {
            println("getDataByInterface  onResult :: ${result}")

        }
    })

//    Thread.sleep(10000)

    MainScope().launch {
        getDataByCoroutine()
    }


}

