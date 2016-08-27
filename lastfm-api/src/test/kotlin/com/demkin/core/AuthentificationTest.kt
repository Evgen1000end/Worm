package com.demkin.core

import com.demkin.core.http.*
import com.demkin.core.model.TokenResult
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.codec.digest.DigestUtils
import org.testng.annotations.Test

class AuthentificationTest {
  val fmService = HttpLastFmService()
  val mapper = ObjectMapper()

//{"session":{"subscriber":0,"name":"Wi-al","key":"dcb842602b7e22386dd552b90985f778"}}

  @Test
  fun scrobbleTest(){

  }


  @Test
  fun authTest(){
    val result =  fmService.invokeRequestAsString(
            constructRequest("auth.getToken", addDefaultParams()))
    val token = mapper.readValue(result, TokenResult::class.java)
    val req = "http://www.last.fm/api/auth/?api_key=$API_KEY&token=${token.token}"

    val apiSig = DigestUtils.md5Hex( "api_key${API_KEY}methodauth.getSessiontoken${token.token}$SHARED_SECRET" )

    //сделать новый токен

//    val result2 =  fmService.invokeRequestAsString(
//            constructRequest("auth.getToken", addDefaultParams()+"&api_sig=$apiSig"))
//    val token2 = mapper.readValue(result, TokenResult::class.java)
    //

    val request2 = constructRequest("auth.getSession", listOf(
            Pair("api_sig", apiSig)
            ,Pair("token", token.token ?: "")
           ))


    //api_keyxxxxxxxxmethodauth.getSessiontokenxxxxxxx

    println(request2)

    var resp = fmService.invokeRequestAsString(request2)

    println(resp)

  }
}