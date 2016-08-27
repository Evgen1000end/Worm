package com.demkin.core

import com.demkin.core.model.Authenticator
import com.demkin.core.services.User
import org.testng.annotations.Test

/**
 * Description of com.demkin.core
 * @author evgen1000end
 * @since 16.08.2016
 */
class UserTest {
  val userService = User()

  @Test(enabled = false)
  fun getLovedTracksAsStringTest() {
    val r = userService.getLovedTracks("Wi-Al", limit = 1000)
  }

  @Test
  fun getRecentTracksTest(){
    val r = userService.getRecentTracks("Wi-Al", limit = 10, from = "2016-08-27 16:00:00")
    println(r)
  }
}