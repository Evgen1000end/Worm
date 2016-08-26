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
class Artist {
  var name: String? = null
  var mbid: String? = null
  var url: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Attr {
  var user: String? = null
  var page: String? = null
  var perPage: String? = null
  var totalPages: String? = null
  var total: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Date {
  var uts: String? = null
  @JsonProperty("#text")
  var text: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Image {
  @JsonProperty("#text")
  var text: String? = null
  var size: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserLovedTracks {
  var lovedtracks: Lovedtracks? = null
}


@JsonInclude(JsonInclude.Include.NON_NULL)
class Lovedtracks {
  var track: List<Track> = ArrayList<Track>()
  @JsonProperty("@attr")
  var attr: Attr? = null
}


@JsonInclude(JsonInclude.Include.NON_NULL)
class Streamable {
  @JsonProperty("#text")
  var text: String? = null
  var fulltrack: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Track {
  var name: String? = null
  var mbid: String? = null
  var url: String? = null
  var date: Date? = null
  var artist: Artist? = null
  var image: List<Image> = ArrayList()
  var streamable: Streamable? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorAnswer {
  var error: Int? = null
  var message: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class TokenResult {
  var token: String? = null
}

