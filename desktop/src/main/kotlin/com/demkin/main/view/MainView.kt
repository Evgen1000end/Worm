package ru.demkin.view

import com.demkin.core.model.Authenticator
import com.demkin.core.model.Session
import javafx.beans.property.SimpleBooleanProperty
import javafx.geometry.Orientation
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
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
  val DEFAULT_SECTION = "Credential"

  fun loadIni(){
    val ini = Wini(File(DEFAULT_INI_PATH))
    model.lastFmLogin.value = ini.get(DEFAULT_SECTION, "lu")
    model.lastFmPassword.value = ini.get(DEFAULT_SECTION, "lp")
    model.vkLogin.value = ini.get(DEFAULT_SECTION, "vu")
    model.vkPassword.value = ini.get(DEFAULT_SECTION, "vp")
  }

  fun saveIni(){
    val ini = Wini(File(DEFAULT_INI_PATH))

    ini.add(DEFAULT_SECTION)

    if (!ini.containsKey("lu"))
      ini.put(DEFAULT_SECTION,"lu",model.lastFmLogin.value)
    if (!ini.containsKey("lp"))
      ini.put(DEFAULT_SECTION,"lp",model.lastFmPassword.value)
    if (!ini.containsKey("vu"))
      ini.put(DEFAULT_SECTION,"vu",model.vkLogin.value)
    if (!ini.containsKey("vp"))
      ini.put(DEFAULT_SECTION,"vp",model.vkPassword.value)
    ini.store()
  }

  init {
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
    }
  }

  private fun loginLastFm(): Boolean {
    try {
      session = Authenticator().fetchSession(model.credentials.lastFmLogin.value, model.credentials.lastFmPassword.value)
      return session != null
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