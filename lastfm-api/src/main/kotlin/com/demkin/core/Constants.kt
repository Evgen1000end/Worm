package com.demkin.core

/**
 * Description of com.demkin.core
 * @author evgen1000end
 * @since 16.08.2016
 */

/*
  URL
 */
const val REQUEST_URL = "http://ws.audioscrobbler.com/2.0/"
const val REQUEST_URL_HTTPS = "https://ws.audioscrobbler.com/2.0/"
const val REQUEST_SEPARATOR = "?method="
/*
  Methods names
 */
const val AUTH_GETMOBILESESSION = "auth.getMobileSession"
const val TRACK_SCROBBLE = "track.scrobble"
const val REQUEST_USER_GETLOVEDTRACKS = "user.getlovedtracks"
const val REQUEST_USER_GETRECENTTRACKS = "user.getRecentTracks"

/*
  Methods Parameters
 */
const val PARAMETER_USER = "user"
const val PARAMETER_LIMIT = "limit"
const val PARAMETER_PAGE = "page"
const val PARAMETER_EXTENDED = "extended"
const val PARAMETER_ERROR = "error"
const val PARAMETER_FORMAT = "format"
const val PARAMETER_API_KEY = "api_key"
const val PARAMETER_USERNAME = "username"
const val PARAMETER_PASSWORD = "password"
const val PARAMETER_METHOD = "method"
const val PARAMETER_ARTIST = "artist"
const val PARAMETER_TRACK = "track"
const val PARAMETER_SK = "sk"
const val PARAMETER_TIMESTAMP = "timestamp"
const val PARAMETER_TO = "to"
const val PARAMETER_FROM = "from"
/*
  Parameters default values
 */
const val DEFAULT_LIMIT = 50
const val DEFAULT_PAGE = 1
const val DEFAULT_EXTENDED = 1
const val DEFAULT_FORMAT = "json"

/*
  Additional
 */
const val EMPTY = "-"