/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.lambda

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
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

val castInt2String : (Int) -> String = {
    val result = it.toString()
    println("pass $it , the result is $result")
    result
}

class XiaoduDevice {

    var name: String? = null

    var state : ConnectState? = ConnectState.IDLE

    enum class ConnectState(var stateId: Int) {
        CONNECT(0),
        DISCONNECT(1),
        CONNECTING(2),
        IDLE(3)
    }


}

// 泛型函数 & lambda表达式入参
fun <T> T.checkConnect(action: (device: T, str: String) -> Boolean): Any? {
//    fun <T> T.checkConnect(action: (T, String) -> Boolean): Any? {

    action.invoke(this, "abc")  // 这里不能访问str ??

    return this

}


fun main() {
//    getData {
//        it.name
//        it.age
//    }

    // 回调的方式
//    getDataByCallback(object : Callback<Student> {
//        override fun onResult(result: Student) {
//            println("getDataByInterface  onResult :: ${result}")
//
//        }
//    })

//    Thread.sleep(10000)

//    MainScope().launch {
//        getDataByCoroutine()
//    }

//    castInt2String(1024)

    val xiaodu = XiaoduDevice()
    xiaodu.name = "tws-pods"
    xiaodu.checkConnect { device, str ->  // -> 前面的是形参（可以随意改名），不是实参
        device.state
        println("device.name = ${device.name}")
        println("str=$str")
        str.isNotEmpty() }


}

