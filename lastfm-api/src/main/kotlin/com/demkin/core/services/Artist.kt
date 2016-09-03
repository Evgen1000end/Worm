package com.demkin.core.services

import com.demkin.core.*
import com.demkin.core.http.answerHasError
import com.demkin.core.http.constructRequest
import com.demkin.core.model.ArtistTopTracks
import com.demkin.core.model.ErrorAnswer
import com.demkin.core.model.Session
import com.demkin.core.model.UserLovedTracks

/**
 * Created by demkinev on 31.08.2016.
 */
class Artist(session: Session = Session()) : LastFmService(session) {

  fun getTopTracks(artist: String, limit: Int = DEFAULT_LIMIT, page: Int = DEFAULT_PAGE): ArtistTopTracks {
    val params = mapOf(
            Pair(PARAMETER_ARTIST, artist),
            Pair(PARAMETER_LIMIT, limit.toString()),
            Pair(PARAMETER_PAGE, page.toString()))
    val body = fmService.get(constructRequest(REQUEST_ARTIST_GETTOPTRACLS, params))

    when (mapper.answerHasError(body)) {
      true -> {
        val error = mapper.readValue(body, ErrorAnswer::class.java)
        throw RuntimeException("Request was completed with error: ${error.error} - ${error.message}")
      }
      false -> return mapper.readValue(body, ArtistTopTracks::class.java)
    }
  }

}