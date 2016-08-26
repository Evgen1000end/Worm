package com.demkin.core.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.*
import javax.annotation.Generated

/**
 * Description of com.demkin.core.model
 * @author evgen1000end
 * @since 16.08.2016
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name", "mbid", "url")
class Artist {
  @JsonProperty("name")
  var name: String? = null
  @JsonProperty("mbid")
  var mbid: String? = null
  @JsonProperty("url")
  var url: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("user", "page", "perPage", "totalPages", "total")
class Attr {
  @JsonProperty("user")
  var user: String? = null
  @JsonProperty("page")
  var page: String? = null
  @JsonProperty("perPage")
  var perPage: String? = null
  @JsonProperty("totalPages")
  var totalPages: String? = null
  @JsonProperty("total")
  var total: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("uts", "#text")
class Date {
  @JsonProperty("uts")
  var uts: String? = null
  @JsonProperty("#text")
  var text: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("#text", "size")
class Image {
  @JsonProperty("#text")
  var text: String? = null

  @JsonProperty("size")
  var size: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("lovedtracks")
class UserLovedTracks {
  @JsonProperty("lovedtracks")
  var lovedtracks: Lovedtracks? = null
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("track", "@attr")
class Lovedtracks {
  @JsonProperty("track")
  var track: List<Track> = ArrayList<Track>()
  @JsonProperty("@attr")
  var attr: Attr? = null
}


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("#text", "fulltrack")
class Streamable {
  @JsonProperty("#text")
  var text: String? = null
  @JsonProperty("fulltrack")
  var fulltrack: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("name", "mbid", "url", "date", "artist", "image", "streamable")
class Track {
  @JsonProperty("name")
  var name: String? = null
  @JsonProperty("mbid")
  var mbid: String? = null
  @JsonProperty("url")
  var url: String? = null
  @JsonProperty("date")
  var date: Date? = null
  @JsonProperty("artist")
  var artist: Artist? = null
  @JsonProperty("image")
  var image: List<Image> = ArrayList()
  @JsonProperty("streamable")
  var streamable: Streamable? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("error", "message")
@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorAnswer {
  @JsonProperty("error")
  var error: Int? = null
  @JsonProperty("message")
  var message: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("token")
class TokenResult {
  @JsonProperty("token")
  var token: String? = null
}