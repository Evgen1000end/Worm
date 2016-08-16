package com.demkin.core.services

import com.demkin.core.API_KEY
import com.demkin.core.REQUEST_URL
import com.demkin.core.http.invokeRequestAsString

/**
 * Description of com.demkin.core.services
 * @author evgen1000end
 * @since 16.08.2016
 */
class UserService {
    fun getLovedTracks(userName:String):String{

        val response = invokeRequestAsString("$REQUEST_URL?method=user.getlovedtracks&user=$userName&api_key=$API_KEY&format=json")

        return response.component1() ?: response.component2().toString()
    }

}