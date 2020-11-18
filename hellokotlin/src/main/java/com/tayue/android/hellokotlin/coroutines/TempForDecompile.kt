/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.coroutines

import java.io.Serializable
import kotlin.coroutines.CoroutineContext

/**
 * 用于临时 copy 代码做反编译java后看源码使用，分析代码结构
 * Created by xionglei01@baidu.com on 2020/11/14.
 */

public object EmptyCoroutineContext : CoroutineContext, Serializable {
    private const val serialVersionUID: Long = 0
    private fun readResolve(): Any = EmptyCoroutineContext

    public override fun <E : CoroutineContext.Element> get(key: CoroutineContext.Key<E>): E? = null
    public override fun <R> fold(initial: R, operation: (R, CoroutineContext.Element) -> R): R = initial
    public override fun plus(context: CoroutineContext): CoroutineContext = context
    public override fun minusKey(key: CoroutineContext.Key<*>): CoroutineContext = this
    public override fun hashCode(): Int = 0
    public override fun toString(): String = "EmptyCoroutineContext"
}

class ABC {
    fun function_1() {

    }
}

fun main() {
    EmptyCoroutineContext
    val abc_java = ABC::class.java
    val abc_class = ABC::class
    ABC().function_1()
    val abc_javaclass = ABC::javaClass
}