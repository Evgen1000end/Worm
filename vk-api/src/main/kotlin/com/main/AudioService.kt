package com.main

import com.demkin.core.model.UserLovedTracks


class AudioService: VkApiService() {
  fun searchAudioByRequest(query:String):AudioSearchResult{
    val params = mapOf(Pair("q",query))
    val body = fmService.get(constructRequest(AUDIO_SEARCH_METHOD, params))



    return mapper.readValue(sanityJSON(body), AudioSearchResult::class.java)
  }

}