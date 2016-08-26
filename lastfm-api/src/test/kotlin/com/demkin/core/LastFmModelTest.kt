package com.demkin.core

import com.demkin.core.http.EMPTY
import com.demkin.core.http.constructRequest
import com.demkin.core.http.constructRequest
import com.demkin.core.http.invokeRequestAsString
import com.demkin.core.model.UserLovedTracks
import com.demkin.core.services.PARAMETER_LIMIT
import com.demkin.core.services.PARAMETER_PAGE
import com.demkin.core.services.PARAMETER_USER
import com.demkin.core.services.REQUEST_USER_GETLOVEDTRACKS
import com.fasterxml.jackson.databind.ObjectMapper
import org.testng.Assert
import org.testng.annotations.Test

class LastFmModelTest {

  val mapper = ObjectMapper()

  val loved_tracks = "{\"lovedtracks\":{\"track\":[{\"name\":\"Casablanca Moon\",\"mbid\":\"a2e93367-f3e8-415b-9d4b-1688d221c617\",\"url\":\"https://www.last.fm/music/Slapp+Happy/_/Casablanca+Moon\",\"date\":{\"uts\":\"1438537829\",\"#text\":\"02 Aug 2015, 17:50\"},\"artist\":{\"name\":\"Slapp Happy\",\"mbid\":\"4be40614-e079-45b6-a7b9-deb1b11bf3c6\",\"url\":\"https://www.last.fm/music/Slapp+Happy\"},\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"extralarge\"}],\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"}}],\"@attr\":{\"user\":\"Wi-al\",\"page\":\"1\",\"perPage\":\"1\",\"totalPages\":\"335\",\"total\":\"335\"}}}"

  @Test
  fun getStringForMappingTest(){

    ///2.0/?method=user.getfriends&user=rj&api_key=YOUR_API_KEY

    val request = constructRequest("auth.getMobileSession", listOf(
            Pair("api_sig", SHARED_SECRET)
            ,Pair("username", "Wi-Al")
            ,Pair("password", "lastfmevgen1000end")
          //  Pair("user","Wi-Al")
    ))
    val result = invokeRequestAsString(request)
    println(result)
  }

  @Test
  fun parseUserLovedTrackTest(){
    val userLovedTracks = mapper.readValue(loved_tracks, UserLovedTracks::class.java)
    Assert.assertEquals(userLovedTracks.lovedtracks?.track?.get(0)?.name,"Casablanca Moon")
  }
}