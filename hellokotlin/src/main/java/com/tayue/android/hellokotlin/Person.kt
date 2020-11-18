/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.mvvm.kotlintest

import java.lang.reflect.Array

/**
 * Created by xionglei01@baidu.com on 2020/6/11.
 */
class Person() {

    /*属性*/
    private var gender: Boolean = true

    private lateinit var sex: String

    private val age by lazy { 24 }

    /*次构造方法*/
    constructor(name: String, gender: Boolean) : this() {
        println("constructor")
    }
    companion object X {
        val instance = Person("yzq", false)


        /*伴生对象中的初始化代码*/
        init {
//            println("companion init 1 ${age}")
        }

        init {
            println("companion init 2")
        }
    }

    /*初始化代码块*/
    init {
        println("Person init 2, gender:${gender}")
    }

    /*初始化代码块*/
    init {
        println("Person init 1")
    }




}

fun main () {

    Person.instance

}