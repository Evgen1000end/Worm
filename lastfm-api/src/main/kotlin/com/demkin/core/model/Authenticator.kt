package com.demkin.core.model

import com.demkin.core.API_KEY
import com.demkin.core.SHARED_SECRET
import com.demkin.core.http.HttpLastFmService
import com.demkin.core.http.answerHasError
import com.demkin.core.http.constructRequest
import com.demkin.core.http.httpParameters
import com.fasterxml.jackson.databind.ObjectMapper

const val AUTH_GETMOBILESESSION = "auth.getMobileSession"

class Authenticator {
  val fmService = HttpLastFmService()
  val mapper = ObjectMapper()

  fun byPassword(username:String, password:String):Session{
    val signature = createSignature(
            mapOf(Pair("api_key", API_KEY),
                    Pair("method", AUTH_GETMOBILESESSION),
                    Pair("password", password),
                    Pair("username", username)), SHARED_SECRET)

    val request = constructRequest(AUTH_GETMOBILESESSION,
            mapOf(Pair("username", username),
                   Pair("password", password),
                   Pair("api_sig",signature) ).httpParameters(), useHttps = true)

    val body = fmService.post(request)

    when (mapper.answerHasError(body)) {
      true -> {
        val error = mapper.readValue(body, ErrorAnswer::class.java)
        throw RuntimeException("Request was completed with error: ${error.error} - ${error.message}")
      }
      false -> return mapper.readValue(body, UserSession::class.java).session ?: Session()
    }
  }
}