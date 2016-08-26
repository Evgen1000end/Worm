package com.demkin.core

import com.demkin.core.http.*
import com.demkin.core.model.ArtistFullTracks
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
  val fmService = HttpLastFmService()

  val loved_tracks = "{\"lovedtracks\":{\"track\":[{\"name\":\"Casablanca Moon\",\"mbid\":\"a2e93367-f3e8-415b-9d4b-1688d221c617\",\"url\":\"https://www.last.fm/music/Slapp+Happy/_/Casablanca+Moon\",\"date\":{\"uts\":\"1438537829\",\"#text\":\"02 Aug 2015, 17:50\"},\"artist\":{\"name\":\"Slapp Happy\",\"mbid\":\"4be40614-e079-45b6-a7b9-deb1b11bf3c6\",\"url\":\"https://www.last.fm/music/Slapp+Happy\"},\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"extralarge\"}],\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"}}],\"@attr\":{\"user\":\"Wi-al\",\"page\":\"1\",\"perPage\":\"1\",\"totalPages\":\"335\",\"total\":\"335\"}}}"
  val artist_tracks = "{\"artisttracks\":{\"track\":[{\"artist\":{\"#text\":\"Genesis\",\"mbid\":\"c5725831-2596-48f1-8f1c-ebe237362860\"},\"name\":\"The Light Dies Down on Broadway\",\"streamable\":\"0\",\"mbid\":\"541e8781-9892-4da5-8d98-afb3a277327e\",\"album\":{\"#text\":\"The Lamb Lies Down On Broadway CD2 [2007 Remaster]\",\"mbid\":\"\"},\"url\":\"https://www.last.fm/music/Genesis/_/The+Light+Dies+Down+on+Broadway\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"extralarge\"}],\"date\":{\"uts\":\"1417263947\",\"#text\":\"29 Nov 2014, 12:25\"}}],\"@attr\":{\"user\":\"Wi-al\",\"artist\":\"Genesis\",\"page\":\"1\",\"perPage\":\"1\",\"totalPages\":\"0\",\"total\":\"0\"}}}"

  @Test(enabled = false)
  fun getStringForMappingTest(){

    val request = constructRequest("auth.getMobileSession", listOf(
            Pair("api_sig", SHARED_SECRET)
            ,Pair("username", "Wi-Al")
            ,Pair("password", "lastfmevgen1000end")
          //  Pair("user","Wi-Al")
    ))
    val result = fmService.invokeRequestAsString(request)
    println(result)
  }

  @Test
  fun parseUserLovedTrackTest(){
    val userLovedTracks = mapper.readValue(loved_tracks, UserLovedTracks::class.java)
    Assert.assertEquals(userLovedTracks.lovedtracks?.track?.get(0)?.name,"Casablanca Moon")
  }

  @Test
  fun parseGetArtistTracks(){
    val artistTracks = mapper.readValue(artist_tracks, ArtistFullTracks::class.java)

    println(artistTracks)
  }


}