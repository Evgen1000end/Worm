package com.demkin.core.model

import com.demkin.core.AUTH_GETMOBILESESSION
import com.demkin.core.PARAMETER_PASSWORD
import com.demkin.core.PARAMETER_USERNAME
import com.demkin.core.http.HttpLastFmService
import com.demkin.core.http.answerHasError
import com.demkin.core.http.constructRequest
import com.fasterxml.jackson.databind.ObjectMapper


class Authenticator {
  val fmService = HttpLastFmService()
  val mapper = ObjectMapper()

  private fun currentSessionExist(username: String): Boolean {
    return false
  }

  private fun findCurrentSession(username: String): Session {
    return Session()
  }

  private fun createNewSession(username: String, password: String): Session {
    val signatureParam = SignatureParams(AUTH_GETMOBILESESSION,
            mapOf(Pair(PARAMETER_PASSWORD, password), Pair(PARAMETER_USERNAME, username)))
    val signature = createSignature(signatureParam)
    val request = constructRequest(signatureParam, useHttps = true, apiSig = signature)
    val body = fmService.post(request)
    when (mapper.answerHasError(body)) {
      true -> {
        val error = mapper.readValue(body, ErrorAnswer::class.java)
        throw RuntimeException("Request was completed with error: ${error.error} - ${error.message}")
      }
      false -> return mapper.readValue(body, UserSession::class.java).session ?: Session()
    }
  }

  fun fetchSession(username: String, password: String): Session {
    return if (currentSessionExist(username)) findCurrentSession(username) else createNewSession(username, password)
  }
}