/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.coroutines

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Created by xionglei01@baidu.com on 2020/9/9.
 */
class CoroutinesTest : CoroutineScope by MainScope(){

    fun init() {
        launch {

        }
    }

}

fun main() {
//    testLaunch()

//    testAsync()

    testLaunch2()

}

fun testLaunch() {
    runBlocking(Dispatchers.IO) {

        val job = launch { // 外层任务，包裹两个协程

            GlobalScope.launch { // 第一个协程
                for (i in 0..10) {
                    println("GlobalScope $i ${Thread.currentThread().name} -----start")
                    delay(100)
                    println("GlobalScope $i ${Thread.currentThread().name} -----end")
                }
            }

            launch { // 第二个协程
                for (i in 0..10) {
                    println("normal launch $i ${Thread.currentThread().name} #####start")
                    delay(100)
                    println("normal launch $i ${Thread.currentThread().name} #####end")
                }
            }
        }

        delay(300); // 延迟一会，让第二个协程能执行3次左右

        job.cancel() // 将外层任务取消了

        delay(2000) // 继续延迟，期望看到GlobalScope能继续运行

    }
}

// Coroutine 在设计的时候，要求在一个范围（Scope）内执行，这样当这个 Scope 取消的时候，
// 里面所有的子 Coroutine 也自动取消。所以要使用 Coroutine 必须要先创建一个对应的 CoroutineScope
//
fun testLaunch2() {



    GlobalScope.launch {
        println("GlobalScope.launch, 111111  block started in thread: ${Thread.currentThread().name}")
        delay(1000)
        println("GlobalScope.launch, 222222  block started in thread: ${Thread.currentThread().name}")

//        coroutineScope {
//
//        }
            getUserInfoFromNetwork()

        println("After execute getUserInfoFromNetwork,  in thread: ${Thread.currentThread().name}")

    }


    /**
     * 这里如果不加sleep来控制主线程运行足够长，那么当主线程结束后整个程序就回终止了，可能还等不到协程执行完成
     */
    Thread.sleep(10 * 1000)
    println("testLaunch2 finished,  in thread: ${Thread.currentThread().name}")

}

// 用async启动协程：两个协程结果组合的情况
fun testAsync() {

    runBlocking(Dispatchers.IO) {
        val job1 = async(Dispatchers.IO) {
            for (i in 0..10) {
                println("async111 [$i] ${Thread.currentThread().name} ----- start")
                delay(1000)
                println("async111 [$i] ${Thread.currentThread().name} ----- end")
            }
            10
        }
        val job2 = async {
            for (i in 0..10) {
                println("async222 [$i] ${Thread.currentThread().name} #### start")
                delay(1000)
                println("async222 [$i] ${Thread.currentThread().name} #### end")
            }
            20
        }
        println("testAsync before await$$$$$$")
        println("job2 + job2 = ${job1.await() + job2.await()}")
        println("testAsync.end!")
    }

    println("testAsync :: the last line executed")

}




fun main1() {
    println("begin test coroutines....")

    runBlocking {

        println("runBlocking....")
        repeat(5) {
            println("repeat....$it ,currentThread=${Thread.currentThread().name}")
            delay(1000)
        }

    }
    println("end test coroutines....")

//    println("GlobalScope.launch.start :: ${job.start()}")

}



private suspend fun getUserInfoFromNetwork() {
//    withContext(Dispatchers.Default) {
        println("getUserInfoFromNetwork :: start::  on Thread :: ${Thread.currentThread().name}")
        delay(1000)
        println("getUserInfoFromNetwork :: end::  on Thread :: ${Thread.currentThread().name}")
//    }
}

private fun setUserInfo() {
    println("setUserInfo :: on Thread :: ${Thread.currentThread().name}")

}

private suspend fun getConfigFromNetwork() {
    println("getConfigFromNetwork :: start::  on Thread :: ${Thread.currentThread().name}")
    delay(2000)
    println("getConfigFromNetwork :: end::  on Thread :: ${Thread.currentThread().name}")
}

private fun setConfig() {
    println("setConfig :: on Thread :: ${Thread.currentThread().name}")

}
