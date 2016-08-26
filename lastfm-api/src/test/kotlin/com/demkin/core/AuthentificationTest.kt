package com.demkin.core

import com.demkin.core.http.*
import org.testng.annotations.Test

/**
 * Created by demkinev on 26.08.2016.
 */
class AuthentificationTest {
  val fmService = HttpLastFmService()

  @Test
  fun authTest(){
    val request = constructRequest("auth.getToken", addDefaultParams())
    val result =  fmService.invokeRequestAsString(request)
    println(result)
  }
}