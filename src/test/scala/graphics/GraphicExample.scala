/**
 * Copyright© Steven Dobay 2015
 */
package scala.test.graphics

import java.awt.Color
import java.net.URL

import org.suit._
import org.suit.graphics._

/**
 * @author Steven Dobay
 */
object GraphicExample extends DesktopApp("Basic graphical stuff") {
  frame.layout = layouts.GridLayout(1, 1)
  frame.size = Size(450, 450)

  val canvas = new Canvas()
  canvas.preferredSize = Size(420, 420)

  canvas.color(Color.GREEN)
  canvas += RoundRectangle(Point(5, 5), Size(415, 415), Size(10, 10), false)

  val pic = Picture(URLResource(
                      new URL("http://lolspoon.com/wp-content/" +
                             "uploads/2012/09/" +
                             "Cute-Kitten-Pictures-and-LOLs-2-400x400.jpg")
                    )
                   )
  pic.startPoint = Point(33, 33)
  pic.size(Size(360, 360))
  canvas += pic

  canvas.color(Color.BLUE)

  def addCircle(start: Point) =
    canvas += Circle(start, Size(40, 40), 0, 360, true)

  addCircle(Point(18, 18))
  addCircle(Point(18, 370))
  addCircle(Point(370, 18))
  addCircle(Point(370, 370))

  frame += canvas.doDrawing()

  frame.updateUI()
}
