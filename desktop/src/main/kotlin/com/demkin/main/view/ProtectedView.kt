package ru.demkin.view

import com.demkin.core.model.ArtistTopTracks
import com.demkin.core.model.Session
import com.demkin.core.services.Artist
import com.demkin.core.services.ScrobbleService
import com.demkin.core.services.User
import com.demkin.main.view.OraclePlayerView
import com.demkin.main.view.PlayerPane
import com.main.AudioService
import javafx.beans.binding.DoubleBinding
import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.geometry.Pos
import javafx.scene.Node
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.ProgressIndicator
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.BorderPane
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.util.StringConverter
import ru.demkin.app.Styles
import ru.demkin.app.StylesPlayer
import tornadofx.*
import tornadofx.control.ListItem
import tornadofx.control.ListMenu
import java.text.FieldPosition
import java.text.Format
import java.text.ParsePosition

/**
 * Description of ru.demkin.view
 * @author evgen1000end
 * @since 07.08.2016
 */
class ProtectedView(val session: Session) : View() {
  override val root = Form().addClass(StylesPlayer.login)
  val artist = SimpleStringProperty()
  val song = SimpleStringProperty()
  val SHEEP_URL = "http://cs1-37v4.vk-cdn.net/p15/8d1c18c9960eda.mp3?extra=Ptm8GwuD02AiDR7c1vHX1xTfpxPzfqDn8CfDSpSbp0vpFVpIlT_gJnob1BUUnLhrFldWmz1Gk_E1i1oUbNqePyFhq1m-8wNHsj05xq-s2dsYQGTaxN2s1_yF1InQHvMoO480ai_ajKs"

  val selectedSong = SimpleStringProperty()
  val userService = User()
  val artistService = Artist()
  var topTracks = ArtistTopTracks()
  val imageView = ImageView()
  var player:OraclePlayerView =OraclePlayerView(MediaPlayer(Media(SHEEP_URL)))
  val vkService = AudioService()
  val borderPane= BorderPane()




  init {

    title = "Send Artist/Song information on server"
    with(root) {

      borderPane.top = hbox {
        spacing = 10.0

        val cbSongList = ComboBox<String>()
        cbSongList.bind(property = selectedSong)
        cbSongList.bind(property = song)
        add(cbSongList)

        cbSongList.valueProperty().addListener { observableValue, s1, s2 ->
          val request = artist.value+" "+song.value

          val res =  vkService.searchAudioByRequest(request)

          if (res.response.size>0) {
            val trackUrl = res.response.get(0).url ?: ""

            println("trackUrl " +trackUrl)
            player.changeTrack(Media(trackUrl))
          }
         }


        button("Find top tracks") {
          setOnAction {
            cbSongList.items.clear()
            topTracks = artistService.getTopTracks(artist.value)

            if (topTracks.toptracks?.track?.size ?: 0 >0){
              val artistImage = topTracks.toptracks?.track?.get(0)?.image
              val artistImageUrl =  artistImage?.get(3)?.text
              val index = artistImageUrl?.indexOf("s")
              val tempBefore = artistImageUrl?.substring(0, index ?: 0)
              val tempAfter = artistImageUrl?.substring(index?.plus(1) ?:0 , artistImageUrl.length)

              println(tempBefore+ " "+tempAfter)

              val correctUrl = tempBefore+tempAfter

              println(correctUrl)
              val img = Image(correctUrl,700.0, 700.0, false, false)
              imageView.image = img
            }

            topTracks.toptracks?.track?.forEach {
              cbSongList.items.add(it.name)
            }
          }
        }

        vbox {
          hbox {
            label("Artist name")
            textfield().bind(artist)
          }

          hbox {
            label("Song name")
            textfield().bind(song)
          }

          button("Scrobble it!") {
            setOnAction {
              send()
            }
          }
        }
      }

      borderPane.center = vbox {

        alignment = Pos.CENTER

        add(imageView)

        imageView.prefHeight(800.0)
        imageView.prefWidth(800.0)

        val img = Image("http://lastfm-img2.akamaized.net/i/u/300x300/64eb091a1d34441697072b74584e3727.png",
                  700.0, 700.0, false, false)
        imageView.image = img

      }

      borderPane.bottom =
              hbox {
                vbox {
                  label("test").bind(selectedSong)
                  add(player)
                }
              }
      add(borderPane)
    }
  }

  private fun Button.send() {
    val service = ScrobbleService(session)
    service.scrobble(artist.value, song.value)
  }
}
