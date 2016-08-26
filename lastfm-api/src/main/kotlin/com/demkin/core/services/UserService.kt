package com.demkin.core.services

import com.demkin.core.http.*
import com.demkin.core.model.ErrorAnswer
import com.demkin.core.model.UserLovedTracks
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Description of com.demkin.core.services
 * @author evgen1000end
 * @since 16.08.2016
 */

const val REQUEST_USER_GETLOVEDTRACKS = "user.getlovedtracks"
const val PARAMETER_USER = "user"
const val PARAMETER_LIMIT = "limit"
const val PARAMETER_PAGE = "page"
const val DEFAULT_LIMIT = 50
const val DEFAULT_PAGE = 1

class UserService {
  val mapper = ObjectMapper()
  val fmService = HttpLastFmService()

  fun getLovedTracks(userName: String, limit: Int = DEFAULT_LIMIT, page: Int = DEFAULT_PAGE): UserLovedTracks {
    val params = listOf(
            Pair(PARAMETER_USER, userName),
            Pair(PARAMETER_LIMIT,limit.toString()),
            Pair(PARAMETER_PAGE, page.toString())).httpParameters()
    val body = fmService.invokeRequestAsString(constructRequest(REQUEST_USER_GETLOVEDTRACKS, params))
    when (mapper.answerHasError(body)) {
      true -> {
        val error = mapper.readValue(body, ErrorAnswer::class.java)
        throw RuntimeException("Request was completed with error: ${error.error} - ${error.message}")
      }
      false -> return mapper.readValue(body, UserLovedTracks::class.java)
    }
  }
}