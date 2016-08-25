package com.demkin.core.http

import com.demkin.core.API_KEY
import com.demkin.core.REQUEST_URL
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.httpGet

/**
 * Description of com.demkin.core.http
 * @author evgen1000end
 * @since 16.08.2016
 */

const val TOKEN_ERROR = "error"
const val REQUEST_SEPARATOR ="?method="
const val TOKEN_FORMAT = "format"
const val VALUE_FORMAT = "json"
const val TOKEN_KEY = "api_key"

fun ObjectMapper.answerHasError(answer: String): Boolean {
  val root = this.readTree(answer)
  root.fields().forEach {
    if (it.key == TOKEN_ERROR) {
      return true
    }
  }
  return false
}


fun Iterable<Pair<String, String>>.httpParameters(): String {
  return (this + Pair(TOKEN_FORMAT, VALUE_FORMAT) + Pair(TOKEN_KEY, API_KEY)).asIterable().
          map { "&${it.first}=${it.second}" }.
          reduce { a, b -> "$a$b" }
}

fun constructRequest(methodName: String, params: String): String {
  return "$REQUEST_URL$REQUEST_SEPARATOR$methodName$params"
}

fun requestToString(path: String): String {
  val (request, response, result) = path.httpGet().responseString()
  val (data, error) = result
  return data ?: error.toString()
}

fun invokeRequestAsString(path: String) = path.httpGet().responseString().third
