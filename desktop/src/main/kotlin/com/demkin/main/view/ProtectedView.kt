package ru.demkin.view

import com.demkin.core.model.Session
import com.demkin.core.services.ScrobbleService
import com.demkin.core.services.User
import com.demkin.main.view.OraclePlayerView
import com.demkin.main.view.PlayerPane
import javafx.beans.binding.DoubleBinding
import javafx.beans.binding.StringBinding
import javafx.beans.property.SimpleStringProperty
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.control.Button
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.util.StringConverter
import ru.demkin.app.Styles
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
  override val root = Form().addClass(Styles.login)
  val artist = SimpleStringProperty()
  val song = SimpleStringProperty()
  val SHEEP_URL = "http://cs521313.vk.me//u10484188//audios//3af33d453269.mp3?extra=M7Cn4q2HaROnlE6BkezJ7zdHq1Q9H6v01pdVeTwOVs5zfblpD70s71hgMMsRn8zY_Rv6IXhjWqr7GFFBkQplM4rTmD5pCUUdT-Mebpv8PVl3Yz8TaSYnecqysmag9bdhogh2bY1kPCSF"

  val selectedSong = SimpleStringProperty()
  val userService = User()

  init {
    title = "Send Artist/Song information on server"
    with(root) {
      fieldset {
        labelPosition = Orientation.VERTICAL

        field("Artist name") {
          textfield().bind(artist)
        }
        field("Vk username") {
          textfield().bind(song)
        }
      }
      button("Scrobble it!") {
        setOnAction {
          send()
        }
      }
      add(OraclePlayerView(MediaPlayer(Media(SHEEP_URL))))

      combobox<String>(property = selectedSong) {
        userService.getLovedTracks("Wi-Al").lovedtracks?.track?.forEach {
          items.add(it.artist?.name+" - "+it.name)
        }
        valueProperty().addListener { observableValue, s1, s2->
          println(s1+" "+s2)
          println(observableValue)
        }

      }

      label("test").bind(selectedSong)
    }
  }

  private fun Button.send() {
    val service = ScrobbleService(session)
    service.scrobble(artist.value, song.value)
  }
}
