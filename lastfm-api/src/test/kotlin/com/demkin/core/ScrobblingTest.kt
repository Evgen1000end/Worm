package com.demkin.core

import com.demkin.core.model.Authenticator
import com.demkin.core.services.ScrobbleService
import de.umass.lastfm.Track
import org.testng.annotations.Test
import java.util.*

class ScrobblingTest {
  @Test
  fun scrobbleTest(){
    val session = Authenticator().fetchSession(USERNAME, PASSWORD)
    val srobbleService = ScrobbleService(session)
    srobbleService.scrobble("The Doors", "Roadhouse Blues",(System.currentTimeMillis() / 1000).toInt())
  }
}