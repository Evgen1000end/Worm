package com.demkin.core

import com.demkin.core.services.UserService
import org.testng.annotations.Test

/**
 * Description of com.demkin.core
 * @author evgen1000end
 * @since 16.08.2016
 */
class UserServiceTest {
  val userService = UserService()

  @Test(enabled = false)
  fun getLovedTracksAsStringTest() {
    val r = userService.getLovedTracks("Wi-Al", limit = 1000)
  }

  @Test
  fun getRecentTracksTest(){
    val r = userService.getRecentTracks("Wi-Al", limit = 1000)
    println(r)
  }
}