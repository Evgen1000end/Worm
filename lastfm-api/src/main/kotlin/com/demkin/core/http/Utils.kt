package com.demkin.core.http

import com.demkin.core.REQUEST_URL
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

fun Array<Pair<String, String>>.apiParam():String {
   return this.map{"&${it.first}=${it.second}"}.reduce{ a, b -> "$a$b"}
}

fun constructRequest(methodName:String, params:String):String{
    return "$REQUEST_URL?method=$methodName$params"
}

fun requestToString(path:String):String  {
    val (request, response, result) = path.httpGet().responseString()
    val (data, error) = result
    println(response)
    println("Data: "+data)
    println("Error: "+error)
    return data ?: error.toString()
}

fun invokeRequestAsString(path:String) = path.httpGet().responseString().third
