package com.demkin.core.services

import com.demkin.core.API_KEY
import com.demkin.core.http.answerHasError
import com.demkin.core.http.constructRequest
import com.demkin.core.http.httpParameters
import com.demkin.core.http.invokeRequestAsString
import com.demkin.core.model.ErrorAnswer
import com.demkin.core.model.UserLovedTracks
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Description of com.demkin.core.services
 * @author evgen1000end
 * @since 16.08.2016
 */
class UserService {
  val mapper = ObjectMapper()
  fun getLovedTracks(userName: String, limit: String = "50", page: String = "1"): UserLovedTracks {
    val params = listOf(
            Pair("user", userName),
            Pair("limit", limit),
            Pair("page", page)).httpParameters()

    val response = invokeRequestAsString(constructRequest("user.getlovedtracks", params))
    val body = response.component1() ?: response.component2().toString()

    when (mapper.answerHasError(body)) {
      true -> {
        val error = mapper.readValue(body, ErrorAnswer::class.java)
        throw RuntimeException("Request was completed with error: ${error.error} - ${error.message}")
      }
      false -> return mapper.readValue(body, UserLovedTracks::class.java)
    }
  }
}