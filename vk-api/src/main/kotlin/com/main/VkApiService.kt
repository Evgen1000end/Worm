package com.main

import com.demkin.core.*
import com.demkin.core.http.HttpLastFmService
import com.demkin.core.model.SignatureParams
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.*

/**
 * Created by demkinev on 03.09.2016.
 */

open class VkApiService {
  val mapper = ObjectMapper()
  val fmService = HttpLastFmService()

  fun Map<String, String>.httpParamVk(): String {
    val mutable = HashMap(this)
    mutable.put("access_token", VK_TOKEN)
    return mutable.map { "&${it.key}=${it.value}" }.reduce { a, b -> "$a$b" }
  }

  private fun constructRequest(methodName: String, params: String = ""): String {
    return "${VK_API_URL}/$methodName?$params"
  }

  fun constructRequest(methodName: String, params: Map<String, String>): String {
    return constructRequest(methodName, params.httpParamVk())
  }

  protected fun sanityJSON(primary:String):String {
    val index = primary.indexOf("[")
    val index2 = primary.indexOf(",")

    val tempBefore = primary.substring(0, index+1)
    val tempAfter = primary.substring(index2+1 , primary.length)

    return tempBefore+tempAfter
  }

}
