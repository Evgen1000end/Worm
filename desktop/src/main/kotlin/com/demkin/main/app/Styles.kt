package ru.demkin.app

import javafx.scene.text.FontPosture
import javafx.scene.text.FontWeight
import tornadofx.Stylesheet
import tornadofx.box
import tornadofx.cssclass
import tornadofx.px

class Styles : Stylesheet() {
  companion object {
    val login by cssclass()
    val loginWidht = 600.px
    val logi by cssclass()
  }

  init {
    form and login {
      padding = box(25.px)
      spacing = 10.px
      fontSize = 20.px
      fontWeight = FontWeight.BOLD
      prefWidth = loginWidht
      button {
        prefWidth = loginWidht
      }

      progressIndicator {
        prefWidth = 16.px
        prefHeight = prefWidth
      }
    }

    logi {
      fontStyle = FontPosture.ITALIC
      spacing = 20.px
    }
  }
}


class StylesPlayer : Stylesheet() {
  companion object {
    val login by cssclass()
    val loginWidht = 1600.px
    val loginHeight = 1000.px
  }

  init {
    login {
      padding = box(25.px)
      spacing = 10.px
      fontSize = 20.px
      fontWeight = FontWeight.BOLD
      prefWidth = loginWidht
      prefHeight = loginHeight

      progressIndicator {
        prefWidth = 16.px
        prefHeight = prefWidth
      }
    }
  }
}