package com.demkin.core.model

import org.apache.commons.codec.digest.DigestUtils

fun md5(input:String) = DigestUtils.md5Hex(input)

fun createSignature(params: Map<String, String>, secret: String): String {
  val sig = params.map {"${it.key}${it.value}"}.reduce { f, s ->"$f$s"}+secret
  return md5(sig)
}