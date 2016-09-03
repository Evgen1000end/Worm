package ru.demkin.view

import com.demkin.core.model.ArtistTopTracks
import com.demkin.core.model.Session
import com.demkin.core.services.Artist
import com.demkin.core.services.ScrobbleService
import com.demkin.core.services.User
import com.demkin.main.view.OraclePlayerView
import com.demkin.main.view.PlayerPane
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
  val SHEEP_URL = "http://cs521313.vk.me//u10484188//audios//3af33d453269.mp3?extra=M7Cn4q2HaROnlE6BkezJ7zdHq1Q9H6v01pdVeTwOVs5zfblpD70s71hgMMsRn8zY_Rv6IXhjWqr7GFFBkQplM4rTmD5pCUUdT-Mebpv8PVl3Yz8TaSYnecqysmag9bdhogh2bY1kPCSF"

  val selectedSong = SimpleStringProperty()
  val userService = User()
  val artistService = Artist()
  var topTracks = ArtistTopTracks()
  val imageView = ImageView()


  init {
    title = "Send Artist/Song information on server"
    with(root) {



      val borderPane = BorderPane()

      borderPane.top = hbox {
        spacing = 10.0

        val cbSongList = ComboBox<String>()
        cbSongList.bind(property = selectedSong)
        cbSongList.bind(property = song)
        add(cbSongList)
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

      borderPane.bottom = hbox {
        vbox {
          label("test").bind(selectedSong)
          add(OraclePlayerView(MediaPlayer(Media(SHEEP_URL))))
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
