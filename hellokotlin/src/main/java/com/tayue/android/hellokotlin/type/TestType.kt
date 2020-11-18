/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.type

/**
 * Created by xionglei01@baidu.com on 2020/9/12.
 */
class TestType {

}

class Person(var name: String, var age: Int, val sex: String) {

    init {
        name = "maoxiaozhang:ZhangXue"
    }

    var height: Float

        get() {
            return height
        }
        set(value) {
            height = value
        }

    var breast: Int

        get() {
            return breast
        }
        set(value) {
            breast = value
        }

    // val 类型没有set方法
    val hobby: String?

        get() {
            return hobby
        }
//        set(value) {
//            hobby = value
//        }

    fun isGirl() : Boolean {
        return height.equals("xionglei") ?: false
    }
}

fun main() {
    var a: String? = "hello"
//    a = "abc"
    var aLength = a?.length ?: 0

    var x: String = "hello"
    var y: String?
//    x = y // Type mismatch
    y = x

//    var somebody = Person()
//    if (somebody is Person) {
//        println(" somebody is girl:: ${(somebody.isGirl()}")
//    }


}