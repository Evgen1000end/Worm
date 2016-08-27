package com.demkin.core.services

import com.demkin.core.API_KEY
import com.demkin.core.SHARED_SECRET
import com.demkin.core.http.constructRequest
import com.demkin.core.http.httpParameters
import com.demkin.core.model.AUTH_GETMOBILESESSION
import com.demkin.core.model.ScrobbleData
import com.demkin.core.model.Session
import com.demkin.core.model.createSignature
import java.util.*

/**
 * Created by demkinev on 27.08.2016.
 */

const val TRACK_SCROBBLE = "track.scrobble"

class ScrobbleService(session: Session = Session()):LastFmService(session){

  fun scrobble(artistName: String, trackName: String, timestamp: Int) {
    val scrobbleData = ScrobbleData(artistName, trackName, timestamp)
    return scrobble(scrobbleData)
  }

  fun scrobble(scrobbleData: ScrobbleData) {
  //  MapUtilities.nullSafePut(params, "album", scrobbleData.getAlbum())
  //  MapUtilities.nullSafePut(params, "albumArtist", scrobbleData.getAlbumArtist())
  //  MapUtilities.nullSafePut(params, "duration", scrobbleData.getDuration())
  //  MapUtilities.nullSafePut(params, "mbid", scrobbleData.getMusicBrainzId())
  //  MapUtilities.nullSafePut(params, "trackNumber", scrobbleData.getTrackNumber())
  //  MapUtilities.nullSafePut(params, "streamId", scrobbleData.getStreamId())
  // params.put("chosenByUser", StringUtilities.convertFromBoolean(scrobbleData.isChosenByUser()))
  //val result = Caller.getInstance().call("track.scrobble", session, params)

    val signature = createSignature(
            mapOf(Pair("api_key", API_KEY), Pair("chosenByUser","1"),
                    Pair("method", TRACK_SCROBBLE),
                    Pair("artist", scrobbleData.artist ?: ""),
                    Pair("track", scrobbleData.track ?: ""), Pair("sk", session.key ?: ""),
                    Pair("timestamp", scrobbleData.timestamp.toString())), SHARED_SECRET)

    val request = constructRequest(TRACK_SCROBBLE,
            mapOf(Pair("api_sig", signature), Pair("chosenByUser","1"),
                      Pair("artist", scrobbleData.artist ?: ""),
                    Pair("track", scrobbleData.track ?: ""), Pair("sk", session.key ?: ""),
                    Pair("timestamp", scrobbleData.timestamp.toString())).httpParameters() )
    val body = fmService.post(request)
  }

}