package com.demkin.core.http

import com.demkin.core.REQUEST_URL
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.httpGet

/**
 * Description of com.demkin.core.http
 * @author evgen1000end
 * @since 16.08.2016
 */


fun ObjectMapper.answerHasError(answer: String): Boolean {
  val root = this.readTree(answer)
  root.fields().forEach {
    if (it.key == "error") {
      return true
    }
  }
  return false
}


fun Iterable<Pair<String, String>>.httpParameters(): String {
  return this.map { "&${it.first}=${it.second}" }.reduce { a, b -> "$a$b" }
}

fun constructRequest(methodName: String, params: String): String {
  return "$REQUEST_URL?method=$methodName$params"
}

fun requestToString(path: String): String {
  val (request, response, result) = path.httpGet().responseString()
  val (data, error) = result
  println(response)
  println("Data: " + data)
  println("Error: " + error)
  return data ?: error.toString()
}

fun invokeRequestAsString(path: String) = path.httpGet().responseString().third
