package com.demkin.main.view

import javafx.beans.value.ChangeListener
import javafx.beans.value.ObservableValue
import javafx.collections.FXCollections
import javafx.collections.MapChangeListener
import javafx.collections.ObservableList
import javafx.collections.ObservableMap
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.control.*
import javafx.scene.control.cell.MapValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.HBox
import javafx.scene.layout.Priority
import javafx.scene.layout.StackPane
import javafx.scene.layout.VBox
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.media.MediaView
import javafx.util.Callback
import javafx.util.Duration
import java.util.*

/**
 * Created by demkinev on 28.08.2016.
 */
class PlayerPane : StackPane() {

  val TAG_COLUMN_NAME = "Tag"
  val VALUE_COLUMN_NAME = "Value"
  internal val currentlyPlaying = Label()
  internal val progress = ProgressBar()
  internal val metadataTable = TableView<Map<*, *>>()
  private var progressChangeListener: ChangeListener<Duration>? = null
  private var metadataChangeListener: MapChangeListener<String, Any>? = null
  val FILE_EXTENSION_LEN = 3
  val SHEEP_URL = "http://cs521313.vk.me//u10484188//audios//3af33d453269.mp3?extra=M7Cn4q2HaROnlE6BkezJ7zdHq1Q9H6v01pdVeTwOVs5zfblpD70s71hgMMsRn8zY_Rv6IXhjWqr7GFFBkQplM4rTmD5pCUUdT-Mebpv8PVl3Yz8TaSYnecqysmag9bdhogh2bY1kPCSF"


  init {
    val players = ArrayList<MediaPlayer>()
    players.add(createPlayer(SHEEP_URL))


    val mediaView = MediaView(players[0])
    val skip = Button("Skip")
    val play = Button("Pause")

    for (i in players.indices) {
      val player = players[i]
      val nextPlayer = players[(i + 1) % players.size]
      player.setOnEndOfMedia {
        player.currentTimeProperty().removeListener(progressChangeListener)
        player.media.metadata.removeListener(metadataChangeListener)
        player.stop()
        mediaView.mediaPlayer = nextPlayer
        nextPlayer.play()
      }
    }

    skip.setOnAction { actionEvent ->
      val curPlayer = mediaView.mediaPlayer
      curPlayer.currentTimeProperty().removeListener(progressChangeListener)
      curPlayer.media.metadata.removeListener(metadataChangeListener)
      curPlayer.stop()

      val nextPlayer = players[(players.indexOf(curPlayer) + 1) % players.size]
      mediaView.mediaPlayer = nextPlayer
      nextPlayer.play()
    }

    play.setOnAction { actionEvent ->
      if ("Pause" == play.text) {
        mediaView.mediaPlayer.pause()
        play.text = "Play"
      } else {
        mediaView.mediaPlayer.play()
        play.text = "Pause"
      }
    }

    mediaView.mediaPlayerProperty().addListener { observableValue, oldPlayer, newPlayer -> setCurrentlyPlaying(newPlayer) }

    val invisiblePause = Button("Pause")
    invisiblePause.isVisible = false
    play.prefHeightProperty().bind(invisiblePause.heightProperty())
    play.prefWidthProperty().bind(invisiblePause.widthProperty())

    // add a metadataTable for meta data display
    metadataTable.style = "-fx-font-size: 13px;"

    val tagColumn = TableColumn<Map<*, *>, String>(TAG_COLUMN_NAME)
    tagColumn.prefWidth = 150.0
    val valueColumn = TableColumn<Map<*, *>, Any>(VALUE_COLUMN_NAME)
    valueColumn.prefWidth = 400.0

    tagColumn.cellValueFactory = MapValueFactory<String>(TAG_COLUMN_NAME)
    valueColumn.cellValueFactory = MapValueFactory<Any>(VALUE_COLUMN_NAME)

    metadataTable.isEditable = true
    metadataTable.selectionModel.isCellSelectionEnabled = true
    metadataTable.columns.setAll(tagColumn, valueColumn)

    valueColumn.cellFactory = Callback<TableColumn<Map<*, *>, Any>, TableCell<Map<*, *>, Any>>() {
      object : TableCell<Map<*, *>, Any>() {
        override fun updateItem(item: Any?, empty: Boolean) {
          super.updateItem(item, empty)

          if (item != null) {
            if (item is String) {
              text = item as String?
              graphic = null
            } else if (item is Int) {
              text = Integer.toString((item as Int?)!!)
              graphic = null
            } else if (item is Image) {
              text = null
              val imageView = ImageView(item as Image?)
              imageView.fitWidth = 200.0
              imageView.isPreserveRatio = true
              graphic = imageView
            } else {
              text = "N/A"
              graphic = null
            }
          } else {
            text = null
            graphic = null
          }
        }
      }
    }


    val progressReport = HBox(10.0)
    progressReport.alignment = Pos.CENTER
    progressReport.children.setAll(skip, play, mediaView)

    val content = VBox(10.0)
    content.children.setAll(
            currentlyPlaying,
            progressReport,
            progress)

    children.addAll(
            invisiblePause,
            content)
    progress.maxWidth = java.lang.Double.MAX_VALUE
    HBox.setHgrow(progress, Priority.ALWAYS)
    VBox.setVgrow(metadataTable, Priority.ALWAYS)
  }

  private fun setCurrentlyPlaying(newPlayer: MediaPlayer) {
    newPlayer.seek(Duration.ZERO)

    progress.progress = 0.0

    // progressChangeListener = { observableValue, oldValue, newValue -> progress.progress = 1.0 * newPlayer.currentTime.toMillis() / newPlayer.totalDuration.toMillis() }

    progressChangeListener = ChangeListener<javafx.util.Duration>
    { observableValue, oldValue, newValue -> progress.progress = 1.0 * newPlayer.currentTime.toMillis() / newPlayer.totalDuration.toMillis() }

    newPlayer.currentTimeProperty().addListener(progressChangeListener)

    var source = newPlayer.media.source
    source = source.substring(0, source.length - FILE_EXTENSION_LEN)
    source = source.substring(source.lastIndexOf("/") + 1).replace("%20".toRegex(), " ")
    currentlyPlaying.text = "Now Playing: " + source

    setMetaDataDisplay(newPlayer.media.metadata)
  }


  private fun convertMetadataToTableData(metadata: ObservableMap<String, Any>): ObservableList<Map<*, *>> {
    val allData = FXCollections.observableArrayList<Map<*, *>>()
    for (key in metadata.keys) {
      val dataRow = HashMap<String, Any>()
      dataRow.put(TAG_COLUMN_NAME, key)
      dataRow.put(VALUE_COLUMN_NAME, metadata[key] ?: Any())
      allData.add(dataRow)
    }
    return allData
  }

  private fun setMetaDataDisplay(metadata: ObservableMap<String, Any>) {
    metadataTable.items.setAll(convertMetadataToTableData(metadata))
    metadataChangeListener =
            MapChangeListener<kotlin.String, kotlin.Any> {
              metadataTable.items.setAll(convertMetadataToTableData(metadata))
            }
    metadata.addListener(metadataChangeListener)
  }

  private fun createPlayer(mediaSource: String): MediaPlayer {
    val media = Media(mediaSource)
    val player = MediaPlayer(media)
    player.setOnError { println("Media error occurred: " + player.error) }
    return player
  }
}