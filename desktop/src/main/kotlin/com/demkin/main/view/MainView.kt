package ru.demkin.view

import com.demkin.core.model.Authenticator
import javafx.geometry.Orientation
import javafx.scene.control.Alert
import javafx.scene.control.Button
import javafx.scene.control.ProgressIndicator
import ru.demkin.app.Styles
import ru.demkin.models.CredentialModel
import ru.demkin.models.Credentials
import tornadofx.*

class MainView : View() {
  override val root = Form().addClass(Styles.login)
  val model = CredentialModel(Credentials())

  init {
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
      button("Log in") {
        setOnAction {
          login()
        }
      }
    }
  }

  private fun loginLastFm(): Boolean {
    try {
      val session = Authenticator().fetchSession(model.credentials.lastFmLogin.value, model.credentials.lastFmPassword.value)
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
        if (success)
          replaceWith(ProtectedView::class, ViewTransition.SlideIn)
        else
          alert(Alert.AlertType.WARNING, "Login failed", "Check your credentials")
      }
    }
  }
}