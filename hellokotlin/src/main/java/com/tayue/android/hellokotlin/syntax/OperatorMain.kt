/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax

/**
 * Created by xionglei01@baidu.com on 2020/11/21.
 */

// 运算符重载

class Complex(var real: Double, var imaginary: Double) { // 复数的运算符重载

    operator fun plus(other: Complex): Complex {
        return Complex(real + other.real, imaginary + other.imaginary)
    }

    operator fun times(other: Complex) = Complex(real * other.real, imaginary * other.imaginary)



    override fun toString(): String {
        return "{real: $real , imaginary: $imaginary}"
    }

}

fun main() {

    // ---------- 运算符重载 ------
    val complexA = Complex(3.0, 4.0)
    val complexB = Complex(1.25, 6.6)

    val result = complexA + complexB
    println("plus [+] ---- execute ${complexA.toString()} + ${complexB.toString()} :: the result is = $result")

    val resultTimes = complexA * complexB
    println("times [*] ---- execute ${complexA.toString()} + ${complexB.toString()} :: the result is = $resultTimes")
    // ---------------------------

    // --------  中缀表达式 infix ------
    val myProduct = Product()
    myProduct.name = "xiaodu-show"
    val isXiaoduProdcut = myProduct belong "xiaodu"
    println("$myProduct 是否是小度的产品：$isXiaoduProdcut" )

    val otherProduct = Product()
    otherProduct.name = "huawei-pods"
    val isXiaoduProdcut_other = otherProduct belong "xiaodu"
    println("$otherProduct 是否是小度的产品：$isXiaoduProdcut_other" )
    // ---------------------------



}

