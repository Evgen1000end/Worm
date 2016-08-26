package com.demkin.core

import com.demkin.core.http.addDefaultParams
import com.demkin.core.http.constructRequest
import com.demkin.core.http.constructRequest
import com.demkin.core.http.invokeRequestAsString
import org.testng.annotations.Test

/**
 * Created by demkinev on 26.08.2016.
 */
class AuthentificationTest {

  @Test
  fun authTest(){
    val request = constructRequest("auth.getToken", addDefaultParams())
    val result = invokeRequestAsString(request)
    println(result)
  }
}