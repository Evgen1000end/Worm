package com.demkin.core

import com.demkin.core.services.User
import org.testng.annotations.Test

/**
 * Description of com.demkin.core
 * @author evgen1000end
 * @since 16.08.2016
 */
class InvalidRequestTest {
  val userService = User()

  @Test(expectedExceptions = arrayOf(RuntimeException::class))
  fun badRequest() {
    val r = userService.getLovedTracks("Wi-Al44", limit = 1000)
  }
}