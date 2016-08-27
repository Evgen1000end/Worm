package com.demkin.core.http

import com.demkin.core.API_KEY
import com.demkin.core.REQUEST_URL
import com.demkin.core.REQUEST_URL_HTTPS
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import java.util.*

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


fun Map<String, String>.httpParameters(): String {
  val mutable = HashMap(this)
  mutable.put(TOKEN_FORMAT, VALUE_FORMAT)
  mutable.put(TOKEN_KEY, API_KEY)
  return  mutable.map { "&${it.key}=${it.value}" }.reduce{a,b -> "$a$b"}
}

fun constructRequest(methodName: String, params: String ="", useHttps:Boolean = false): String {
  val protocol = if (useHttps)  REQUEST_URL_HTTPS  else  REQUEST_URL
  return "$protocol$REQUEST_SEPARATOR$methodName$params"
}

fun constructRequest(methodName: String, params: MutableMap<String, String>, useHttps:Boolean = false): String {
  return constructRequest(methodName, params.httpParameters(), useHttps)
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


