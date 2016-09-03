package com.main

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.*
import javax.annotation.Generated


@JsonInclude(JsonInclude.Include.NON_NULL)
class AudioSearchResult {
  var response: List<Response> = ArrayList()
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Response {
  var aid: Int? = null
  @JsonProperty("owner_id")
  var ownerId: Int? = null
  var artist: String? = null
  var title: String? = null
  var duration: Int? = null
  var url: String? = null
  @JsonProperty("lyrics_id")
  var lyricsId: String? = null
  var genre: Int? = null
  var album: String? = null
}
