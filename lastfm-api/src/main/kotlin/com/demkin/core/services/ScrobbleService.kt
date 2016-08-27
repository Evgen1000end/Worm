package com.demkin.core.services

import com.demkin.core.*
import com.demkin.core.http.constructRequest
import com.demkin.core.http.now
import com.demkin.core.model.ScrobbleData
import com.demkin.core.model.Session
import com.demkin.core.model.SignatureParams
import com.demkin.core.model.createSignature

class ScrobbleService(session: Session = Session()) : LastFmService(session) {

  fun scrobble(artistName: String, trackName: String) {
    val scrobbleData = ScrobbleData(artistName, trackName, now())
    return scrobble(scrobbleData)
  }

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
    val signatureParams = SignatureParams(TRACK_SCROBBLE, mapOf(
            Pair(PARAMETER_ARTIST, scrobbleData.artist ?: ""),
            Pair(PARAMETER_TRACK, scrobbleData.track ?: ""),
            Pair(PARAMETER_SK, session.key ?: ""),
            Pair(PARAMETER_TIMESTAMP, scrobbleData.timestamp.toString())))
    val signature = createSignature(signatureParams)
    val request = constructRequest(signatureParams, apiSig = signature)
    val body = fmService.post(request)
  }
}