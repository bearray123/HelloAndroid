/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin

import com.tayue.android.mvvm.kotlintest.Person

/**
 * Created by xionglei01@baidu.com on 2020/6/12.
 */
class TestMain {

    lateinit var person1: Person
    var person2: Person

    init {
//        person1 = Person("xionglei", true)  // 报warning，没必要lateinit，因为这里已经使用init初始化了，lateinit不会生效了
        person2 = Person("xionglei", true)
    }


}


fun main() {
    print("main start ... ")

    val poorguy = PoorGuy()
    poorguy.moneyCount = 100.0
    poorguy.age = 20
    println("the poorguy'age is ${poorguy.age}, and has money = ${poorguy.moneyCount}")
}



class PoorGuy {

    var age = 18
    var moneyCount = 0.0
        get() {
            return field
        }
        set(value) {
            field = value
        }


}




