/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.example.kotlininandroid

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import kotlinx.coroutines.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.ContinuationInterceptor
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

/**
 * Created by xionglei01@baidu.com on 2020/11/14.
 */
class KotlinMainEntryActivity : Activity(), CoroutineScope by MainScope() { // 属性代理

    val userNameTxt by lazy {
        findViewById<TextView>(R.id.user_name)
    }
    val ageTxt by lazy {
        findViewById(R.id.user_pernal_age) as TextView
    }
    val hasMerried by lazy {
        findViewById<TextView>(R.id.user_pernal_has_merried)
    }

//    val scope = MainScope()

    companion object {
        val TAG = KotlinMainEntryActivity::class.simpleName
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.kotlin_main_entry_layout)

        CoroutineScope(EmptyCoroutineContext).launch {

        }

        launch {
            Log.d(TAG, "launch execute... in Thread:: ${Thread.currentThread().name}") // main
            var name = getUserInfo()
            Log.d(TAG, "after getUserInfo in Thread:: ${Thread.currentThread().name}") // main
            userNameTxt.text = name

            val persnalInfo = getPersnalInfo()

            ageTxt.text = persnalInfo.first.toString()
            hasMerried.text = persnalInfo.second.toString()
            Log.d(TAG, "age and hasMerried is refreshed:: ${Thread.currentThread().name}") // main


        }

        somethingFun()


    }


    fun initView() {

    }


    suspend fun getUserInfo(): String {
        Log.d(TAG, "getUserInfo# before withContext ... in Thread:: ${Thread.currentThread().name}") // main
        return withContext(Dispatchers.IO) { // 这里才真正切线程
            Log.d(TAG, "withContext begin... in Thread:: ${Thread.currentThread().name}") // DefaultDispatcher-worker-1

            delay(5_000)

            Log.d(TAG, "withContext end... in Thread:: ${Thread.currentThread().name}") // DefaultDispatcher-worker-1
            return@withContext "xionglei01"
        }
    }

    suspend fun getPersnalInfo() : Pair<Int, Boolean>{
        Log.d(TAG, "getPersnalInfo# before async ... in Thread:: ${Thread.currentThread().name}") // main
        val wrapeAge =  async(Dispatchers.IO) { // 这里才真正切线程
            Log.d(TAG, "[getAge] async begin... in Thread:: ${Thread.currentThread().name}") // DefaultDispatcher-worker-1

            delay(5_000)

            Log.d(TAG, "[getAge] async end... in Thread:: ${Thread.currentThread().name}") // DefaultDispatcher-worker-1
            return@async 33
        }

        val wrapMerried = async(Dispatchers.IO){
            Log.d(TAG, "[getMerried] async begin... in Thread:: ${Thread.currentThread().name}") // DefaultDispatcher-worker-1

            delay(8_000)

            Log.d(TAG, "[getMerried] async end... in Thread:: ${Thread.currentThread().name}") // DefaultDispatcher-worker-1
            return@async true
        }

        Log.d(TAG, "before await... in Thread:: ${Thread.currentThread().name}") // main

        val age = wrapeAge.await()
        val hasMerried = wrapMerried.await()

        // before <---> after await 一共耗时 8秒，以最长路径为准，最大优化并发
        Log.d(TAG, "after await... in Thread:: ${Thread.currentThread().name}") // main

        return Pair(age, hasMerried)
    }



    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")

    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
        // 需要手动调用scope.cancel来取消协程
//        scope.cancel()
        cancel()
    }

}


class MyContinuationInterceptor: ContinuationInterceptor{
    override val key = ContinuationInterceptor
    override fun <T> interceptContinuation(continuation: Continuation<T>) = MyContinuation(continuation)
}

class MyContinuation<T>(val continuation: Continuation<T>): Continuation<T> {
    override val context = continuation.context
    override fun resumeWith(result: Result<T>) {
        Log.d(KotlinMainEntryActivity.TAG, "MyContinuation#resumeWith() ${result.getOrNull()}")
        continuation.resumeWith(result)
    }
}