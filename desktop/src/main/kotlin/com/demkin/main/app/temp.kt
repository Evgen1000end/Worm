package com.demkin.main.app

import javafx.beans.value.ChangeListener
import java.util.function.Consumer

//var listener: ChangeListener<String>? = null


fun main(args: Array<String>) {
  //val listener:ChangeListener<String> = { a, b, c -> println("") }
  val consumer:Consumer<String> = Consumer { println(it)}
}
