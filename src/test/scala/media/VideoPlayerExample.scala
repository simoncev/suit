/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.media

import java.net.URL
import org.suit._
import org.suit.layouts.BorderLayoutPosition
import org.suit.media._

/**
 * @author Steven Dobay
 *
 * Simple video playing frame.
 */
object VideoPlayerExample extends DesktopApp("Example for using VideoPlayer") {

  frame.size = Size(800, 600)
  frame.layout = layouts.BorderLayout()

  // we use a chooser to choose a video file
  val chooser = new FileChooser()

  // adding the component
  val player = VideoPlayer()
  player.popupMenu = new PopupMenu_() {
    newItem := TextMenuItem("Open video from file") @> (_ =>
                 if(chooser.runOpen(frame))
                   player.urlLocation =
                    chooser.selectedFile().toURI.toURL()
               )
    newItem := TextMenuItem("Open video from URL") @> ( e =>
                 player.urlLocation =
                   new URL(popups.input("", "Write here the video's URL: "))
               )
  }.pack()

  player.preferredSize = Size(1260, 670)
  frame.add(player, BorderLayoutPosition.CENTER)

  val controller = new MediaController(player)
  frame.add(controller, BorderLayoutPosition.PAGE_END)

  frame.updateUI()
}