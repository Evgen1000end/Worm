package ru.demkin.models

import javafx.beans.property.SimpleStringProperty
import tornadofx.ViewModel

/**
 * Description of ru.demkin.models
 * @author evgen1000end
 * @since 07.08.2016
 */
class Credentials {
  val lastFmLogin = SimpleStringProperty()
  val lastFmPassword = SimpleStringProperty()
  val vkLogin = SimpleStringProperty()
  val vkPassword = SimpleStringProperty()
}

class CredentialModel(var credentials: Credentials) : ViewModel() {
  val lastFmLogin = bind { credentials.lastFmLogin }
  val lastFmPassword = bind { credentials.lastFmPassword }
  val vkLogin = bind { credentials.vkLogin }
  val vkPassword = bind { credentials.vkPassword }
}