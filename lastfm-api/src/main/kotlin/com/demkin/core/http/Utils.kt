package com.demkin.core.http

import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs
import com.sun.net.httpserver.Authenticator

/**
 * Description of com.demkin.core.http
 * @author evgen1000end
 * @since 16.08.2016
 */

fun requestToString(path:String):String  {
    val (request, response, result) = path.httpGet().responseString()
    val (data, error) = result
    println(response)
    println("Data: "+data)
    println("Error: "+error)
    return data ?: error.toString()
}

fun invokeRequestAsString(path:String) = path.httpGet().responseString().third
