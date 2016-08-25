package com.demkin.core

import com.demkin.core.http.httpParameters
import org.testng.Assert.assertEquals
import org.testng.annotations.Test

/**
 * Created by demkinev on 24.08.2016.
 */

class UtilsTest {

  @Test
  fun paramGenerateTest() {
    val paramArray = listOf(Pair("Eugene", "26"), Pair("Kate", "21"))
    val params = paramArray.httpParameters()
    assertEquals(params, "&Eugene=26&Kate=21")

  }
}
