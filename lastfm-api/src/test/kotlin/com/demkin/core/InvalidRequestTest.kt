package com.demkin.core

import com.demkin.core.http.requestToString
import org.testng.Assert
import org.testng.Assert.*
import org.testng.annotations.Test

/**
 * Description of com.demkin.core
 * @author evgen1000end
 * @since 16.08.2016
 */
class InvalidRequestTest {
    @Test
    fun badRequest() {
       val data = requestToString("http://ws.audioscrobbler.com/2.0/?method=user.getlovedtracks&user=rj&api_key=API_KEY&format=json")
       assertEquals(data, "Exception : HTTP Exception 403 Forbidden")
    }
}