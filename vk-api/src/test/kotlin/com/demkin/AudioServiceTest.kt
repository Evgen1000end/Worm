package com.demkin

import com.main.AudioService
import org.testng.annotations.Test

/**
 * Created by demkinev on 03.09.2016.
 */
class AudioServiceTest {

  @Test
  fun getAudioQuery(){

    val service = AudioService()

    val res = service.searchAudioByRequest("Floyd")


  }

}