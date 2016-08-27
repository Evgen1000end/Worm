package ru.demkin.view

import com.demkin.core.model.Session
import com.demkin.core.services.ScrobbleService
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.Button
import ru.demkin.app.Styles
import tornadofx.*

/**
 * Description of ru.demkin.view
 * @author evgen1000end
 * @since 07.08.2016
 */
class ProtectedView(val session: Session) : View() {
  override val root = Form().addClass(Styles.login)

  val artist = SimpleStringProperty()
  val song = SimpleStringProperty()


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
    }
  }

  private fun Button.send() {
    val service = ScrobbleService(session)
    service.scrobble(artist.value, song.value)
  }
}
