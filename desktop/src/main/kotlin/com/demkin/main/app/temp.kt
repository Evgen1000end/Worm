package com.demkin.main.app

import javafx.beans.value.ChangeListener
import java.util.function.Consumer

//var listener: ChangeListener<String>? = null


class Tempo(val a:String){
  open fun showSomething() = println("Hello "+a)
}

fun main(args: Array<String>) {
  val tempo =Tempo("eug")

  with(tempo){
    println("sd")
    showSomething()
  }

  val r = run {
    tempo.a
  }

  println(r)
}
