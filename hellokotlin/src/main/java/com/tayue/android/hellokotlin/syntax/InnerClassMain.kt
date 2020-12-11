/*
 * Copyright (C) 2020 Baidu, Inc. All Rights Reserved.
 */
package com.tayue.android.hellokotlin.syntax

/**
 * Created by xionglei01@baidu.com on 2020/11/21.
 */
class OutClass {

    var paramA : Int = 0

    // 内部类缺省默认是：public static final class
    class InnerClassA {
        var paramA : String? = "default"
    }

    // 若要定义内部类：用inner申明
    inner class InnerClassB {
        var paramA : String? = "default"

        // 若内不类想访问外部类的成员变量： @OutClass
        fun getOutClassParamA() = this@OutClass.paramA

    }

}

class View(var viewName : String) {
    var listener : ViewClickListener? = null

    fun click() {
        this.listener?.onClick(this)
    }
}

interface ViewClickListener {
    fun onClick(view : View)
}

fun main() {

    println("InnerClass : ${OutClass.InnerClassA().paramA}")
    println("InnerClass : ${OutClass.InnerClassA()}")

    println("InnerClass : ${OutClass().InnerClassB().paramA}")
    println("InnerClass : ${OutClass().InnerClassB().getOutClassParamA()}")

    // ------ 匿名内部类 object 申明 -----
    val view = View("Button")
    view.listener = object : ViewClickListener{
        override fun onClick(view: View) {
            println("the ${view.viewName} invoke :: View.onClick")
        }
    }
    view.click()

    var obs = object : Observe<Product> {
        override fun onChanged(data: Product) {
            println("from kotlin :: ${data.name}")

        }

    }

    var obs_Java = object : ObserveJava<Product> {
        override fun onChanged(data: Product?) {
            TODO("Not yet implemented")
        }

        override fun onSuccess() {
            TODO("Not yet implemented")
        }

        override fun onError(msg: String?) {
            TODO("Not yet implemented")
        }

    }
}



interface Observe<T> {
    fun onChanged(data: T)
}