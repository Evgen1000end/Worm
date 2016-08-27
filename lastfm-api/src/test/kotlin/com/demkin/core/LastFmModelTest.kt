package com.demkin.core

import com.demkin.core.http.*
import com.demkin.core.model.*
import com.demkin.core.*
import com.fasterxml.jackson.databind.ObjectMapper
import org.testng.Assert
import org.testng.annotations.Test

class LastFmModelTest {
  val mapper = ObjectMapper()
  val fmService = HttpLastFmService()

  val loved_tracks = "{\"lovedtracks\":{\"track\":[{\"name\":\"Casablanca Moon\",\"mbid\":\"a2e93367-f3e8-415b-9d4b-1688d221c617\",\"url\":\"https://www.last.fm/music/Slapp+Happy/_/Casablanca+Moon\",\"date\":{\"uts\":\"1438537829\",\"#text\":\"02 Aug 2015, 17:50\"},\"artist\":{\"name\":\"Slapp Happy\",\"mbid\":\"4be40614-e079-45b6-a7b9-deb1b11bf3c6\",\"url\":\"https://www.last.fm/music/Slapp+Happy\"},\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/4bd3549f4aa34570967800bc68b9bdbb.png\",\"size\":\"extralarge\"}],\"streamable\":{\"#text\":\"0\",\"fulltrack\":\"0\"}}],\"@attr\":{\"user\":\"Wi-al\",\"page\":\"1\",\"perPage\":\"1\",\"totalPages\":\"335\",\"total\":\"335\"}}}"
  val artist_tracks = "{\"artisttracks\":{\"track\":[{\"artist\":{\"#text\":\"Genesis\",\"mbid\":\"c5725831-2596-48f1-8f1c-ebe237362860\"},\"name\":\"The Light Dies Down on Broadway\",\"streamable\":\"0\",\"mbid\":\"541e8781-9892-4da5-8d98-afb3a277327e\",\"album\":{\"#text\":\"The Lamb Lies Down On Broadway CD2 [2007 Remaster]\",\"mbid\":\"\"},\"url\":\"https://www.last.fm/music/Genesis/_/The+Light+Dies+Down+on+Broadway\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/baa10ac54ec44421b7ecb09f265ea55b.png\",\"size\":\"extralarge\"}],\"date\":{\"uts\":\"1417263947\",\"#text\":\"29 Nov 2014, 12:25\"}}],\"@attr\":{\"user\":\"Wi-al\",\"artist\":\"Genesis\",\"page\":\"1\",\"perPage\":\"1\",\"totalPages\":\"0\",\"total\":\"0\"}}}"
  val user_friends = "{\"friends\":{\"user\":[{\"name\":\"zedshtein\",\"realname\":\"Artyom\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/88873dd8b29aa5c83a9178f983ef3dd0.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/88873dd8b29aa5c83a9178f983ef3dd0.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/88873dd8b29aa5c83a9178f983ef3dd0.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/88873dd8b29aa5c83a9178f983ef3dd0.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/zedshtein\",\"country\":\"\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"217996\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2007-12-01 15:15:14\",\"unixtime\":\"1196522114\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\"},{\"name\":\"AlfvenResonator\",\"realname\":\"Женя\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/173559b01511492dc9f95954f9a01f2c.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/173559b01511492dc9f95954f9a01f2c.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/173559b01511492dc9f95954f9a01f2c.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/173559b01511492dc9f95954f9a01f2c.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/AlfvenResonator\",\"country\":\"Russian Federation\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"46928\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2013-04-10 14:46:26\",\"unixtime\":\"1365605186\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Peter Hammill\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Peter+Hammill\"},\"album\":{\"name\":\"Thin Air\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Peter+Hammill/Thin+Air\"},\"name\":\"Undone\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Peter+Hammill/_/Undone\",\"@attr\":{\"date\":\"25 Aug 2016, 19:22\",\"uts\":\"1472152931\"}}},{\"name\":\"sk_maniak\",\"realname\":\"Саша\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/5808bfbc9ee7444ec422e47a758204aa.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/5808bfbc9ee7444ec422e47a758204aa.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/5808bfbc9ee7444ec422e47a758204aa.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/5808bfbc9ee7444ec422e47a758204aa.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/sk_maniak\",\"country\":\"Ukraine\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"51870\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2011-02-02 16:20:46\",\"unixtime\":\"1296663646\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Lotic\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Lotic\"},\"album\":{\"name\":\"\",\"mbid\":\"\",\"url\":\"\"},\"name\":\"Boiler Room Berlin DJ Set\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Lotic/_/Boiler+Room+Berlin+DJ+Set\",\"@attr\":{\"date\":\"27 Jul 2016, 21:00\",\"uts\":\"1469653210\"}}},{\"name\":\"Ranunculus\",\"realname\":\"Ranunculus Ranunculaceæ\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/893b440c8c4ce535f35bfc05eabbc858.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/893b440c8c4ce535f35bfc05eabbc858.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/893b440c8c4ce535f35bfc05eabbc858.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/893b440c8c4ce535f35bfc05eabbc858.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/Ranunculus\",\"country\":\"Russian Federation\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"119293\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2007-01-11 13:32:56\",\"unixtime\":\"1168522376\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Fruupp\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Fruupp\"},\"album\":{\"name\":\"Prince Of Heaven's Eyes\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Fruupp/Prince+Of+Heaven%27s+Eyes\"},\"name\":\"Jaunting Car\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Fruupp/_/Jaunting+Car\",\"@attr\":{\"date\":\"31 Jul 2016, 21:33\",\"uts\":\"1470000817\"}}},{\"name\":\"HaHaHaYoureDead\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/eb109603f30141abc193810039d86f77.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/eb109603f30141abc193810039d86f77.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/eb109603f30141abc193810039d86f77.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/eb109603f30141abc193810039d86f77.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/HaHaHaYoureDead\",\"country\":\"\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"43383\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2012-12-06 11:42:16\",\"unixtime\":\"1354794136\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Bohren & der Club of Gore\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Bohren+&+der+Club+of+Gore\"},\"album\":{\"name\":\"Sunset Mission\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Bohren+&+der+Club+of+Gore/Sunset+Mission\"},\"name\":\"Painless Steel\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Bohren+&+der+Club+of+Gore/_/Painless+Steel\",\"@attr\":{\"date\":\"18 Apr 2016, 06:37\",\"uts\":\"1460961422\"}}},{\"name\":\"juniorsimadon\",\"realname\":\"Hélio\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/8198e47a095f415dc128957e74365db6.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/8198e47a095f415dc128957e74365db6.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/8198e47a095f415dc128957e74365db6.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/8198e47a095f415dc128957e74365db6.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/juniorsimadon\",\"country\":\"Brazil\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"57805\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2011-07-29 01:53:32\",\"unixtime\":\"1311904412\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Sigur Rós\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Sigur+R%C3%B3s\"},\"album\":{\"name\":\"Með suð í eyrum við spilum endalaust\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Sigur+R%C3%B3s/Me%C3%B0+su%C3%B0+%C3%AD+eyrum+vi%C3%B0+spilum+endalaust\"},\"name\":\"Fljótavík\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Sigur+R%C3%B3s/_/Flj%C3%B3tav%C3%ADk\",\"@attr\":{\"date\":\"17 Aug 2014, 19:29\",\"uts\":\"1408303776\"}}},{\"name\":\"Kcrimso\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/ccb26eb863d24280c239ffbdd4c1b05f.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/ccb26eb863d24280c239ffbdd4c1b05f.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/ccb26eb863d24280c239ffbdd4c1b05f.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/ccb26eb863d24280c239ffbdd4c1b05f.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/Kcrimso\",\"country\":\"Finland\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"96406\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2004-12-30 19:21:49\",\"unixtime\":\"1104434509\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\"},{\"name\":\"Mr_Place\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/b970bfa45f9e45b7c556d569da60b813.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/b970bfa45f9e45b7c556d569da60b813.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/b970bfa45f9e45b7c556d569da60b813.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/b970bfa45f9e45b7c556d569da60b813.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/Mr_Place\",\"country\":\"Russian Federation\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"14783\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2008-08-08 20:20:32\",\"unixtime\":\"1218226832\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Irma Thomas\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Irma+Thomas\"},\"album\":{\"name\":\"The Instant And Minit Story 2\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Irma+Thomas/The+Instant+And+Minit+Story+2\"},\"name\":\"It's Raining\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Irma+Thomas/_/It%27s+Raining\",\"@attr\":{\"date\":\"12 Mar 2014, 16:19\",\"uts\":\"1394641154\"}}},{\"name\":\"Otters_Pocket\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/ce2ada4d66044c88ccdfa4f05ad17e38.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/ce2ada4d66044c88ccdfa4f05ad17e38.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/ce2ada4d66044c88ccdfa4f05ad17e38.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/ce2ada4d66044c88ccdfa4f05ad17e38.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/Otters_Pocket\",\"country\":\"United Kingdom\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"115340\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2007-05-03 21:20:34\",\"unixtime\":\"1178227234\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Black Sabbath\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Black+Sabbath\"},\"album\":{\"name\":\"Master of Reality (Remastered)\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Black+Sabbath/Master+of+Reality+(Remastered)\"},\"name\":\"Children of the Grave\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Black+Sabbath/_/Children+of+the+Grave\",\"@attr\":{\"date\":\"25 Aug 2016, 22:57\",\"uts\":\"1472165842\"}}},{\"name\":\"fetus989\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/fetus989\",\"country\":\"\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"FIXME\",\"playcount\":\"46060\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":\"2008-06-07 04:02:11\",\"unixtime\":\"1212811331\"},\"type\":\"FIXME\",\"scrobblesource\":\"FIXME\",\"recenttrack\":{\"artist\":{\"name\":\"Childish Gambino\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Childish+Gambino\"},\"album\":{\"name\":\"Because the Internet\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Childish+Gambino/Because+the+Internet\"},\"name\":\"I. Flight Of The Navigator\",\"mbid\":\"\",\"url\":\"https://www.last.fm/music/Childish+Gambino/_/I.+Flight+Of+The+Navigator\",\"@attr\":{\"date\":\"24 Jan 2014, 20:50\",\"uts\":\"1390596639\"}}}],\"@attr\":{\"for\":\"Wi-al\",\"page\":\"1\",\"perPage\":\"10\",\"totalPages\":\"15\",\"total\":\"141\"}}}"
  val user_info = "{\"user\":{\"name\":\"Wi-al\",\"realname\":\"Евгений\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/9faab925524e4e68ce7c291257a87aaf.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/9faab925524e4e68ce7c291257a87aaf.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/9faab925524e4e68ce7c291257a87aaf.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/9faab925524e4e68ce7c291257a87aaf.png\",\"size\":\"extralarge\"}],\"url\":\"https://www.last.fm/user/Wi-al\",\"country\":\"Russian Federation\",\"age\":\"0\",\"gender\":\"n\",\"subscriber\":\"0\",\"playcount\":\"25501\",\"playlists\":\"0\",\"bootstrap\":\"0\",\"registered\":{\"#text\":1340014405,\"unixtime\":\"1340014405\"},\"type\":\"user\"}}"
  val recent_tracks = "{\"recenttracks\":{\"track\":[{\"artist\":{\"name\":\"Iron Maiden\",\"mbid\":\"ca891d65-d9b0-4258-89f7-e6ba29d83767\",\"url\":\"https://www.last.fm/music/Iron+Maiden\",\"image\":[{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/34s/3232796779fd4f4184e7bb4505657afd.png\",\"size\":\"small\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/64s/3232796779fd4f4184e7bb4505657afd.png\",\"size\":\"medium\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/174s/3232796779fd4f4184e7bb4505657afd.png\",\"size\":\"large\"},{\"#text\":\"https://lastfm-img2.akamaized.net/i/u/300x300/3232796779fd4f4184e7bb4505657afd.png\",\"size\":\"extralarge\"}]},\"loved\":\"0\",\"name\":\"The Number of the Beast (1982)\",\"streamable\":\"0\",\"mbid\":\"\",\"album\":{\"#text\":\"\",\"mbid\":\"\"},\"url\":\"https://www.last.fm/music/Iron+Maiden/_/The+Number+of+the+Beast+(1982)\",\"image\":[{\"#text\":\"\",\"size\":\"small\"},{\"#text\":\"\",\"size\":\"medium\"},{\"#text\":\"\",\"size\":\"large\"},{\"#text\":\"\",\"size\":\"extralarge\"}],\"date\":{\"uts\":\"1468608892\",\"#text\":\"15 Jul 2016, 18:54\"}}],\"@attr\":{\"user\":\"Wi-al\",\"page\":\"1\",\"perPage\":\"1\",\"totalPages\":\"25494\",\"total\":\"25494\"}}}"

  @Test(enabled = false)
  fun getStringForMappingTest(){

    val request = constructRequest("auth.getMobileSession", mapOf(
            Pair("api_sig", SHARED_SECRET)
            ,Pair("username", "Wi-Al")
            ,Pair("password", "lastfmevgen1000end")
    ))
    val result = fmService.get(request)
    println(result)
  }

  @Test
  fun parseUserLovedTrackTest(){
    val userLovedTracks = mapper.readValue(loved_tracks, UserLovedTracks::class.java)
    Assert.assertEquals(userLovedTracks.lovedtracks?.track?.get(0)?.name,"Casablanca Moon")
  }

  @Test
  fun parseGetArtistTracksTest(){
    val artistTracks = mapper.readValue(artist_tracks, ArtistFullTracks::class.java)

    //TODO - Assert
    println(artistTracks)
  }


  @Test
  fun parseUserFriendsTest(){
    val userFriends = mapper.readValue(user_friends, UserFriends::class.java)

    println(userFriends)
  }

  @Test
  fun parseUserInfoTest(){
    val userInfo = mapper.readValue(user_info, UserInfo::class.java)

    println(userInfo)
  }

  @Test
  fun parseRecentTrackTest(){
    val recentTracks = mapper.readValue(recent_tracks, UserRecentTracks::class.java)
    println(recentTracks)
  }
}