package com.demkin.core

import com.demkin.core.http.requestToString
import com.demkin.core.services.UserService
import org.testng.Assert
import org.testng.Assert.*
import org.testng.annotations.ExpectedExceptions
import org.testng.annotations.Test

/**
 * Description of com.demkin.core
 * @author evgen1000end
 * @since 16.08.2016
 */
class InvalidRequestTest {
    val userService = UserService()

    @Test(expectedExceptions = arrayOf(RuntimeException::class))
    fun badRequest() {
        val r = userService.getLovedTracks("Wi-Al44", limit = "1000")
    }
}