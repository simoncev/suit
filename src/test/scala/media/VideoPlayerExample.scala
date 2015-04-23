/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.media

import java.awt.GridBagConstraints

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
    newItem := TextMenuItem("Open video") @> (_ =>
                 if(chooser.runOpen(frame))
                 player.urlLocation =
                 chooser.selectedFile().toURI.toURL()
               )
  }.pack()

  player.preferredSize = Size(1260, 670)
  frame.add(player, BorderLayoutPosition.CENTER)

  val controller = new MediaController(player)
  frame.add(controller, BorderLayoutPosition.PAGE_END)

  frame.updateUI()
}