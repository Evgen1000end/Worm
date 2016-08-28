package ru.demkin.view

import com.demkin.core.model.Authenticator
import com.demkin.core.model.Session
import javafx.beans.property.SimpleBooleanProperty
import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Orientation
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.stage.Modality
import org.ini4j.Wini
import ru.demkin.app.Styles
import ru.demkin.models.CredentialModel
import ru.demkin.models.Credentials
import tornadofx.*
import java.io.File

class MainView : View() {
  override val root = Form().addClass(Styles.login)
  val model = CredentialModel(Credentials())
  var session: Session = Session()
  val saveCredential = SimpleBooleanProperty()
  val DEFAULT_INI_PATH = "desktop\\build\\libs\\worm.ini"
  val SECTION_CREDENTIALS = "Credential"
  val SECTION_SESSION = "Session"
  val ini = Wini(File(DEFAULT_INI_PATH))
  val SHEEP_URL = "http://cs521313.vk.me//u10484188//audios//3af33d453269.mp3?extra=M7Cn4q2HaROnlE6BkezJ7zdHq1Q9H6v01pdVeTwOVs5zfblpD70s71hgMMsRn8zY_Rv6IXhjWqr7GFFBkQplM4rTmD5pCUUdT-Mebpv8PVl3Yz8TaSYnecqysmag9bdhogh2bY1kPCSF"
  val ORACLE_URL= "http://download.oracle.com/otndocs/products/javafx/oow2010-2.flv"

  fun loadIni(){
    model.lastFmLogin.value = ini.get(SECTION_CREDENTIALS, "lu")
    model.lastFmPassword.value = ini.get(SECTION_CREDENTIALS, "lp")
    model.vkLogin.value = ini.get(SECTION_CREDENTIALS, "vu")
    model.vkPassword.value = ini.get(SECTION_CREDENTIALS, "vp")
    session.key = ini.get(SECTION_SESSION, "key")
  }

  fun saveIni(){

    ini.add(SECTION_CREDENTIALS)

    if (!ini.containsKey("lu"))
      ini.put(SECTION_CREDENTIALS,"lu",model.lastFmLogin.value)
    if (!ini.containsKey("lp"))
      ini.put(SECTION_CREDENTIALS,"lp",model.lastFmPassword.value)
    if (!ini.containsKey("vu"))
      ini.put(SECTION_CREDENTIALS,"vu",model.vkLogin.value)
    if (!ini.containsKey("vp"))
      ini.put(SECTION_CREDENTIALS,"vp",model.vkPassword.value)
    ini.store()
  }

  init {
    val media = Media(SHEEP_URL)
    val mediaPlayer = MediaPlayer(media)
    mediaPlayer.setAutoPlay(true)

    loadIni()

    title = "Last FM and VK Scrobbler"
    with(root) {
      fieldset {
        labelPosition = Orientation.VERTICAL

        field("Last Fm username") {
          textfield(model.lastFmLogin).required(message = "Insert your username")
        }
        field("Last Fm password") {
          passwordfield(model.lastFmPassword).required(message = "Insert your password")
        }
        field("Vk username") {
          textfield(model.vkLogin).required(message = "Insert your username")
        }
        field("Vk password") {
          passwordfield(model.vkPassword).required(message = "Insert your password")
        }
      }
      checkbox("Save credentials") {
        bind(saveCredential)
        setOnAction {
          if (saveCredential.value)
            saveIni()
        }
      }

      button("Log in") {
        setOnAction {
          login()
        }
      }

      button("Play") {
        setOnAction(EventHandler<javafx.event.ActionEvent> {
          val status = mediaPlayer.getStatus()
          if (status === MediaPlayer.Status.UNKNOWN || status === MediaPlayer.Status.HALTED) {
            return@EventHandler
          }

          if (status === MediaPlayer.Status.PAUSED
                  || status === MediaPlayer.Status.READY
                  || status === MediaPlayer.Status.STOPPED) {
            // rewind the movie if we're sitting at the end
//              if (atEndOfMedia) {
//                mp.seek(mp.getStartTime())
//                atEndOfMedia = false
//              }
            mediaPlayer.play()
          } else {
            mediaPlayer.pause()
          }
        })
      }

      val mediaView = MediaView()
      mediaView.mediaPlayer = mediaPlayer
      //mediaView.prefHeight(100.0)

      children.add(mediaView)
    }

  }

  private fun loginLastFm(): Boolean {
    try {
      if (session.key!=null){
        return  true
      } else {
        session = Authenticator().fetchSession(model.credentials.lastFmLogin.value, model.credentials.lastFmPassword.value)
        ini.add(SECTION_SESSION)
        if (!ini.containsKey("key"))
          ini.put(SECTION_SESSION,"key",session.key)
        ini.store()
        return session != null
      }
    } catch (e: Exception) {
      return false
    }
  }

  private fun Button.login() {
    if (model.commit()) {
      graphic = ProgressIndicator()
      runAsync {
        loginLastFm()
      } ui { success ->
        graphic = null
        if (success) {
          val v = ProtectedView(session)
          v.openModal(modality = Modality.NONE)
          // replaceWith(ProtectedView::class, ViewTransition.SlideIn)
        } else
          alert(Alert.AlertType.WARNING, "Login failed", "Check your credentials")
      }
    }
  }
}