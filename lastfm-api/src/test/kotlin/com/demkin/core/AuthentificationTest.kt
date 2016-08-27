package com.demkin.core

import com.demkin.core.http.HttpLastFmService
import com.demkin.core.model.Authenticator
import com.fasterxml.jackson.databind.ObjectMapper
import org.testng.annotations.Test

class AuthentificationTest {
  val fmService = HttpLastFmService()
  val mapper = ObjectMapper()

  @Test
  fun scrobbleTest() {

  }

  @Test
  fun authWithPasswordTest() {
    val auth = Authenticator()
    auth.fetchSession(USERNAME, PASSWORD + "2")

  }
}