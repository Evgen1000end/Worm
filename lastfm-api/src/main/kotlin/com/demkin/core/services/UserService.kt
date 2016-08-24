package com.demkin.core.services

import com.demkin.core.API_KEY
import com.demkin.core.REQUEST_URL
import com.demkin.core.http.apiParam
import com.demkin.core.http.constructRequest
import com.demkin.core.http.invokeRequestAsString
import com.demkin.core.model.UserLovedTracks
import com.fasterxml.jackson.databind.ObjectMapper

/**
 * Description of com.demkin.core.services
 * @author evgen1000end
 * @since 16.08.2016
 */

//fun traverseJsonNode


class UserService {
    fun getLovedTracks(userName:String, limit:String ="50", page:String ="1"):String{

        val params = arrayOf(
                Pair("user",userName),
                Pair("api_key", API_KEY),
                Pair("limit",limit),
                Pair("page",page),
                Pair("format","json")).
                apiParam()

        val response = invokeRequestAsString(constructRequest("user.getlovedtracks",params))
        val r = response.component1() ?: response.component2().toString()
        val mapper = ObjectMapper()

        val root = mapper.readValue(r, UserLovedTracks::class.java)

        root.lovedtracks?.track?.forEach {
            println(it.name)
        }

        return r
    }
}