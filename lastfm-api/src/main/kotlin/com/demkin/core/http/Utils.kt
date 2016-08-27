package com.demkin.core.http

import com.demkin.core.*
import com.demkin.core.model.SignatureParams
import com.fasterxml.jackson.databind.ObjectMapper
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * Description of com.demkin.core.http
 * @author evgen1000end
 * @since 16.08.2016
 */


fun ObjectMapper.answerHasError(answer: String): Boolean {
  val root = this.readTree(answer)
  root.fields().forEach {
    if (it.key == PARAMETER_ERROR) {
      return true
    }
  }
  return false
}


fun Map<String, String>.httpParam(): String {
  val mutable = HashMap(this)
  mutable.put(PARAMETER_FORMAT, DEFAULT_FORMAT)
  mutable.put(PARAMETER_API_KEY, API_KEY)
  return mutable.map { "&${it.key}=${it.value}" }.reduce { a, b -> "$a$b" }
}

private fun constructRequest(methodName: String, params: String = "", useHttps: Boolean = false): String {
  val protocol = if (useHttps) REQUEST_URL_HTTPS else REQUEST_URL
  return "$protocol$REQUEST_SEPARATOR$methodName$params"
}

fun constructRequest(params: SignatureParams, apiSig: String = "", useHttps: Boolean = false): String {
  fun generateSigParam(apiSig: String) = if (apiSig == "") "" else "&api_sig=" + apiSig
  return constructRequest(params.methodName, params.params.httpParam(), useHttps) + generateSigParam(apiSig)
}

fun constructRequest(methodName: String, params: Map<String, String>, apiSig: String = "", useHttps: Boolean = false): String {
  fun generateSigParam(apiSig: String) = if (apiSig == "") "" else "&api_sig=" + apiSig
  return constructRequest(methodName, params.httpParam(), useHttps) + generateSigParam(apiSig)
}

interface LastFmService {
  fun get(path: String): String
  fun post(path: String): String
}

class HttpLastFmService : LastFmService {
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

fun now() = (System.currentTimeMillis() / 1000).toInt()


fun timestamp(time: LocalDateTime): Int {
  return (time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli() / 1000).toInt()  //of("UTC")
}

/*

 */
fun timestamp(time: String): Int {
  return timestamp(LocalDateTime.parse(time, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
}