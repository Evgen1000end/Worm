package com.demkin.core.model

import com.demkin.core.API_KEY
import com.demkin.core.SHARED_SECRET
import com.demkin.core.http.TOKEN_KEY
import org.apache.commons.codec.digest.DigestUtils
import java.util.*

const val KEY_METHOD = "method"

class SignatureParams(val methodName: String, val params: Map<String, String>)

fun md5(input:String) = DigestUtils.md5Hex(input)

fun createSignature(params:SignatureParams): String {
  return createSignature(params.methodName, params.params)
}

fun createSignature(methodName:String, params: Map<String, String>): String {
  val mutable = TreeMap(params)
  mutable.put(TOKEN_KEY, API_KEY)
  mutable.put(KEY_METHOD, methodName)
  val sig = mutable.toSortedMap().map {"${it.key}${it.value}"}.reduce { f, s ->"$f$s"} + SHARED_SECRET
  return md5(sig)
}

