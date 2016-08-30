package com.demkin

import org.testng.annotations.Test
import java.awt.Desktop
import java.net.URL

/**
 * Created by demkinev on 28.08.2016.
 */
class AuthTest {
  private val API_VERSION = "5.53"
  private val AUTH_URL = "https://oauth.vk.com/authorize?client_id={APP_ID}"+"&scope={PERMISSIONS}" +
          "&redirect_uri={REDIRECT_URI}&display={DISPLAY}&v={API_VERSION}&response_type=token"
  val SHEEP_URL = "http://cs521313.vk.me//u10484188//audios//3af33d453269.mp3?extra=M7Cn4q2HaROnlE6BkezJ7zdHq1Q9H6v01pdVeTwOVs5zfblpD70s71hgMMsRn8zY_Rv6IXhjWqr7GFFBkQplM4rTmD5pCUUdT-Mebpv8PVl3Yz8TaSYnecqysmag9bdhogh2bY1kPCSF"
  private fun auth(appId: String) {
    val reqUrl = AUTH_URL.replace("{APP_ID}",
            appId).replace("{PERMISSIONS}",
            "audio").replace("{REDIRECT_URI}",
            "https://oauth.vk.com/blank.html").replace("{DISPLAY}", "page").
            replace("{API_VERSION}", API_VERSION)
      Desktop.getDesktop().browse(URL(reqUrl).toURI())
  }

  @Test(enabled = false)
  fun authWithTokenTest(){
    auth("5608043")
  }

  @Test
  fun playMp3Stream(){
  }
}