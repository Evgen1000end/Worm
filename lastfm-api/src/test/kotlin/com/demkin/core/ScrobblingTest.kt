package com.demkin.core

import com.demkin.core.model.Authenticator
import com.demkin.core.services.ScrobbleService
import de.umass.lastfm.Track
import org.testng.annotations.Test
import java.util.*

class ScrobblingTest {
  @Test
  fun scrobbleTest(){
    val session = Authenticator().byPassword(USERNAME, PASSWORD)
    val srobbleService = ScrobbleService(session)
    srobbleService.scrobble("Pink Floyd", "Atom Heart Mother",(System.currentTimeMillis() / 1000).toInt() , session)

  //  val session = de.umass.lastfm.Authenticator.getMobileSession(USERNAME, PASSWORD, API_KEY, SHARED_SECRET)

 //   val now = (System.currentTimeMillis() / 1000).toInt()
//    val result = Track.scrobble("Pink Floyd", "Embryo", now, session)

  }

  @Test
  fun getCurrentTimeInSecond(){
    val l  = System.currentTimeMillis()
    val ls = l/1000
    val i = ls.toInt()

    println("$l $ls $i")
  }

}