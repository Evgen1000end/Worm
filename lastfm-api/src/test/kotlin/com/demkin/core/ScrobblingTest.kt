package com.demkin.core

import com.demkin.core.model.Authenticator
import com.demkin.core.services.ScrobbleService
import org.testng.annotations.Test

class ScrobblingTest {
  @Test
  fun scrobbleTest() {
    val session = Authenticator().fetchSession(USERNAME, PASSWORD)
    val srobbleService = ScrobbleService(session)
    srobbleService.scrobble("The Doors", "Yes, The River Knows")
  }
}