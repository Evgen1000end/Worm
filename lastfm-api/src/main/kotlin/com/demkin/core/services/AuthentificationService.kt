package com.demkin.core.services

import com.demkin.core.http.addDefaultParams
import com.demkin.core.http.constructRequest
import com.demkin.core.http.invokeRequestAsString
import com.demkin.core.model.TokenResult
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Created by demkinev on 26.08.2016.
 */

const val NO_SESSION = "-"

class AuthentificationService {
  var sessionKey:String = NO_SESSION
  val mapper = ObjectMapper()

  fun apply(username:String, password:String):String {
    if (sessionKey== NO_SESSION){

      val request = constructRequest("auth.getToken", addDefaultParams())
      val result = invokeRequestAsString(request)

      val token = mapper.readValue(result, TokenResult::class.java)


    }
    return sessionKey
  }
}