/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.media

import suit.{App, Size, layouts, FileChooser, popups}
import suit.media._

/**
 * @author Steven Dobay
 *
 * Simple video playing frame.
 */
object VideoPlayerExample extends App("Example for using VideoPlayer") {

  frame.size = Size(400, 300)
  frame.layout = layouts.GridLayout(1, 1)

  // adding the component
  val player = VideoPlayer()
  player.preferredSize = Size(400, 300)
  frame += player

  // we use a chooser to choose a video file
  val chooser = new FileChooser()

  // when we chosen we creates the video component and starts it
  if(chooser.runOpen(frame)) {
    player.urlLocation(chooser.selectedFile().toURI.toURL())
    player.play()
  } else popups.warning("No file was selected!", "Choose a file to play it!")

}
