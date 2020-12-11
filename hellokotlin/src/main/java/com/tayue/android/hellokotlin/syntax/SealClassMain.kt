/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax

/**
 * Created by xionglei01@baidu.com on 2020/11/21.
 */
// 与枚举的区别
// 密封类：子类有限，且只能在本文件内定义（kotlin>=1.1）,1.1以下只能在类内部定义
// 枚举：  实例有限
sealed class MediaPlayerCmd // 子类有限! 子类有限! 子类有限!  模块隔离使用，让文件外部无法进行扩展子类

class Play(val url: String, var position: Long) : MediaPlayerCmd() {

    fun play() {
        println("exe: play = $url, postion $position")
    }

}

class Pause : MediaPlayerCmd() {
    fun pause() = println("exe: pause.")

}

class Resume : MediaPlayerCmd() {
    fun resume() = println("exe: resume.")
}

class Release : MediaPlayerCmd() {
    fun release() = println("exe: release.")
}

fun main() {

    var playCmd = Play("https://www.baidu.com", 90)
    playCmd.play()

    var pauseCmd = Pause()
    pauseCmd.pause()

    var resumeCmd = Resume()
    resumeCmd.resume()

    var releaseCmd = Release()
    releaseCmd.release()

}