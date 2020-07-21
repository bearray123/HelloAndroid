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
}
