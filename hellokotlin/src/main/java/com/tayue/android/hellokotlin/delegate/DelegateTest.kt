/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.delegate

/**
 * Created by xionglei01@baidu.com on 2020/10/7.
 */


interface DuerApi {

    fun initSdk()

    fun startSpeech()

    fun stopSpeech()

    fun release()

}

class DuerApiImpl : DuerApi {
    override fun initSdk() {
        TODO("Not yet implemented")
    }

    override fun startSpeech() {
        println("DuerApiImpl.startSpeech...")
    }

    override fun stopSpeech() {
        TODO("Not yet implemented")
    }

    override fun release() {
        TODO("Not yet implemented")
    }

}

/**
 * 接口代理：不用重写非必要的接口方法
 */
class DuerClient(val apiDelegate: DuerApi) : DuerApi by apiDelegate{

//    override fun initSdk() {
//        TODO("Not yet implemented")
//    }
//
//    override fun startSpeech() {
//        TODO("Not yet implemented")
//    }
//
//    override fun stopSpeech() {
//        TODO("Not yet implemented")
//    }
//
//    override fun release() {
//        TODO("Not yet implemented")
//    }

    // 只重写需要被代理的方法即可
    override fun startSpeech() {
        apiDelegate.startSpeech()
        println("DuerClient.startSpeech......")
    }


}

fun main() {

    val apiImpl = DuerApiImpl()
    val duerClient = DuerClient(apiImpl)

    duerClient.startSpeech()

}


