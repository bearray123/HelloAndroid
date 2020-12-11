/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.lambda

/**
 * Created by xionglei01@baidu.com on 2020/11/23.
 */


/**
 * 定义一个去购物的函数
 *  带接受者receiver
 */
fun <T, R> T.goShopping(bags: Int, block : T.(Boolean) -> R) : R {
    println(bags)
    // 如果提了两个包以上，则需要消费信用卡
    var creditCard = bags >= 2
    return block.invoke(this, creditCard)
}

class Girl {
    var name: String? = ""
    var height: Int = 168
    var pocketMoney : Double = 100.00
}

data class Clothers(var price: Double)

data class Dress(var price: Double)

data class Shoes(var price: Double, var color: String)

fun main() {
    val girl = Girl()
    girl.pocketMoney = 500.00
    val gaint = girl.goShopping(3) { creditCard ->
        println("credit card = $creditCard")
        if (creditCard) { // 如果消费了信用卡
            pocketMoney += 500
        }
        when (pocketMoney) {
            1000.00 -> Shoes(999.9, "red")
            500.00 -> Clothers(100.0)
            else -> Dress(50.0)
        }
    }

    println("女孩去购物，最终的收获是：${gaint}" )
}
