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
@JsonIgnoreProperties(ignoreUnknown = true)
class Artist {
  var name: String? = null
  var mbid: String? = null
  var url: String? = null
  @JsonProperty("#text")
  var text: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Attr {
  var user: String? = null
  var artist:String? = null
  var page: String? = null
  var perPage: String? = null
  var totalPages: String? = null
  var total: String? = null
  var date:String? = null
  var uts:String? = null
  @JsonProperty("for")
  var _for:String? = null
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
  var lovedtracks: Tracks? = null
}


@JsonInclude(JsonInclude.Include.NON_NULL)
class Tracks {
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
@JsonIgnoreProperties(ignoreUnknown = true)
class Track {
  var name: String? = null
  var mbid: String? = null
  var url: String? = null
  var date: Date? = null
  var artist: Artist? = null
  var image: List<Image> = ArrayList()
  var album:Album? = null
  var attr:Attr? = null

  //TODO - No Streamable properties!!
 // @JsonProperty("streamable")
  //var streamable: Streamable? = null
 // @JsonProperty("streamable")
 // var streamableStr:String? = null


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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class Album {
  @JsonProperty("#text")
  var text: String? = null
  var mbid: String? = null
  var name:String? = null
  var url:String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class ArtistFullTracks {
  var artisttracks: Tracks? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Registered {
  @JsonProperty("#text")
  var text: String? = null
  var unixtime: String? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
class User {
  var name: String? = null
  var realname: String? = null
  var image: List<Image> = ArrayList()
  var url: String? = null
  var country: String? = null
  var age: String? = null
  var gender: String? = null
  var subscriber: String? = null
  var playcount: String? = null
  var playlists: String? = null
  var bootstrap: String? = null
  var registered: Registered? = null
  var type: String? = null

  @JsonProperty("scrobblesource")
  var scrobblesource: String? = null
  @JsonProperty("recenttrack")
  var recenttrack: Track? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class Friends {
  var user: List<User> = ArrayList()
  @JsonProperty("@attr")
  var attr: Attr? = null
}

@JsonInclude(JsonInclude.Include.NON_NULL)
class UserFriends {
  var friends: Friends? = null
}