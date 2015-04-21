/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.media

import org.suit.media.AudioPlayer
import org.suit.{popups, FileChooser, Size}

/**
 * @author Steven Dobay
 */
object AudioPlayerExample extends org.suit.DesktopApp("Example for audio player") {
  frame.size = Size(100, 100)

  // creating the player
  val player = AudioPlayer()

  // we use a chooser to choose an audio file
  val chooser = new FileChooser()

  // when we chosen we creates the video component and starts it
  if(chooser.run(frame, "Choose an audio(or a video)!")) {
    player.urlLocation = chooser.selectedFile().toURI.toURL()
    player.play()
  } else popups.warning("No file was selected!", "Choose a file to play it!")

}
