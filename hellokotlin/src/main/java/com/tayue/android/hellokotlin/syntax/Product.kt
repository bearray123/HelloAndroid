/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax

/**
 * Created by xionglei01@baidu.com on 2020/11/21.
 */
class Product { // 类的访问限制默认是 ：public final class
                // 如果想被继承，加上open 关键字 ： open class Product

    // 类成员变量申明默认是private， get/set方法默认是public
    public var name: String? = null
        get() {
//            println("get the name ,the value is : $field")
            return field
        }
        set(value) {
//            println("set the name, pass value is: $value")
            field = value
        }

    var price = 1.00

    override fun toString(): String {
        return "{product.name : $name, prduct.price : $price}"
    }



    // 中缀表达式 infix
    infix fun belong (obj : String) : Boolean? {
        return this.name?.startsWith(obj)
    }

}

fun main() {
    val product = Product()
    product.name = "xiaodu-tws-earphone"
    println("product.name = ${product.name}")
}