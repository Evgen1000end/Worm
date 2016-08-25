package com.demkin.core

import com.demkin.core.services.CommonService
import org.testng.annotations.Test

/**
 * Created by demkinev on 25.08.2016.
 */
class Temporary {

  val service = CommonService()

  @Test
  fun getStringResult() {
    val r = service.getRequest("user.getartisttracks", listOf(
            Pair("user", "Wi-Al"),
            Pair("artist", "Genesis")))

    println(r)
  }

}