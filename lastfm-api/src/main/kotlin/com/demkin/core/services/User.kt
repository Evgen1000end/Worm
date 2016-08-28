package com.demkin.core.services

import com.demkin.core.*
import com.demkin.core.http.answerHasError
import com.demkin.core.http.constructRequest
import com.demkin.core.http.timestamp
import com.demkin.core.model.ErrorAnswer
import com.demkin.core.model.Session
import com.demkin.core.model.UserLovedTracks
import com.demkin.core.model.UserRecentTracks
import java.util.*

/**
 * Description of com.demkin.core.services
 * @author evgen1000end
 * @since 16.08.2016
 */

class User(session: Session = Session()) : LastFmService(session) {

  fun getLovedTracks(userName: String, limit: Int = DEFAULT_LIMIT, page: Int = DEFAULT_PAGE): UserLovedTracks {
    val params = mapOf(
            Pair(PARAMETER_USER, userName),
            Pair(PARAMETER_LIMIT, limit.toString()),
            Pair(PARAMETER_PAGE, page.toString()))
    val body = fmService.get(constructRequest(REQUEST_USER_GETLOVEDTRACKS, params))

    when (mapper.answerHasError(body)) {
      true -> {
        val error = mapper.readValue(body, ErrorAnswer::class.java)
        throw RuntimeException("Request was completed with error: ${error.error} - ${error.message}")
      }
      false -> return mapper.readValue(body, UserLovedTracks::class.java)
    }
  }

  fun getRecentTracks(userName: String,
                      limit: Int = DEFAULT_LIMIT,
                      page: Int = DEFAULT_PAGE,
                      extended: Int = DEFAULT_EXTENDED,
                      from: String = EMPTY,
                      to: String = EMPTY): UserRecentTracks {
    val params = HashMap<String, String>()
    params.put(PARAMETER_USER, userName)
    params.put(PARAMETER_LIMIT, limit.toString())
    params.put(PARAMETER_PAGE, page.toString())
    params.put(PARAMETER_EXTENDED, extended.toString())

    if (from != EMPTY) params.put(PARAMETER_FROM, timestamp(from).toString())
    if (to != EMPTY) params.put(PARAMETER_TO, timestamp(to).toString())

    val body = fmService.get(constructRequest(REQUEST_USER_GETRECENTTRACKS, params))
    when (mapper.answerHasError(body)) {
      true -> {
        val error = mapper.readValue(body, ErrorAnswer::class.java)
        throw RuntimeException("Request was completed with error: ${error.error} - ${error.message}")
      }
      false -> return mapper.readValue(body, UserRecentTracks::class.java)
    }
  }
}