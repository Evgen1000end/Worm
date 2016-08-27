package com.demkin.core

import com.demkin.core.http.now
import com.demkin.core.http.timestamp
import org.testng.annotations.Test
import java.time.LocalDateTime

/**
 * Created by demkinev on 27.08.2016.
 */
class UtilsTest {


  @Test
  fun timestampTest() {
    println(timestamp(LocalDateTime.now()))
    println(now())
    println(timestamp("1970-01-01 00:00:00"))
    println(timestamp(LocalDateTime.of(1970, 1, 1, 0, 0, 0)))
  }
}