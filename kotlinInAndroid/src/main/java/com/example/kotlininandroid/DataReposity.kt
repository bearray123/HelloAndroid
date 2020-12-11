/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.example.kotlininandroid

import com.tayue.android.hellokotlin.syntax.Product
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.concurrent.thread
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

/**
 * Created by xionglei01@baidu.com on 2020/11/18.
 */


class CommonUser(name: String, age: Int) {

}

suspend fun getUserFromCloud() {
    suspendCoroutine<CommonUser> {
    }

}

suspend fun getProductInfo() = suspendCoroutine<Product> { continuation ->
    println("getProductInfo pre_begin  ---- on thread::${Thread.currentThread().name}")

    thread(name = "xionglei-work_thread") {
        val request = Request()
        println("getProductInfo begin  ---- on thread::${Thread.currentThread().name}")
        request.enqueue(object : RequestCallback {

            override fun success(product: Product) {
                println("success: $product  ---- on thread::${Thread.currentThread().name}")
                continuation.resume(product)
            }

            override fun onFailed(error: String) {
                println("onFailed: $error ---- on thread::${Thread.currentThread().name}")
                continuation.resumeWithException(Exception(error))
            }

        })

    }

}

suspend fun getP3Info(): Product {
    delay(3000)
    return Product()
}

suspend fun getP2Info() {
    // --------------------------------------
    val request2 = Request()
    // TODO 疑惑: 为什么我自己定义的SAM函数不能直接使用lambda表达式，
    //  而Android系统的View.OnClickListener可以直接使用Lambda
    request2.call(object : Callback {
        override fun onData(product: Product) {
            println("onData: $product")
        }
    }) // 这里传的不是lambda，而是一个匿名类
}

class Request {

    fun enqueue(callback: RequestCallback) {
        Thread.sleep(5000)
        callback.success(product = Product())
    }

    suspend fun call(callback: Callback) {
//        Thread.sleep(5000)
        delay(5000)
        callback.onData(Product())
    }

}

interface RequestCallback {
    fun success(product: Product)
    fun onFailed(error: String)
}

interface Callback {
    fun onData(product: Product)
}

fun main() {

//    println("0 -- ${Thread.currentThread().name}")
//
//    GlobalScope.launch {
//        println("1 -- ${Thread.currentThread().name}")
//        delay(5000)
//        println("2 -- ${Thread.currentThread().name}")
//
//    }
//
//    println("3 -- ${Thread.currentThread().name}")
//
//    Thread.currentThread().join()


    GlobalScope.launch(Dispatchers.Default) {

        println("1-------- on thread::${Thread.currentThread().name}")

        var p3 = withContext<Product>(Dispatchers.IO) {
                getP3Info()
        }

        var p = getProductInfo()

        println("2-------- on thread::${Thread.currentThread().name}")

        println(p)

        println("3-------- on thread::${Thread.currentThread().name}")

        println("4-------- on thread::${Thread.currentThread().name}")


    }


    Thread.sleep(10_000)

}