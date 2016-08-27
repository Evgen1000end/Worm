package com.demkin.core

import com.demkin.core.http.*
import com.demkin.core.model.Authenticator
import com.demkin.core.model.TokenResult
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.codec.digest.DigestUtils
import org.testng.annotations.Test

class AuthentificationTest {
  val fmService = HttpLastFmService()
  val mapper = ObjectMapper()

  @Test
  fun scrobbleTest(){

  }

  @Test
  fun authWithPasswordTest(){
    val auth = Authenticator()
    auth.byPassword(USERNAME, PASSWORD)
        
  }
}