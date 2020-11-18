/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax

/**
 *
 *  Kotlin 基础语法
 *      1）【表达式】要强于语句！！！- Expressions is stronger than Statements
 *      2) 语句不需要 ; 分号
 *
 * Created by xionglei01@baidu.com on 2020-05-24.
 */
class TestSyntax {


//    var str0 : String = null //compile error!
    var str1_var : String?= null

    var str2_var : String = "java"

    val str3_val : String  = "kotlin"


    fun testVarAndVal() : Unit {

//    str3_val = "modify-kotlin" // compile error!
        str2_var = "modify-java"

    }

    fun test_if_else_expression(flag: Int): String {
        return if (flag == 1) {
            "java" + "_1"
        } else if (flag == 2) {
            "java" + "_2"
        } else {
            "java" + "_0"
        }
    }

    fun test_when_expression(flag: Int): String {
        str1_var = "str1_init:"
        var result = when {
                    flag == 1 -> str1_var + "_1_by_when"
                    flag == 2 -> str1_var + "_2_by_when"
                    flag == 3 -> str1_var + "_2_by_when"
                    else -> str1_var + "_else_by_when"
        }
        return result
    }

    /**
     * 高阶函数：函数的入参 或 返回值也是函数 的函数
     * 自定义高阶函数
     */
    fun myFilter1(v1: Int, v2: Boolean, v3: () -> Array<String>): Array<String>? {

        println("myFilter1 execute!")
        return v3.invoke()
    }

    /**
     * 高阶函数最后一个参数是闭包时，可以简写
     */
    fun myFilter2(v1: Int, v2: Boolean,
                  v3: (Array<String>) -> Array<String>): Array<String>? {

        return null
    }


    /**
     * when 第二种写法
     * 简化函数声明: 可以将 return 关键字替换为赋值运算符
     */
    fun test_when_expression_1 (flag: Int): String = when (flag){
            1 -> str1_var + "_1_by_when"
            2 -> str1_var + "_2_by_when"
            3 -> str1_var + "_2_by_when"
            else -> str1_var + "_else_by_when"
    }

    fun test_for_loop() {
        for (i in 1..100 step 10) {
            println("current index is $i")
        }

    }

    fun test_fun(vFun : (Int, Int)->Int) {
        vFun.invoke(1, 2) // 只有执行了传入函数参数的invoke方法，这个入参的函数才会执行
        println("test_fun execute finish, and the fun in is ::" + vFun)
    }


}


    fun main (args : Array<String>) {

        println("begin main test!!!")

        var testSyntax = TestSyntax()

        var result_test_if = testSyntax.test_if_else_expression(2)

        var result_test_when = testSyntax.test_when_expression_1(3)

        println(result_test_if)

        println(result_test_when)

        testSyntax.test_for_loop()

        println("end main test!!!")


        val strings = arrayOf("hello", "world", "myheelo", "xionglei", "huyu")

        println("--------user system filter begin--------------")
        strings.filter { it.contains("he") }.forEach{ println(it)}
        println("--------user system filter end--------------")


        println("--------user myself filter begin--------------")

        // 当闭包是最后一个参数时，最好把闭包移除到函数入参（也就是两个括号）的外面
        // 完整写法，会报 warning
//        testSyntax.myFilter1(1, true,
//                {
//                    var re = Array<String>(10, {""})
//                    for (item in strings) {
//                        if (item.contains("he"))
//                            re.fill(item)
//                    }
//                            re
//                } )

        // 优雅写法, v3是闭包，放到函数参数的外面
        testSyntax.myFilter1(1, true) {
            println("闭包开始执行！")
            var re = Array<String>(10, {""})
            for (item in strings) {
                if (item.contains("he"))
                    println("找到包含的了：$item")
            }
            println("闭包执行完毕！")
            re
        }?.forEach { println("调用 myFileter过滤之后的打印：：$it") }



        println("--------user myself filter end--------------")




        // 匿名函数, z其实是定义的一个匿名函数，它的类型是 (Int, Int) -> Int
        // z 并不是一个 Int 类型！
        var z = fun (x: Int, y: Int): Int {
            println("execute x + y")
            return x + y
        }
        // 完整的写法就像下面这样
        var z1: (Int, Int)->Int = fun (x: Int, y: Int): Int {
            println("execute x + y")
            return x + y
        }

        //
        var m = fun (x: Int, y: Int) = x + y

        testSyntax.test_fun(z) // z这个函数其实是没执行的，不会打印里面的println

    }