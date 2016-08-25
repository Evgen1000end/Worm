package com.demkin.core

import com.demkin.core.http.constructRequest
import com.demkin.core.http.invokeRequestAsString
import com.demkin.core.services.PARAMETER_LIMIT
import com.demkin.core.services.PARAMETER_PAGE
import com.demkin.core.services.PARAMETER_USER
import com.demkin.core.services.REQUEST_USER_GETLOVEDTRACKS
import org.testng.annotations.Test

/**
 * Created by demkinev on 25.08.2016.
 */
class LastFmModelTest {

  @Test
  fun parseUserLovedTrackTest(){
    val request = constructRequest(REQUEST_USER_GETLOVEDTRACKS, listOf(
            Pair(PARAMETER_USER, "Wi-Al"),
            Pair(PARAMETER_LIMIT,1.toString())))
    val result = invokeRequestAsString(request)

    println(result)
  }

}