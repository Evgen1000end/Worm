package com.demkin.core

import com.demkin.core.services.Artist
import org.testng.annotations.Test

/**
 * Created by demkinev on 31.08.2016.
 */
class ArtistTest {

  val artistService = Artist()

  @Test
  fun artistTopTest(){
    val res = artistService.getTopTracks("The Beatles")

    println(res)
  }
}