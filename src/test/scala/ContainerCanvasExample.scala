/**
 * Copyright© Steven Dobay 2015
 */
package scala.test

import java.awt.Color
import java.net.URL

import org.suit._
import org.suit.graphics._

/**
 * @author Steven Dobay
 */
object ContainerCanvasExample extends DesktopApp("Example for using containers' canvas.") {
  // We hide the frame to let the panel draw and show it after.
  frame.hide()

  frame.size = Size(470, 470)

  val panel = new Panel()
  panel.preferredSize = Size(420, 420)

  panel.canvas.color(Color.GREEN)
  panel.canvas += RoundRectangle(Point(5, 5), Size(415, 415), Size(10, 10), false)

  val pic = Picture(URLResource(
    new URL("http://lolspoon.com/wp-content/" +
      "uploads/2012/09/" +
      "Cute-Kitten-Pictures-and-LOLs-2-400x400.jpg")
    )
  )
  pic.startPoint = Point(33, 33)
  pic.size(Size(360, 360))
  panel.canvas += pic

  panel.canvas.color(Color.BLUE)

  def addCircle(start: Point) =
    panel.canvas += Circle(start, Size(40, 40), 0, 360, true)

  addCircle(Point(18, 18))
  addCircle(Point(18, 370))
  addCircle(Point(370, 18))
  addCircle(Point(370, 370))

  frame += panel
  frame.show()
}
