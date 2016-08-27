package com.demkin.core.http

import com.demkin.core.API_KEY
import com.demkin.core.REQUEST_URL
import com.demkin.core.REQUEST_URL_HTTPS
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import org.apache.commons.codec.digest.DigestUtils

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
const val EMPTY = "-"

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
          map { if (it.second == EMPTY) "&${it.first}" else "&${it.first}=${it.second}" }.
          reduce { a, b -> "$a$b" }
}

fun addDefaultParams() ="&$TOKEN_FORMAT=$VALUE_FORMAT&$TOKEN_KEY=$API_KEY"

fun constructRequest(methodName: String, params: String ="", useHttps:Boolean = false): String {
  val protocol = if (useHttps)  REQUEST_URL_HTTPS  else  REQUEST_URL
  return "$protocol$REQUEST_SEPARATOR$methodName$params"
}

fun constructRequest(methodName: String, params: Iterable<Pair<String, String>> = emptyList(), useHttps:Boolean = false): String {
  return constructRequest(methodName, params.httpParameters(), useHttps)
}

fun requestToString(path: String): String {
  val (request, response, result) = path.httpGet().responseString()
  val (data, error) = result
  return data ?: error.toString()
}


interface LastFmService {
  fun get(path:String):String
  fun post(path:String):String
}

class HttpLastFmService:LastFmService{
  override fun get(path: String): String {
    val response = invokeRequest(path)
    return response.component1() ?: response.component2().toString()
  }
  override fun post(path: String): String {
   val response = path.httpPost().responseString().third
   return response.component1() ?: response.component2().toString()
  }
}

fun invokeRequest(path: String) = path.httpGet().responseString().third


