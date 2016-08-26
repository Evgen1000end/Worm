package com.demkin.core.services

import com.demkin.core.http.constructRequest
import com.demkin.core.http.constructRequest
import com.demkin.core.http.httpParameters
import com.demkin.core.http.invokeRequest
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Created by demkinev on 25.08.2016.
 */
class CommonService {
  fun getRequest(functionName:String, params:Iterable<Pair<String, String>> ):String {
    val response = invokeRequest(constructRequest(functionName, params.httpParameters()))
    return response.component1() ?: response.component2().toString()
  }
}