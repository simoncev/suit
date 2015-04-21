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
 *
 * A graphical example(same as GraphicalExample.scala)
 * using dispatching of the shapes.
 */
object GraphicalExampleWithDispatching extends DesktopApp("Basic graphical stuff") {
  frame.layout = layouts.GridLayout(1, 1)
  frame.size = Size(450, 450)

  val canvas = new Canvas()
  canvas.preferredSize = Size(420, 420)

  canvas.color(Color.GREEN)
  canvas += new RoundRectangle_() {
   startPoint := Point(5, 5)
   size := Size(415, 415)
   arcSize := Size(10, 10)
  }

  val url = new URL("http://lolspoon.com/wp-content/" +
                    "uploads/2012/09/" +
                    "Cute-Kitten-Pictures-and-LOLs-2-400x400.jpg")
  val pic = new Picture_(URLResource(url)) {
    startPoint := Point(33, 33)
    size := Size(360, 360)
  }
  canvas += pic

  canvas.color(Color.BLUE)

  def addCircle(start: Point) =
    canvas += new Circle_ {
      startPoint := start
      size := Size(40, 40)
      startAngle := 0
      arcAngle := 360
      filled := true
    }

  addCircle(Point(18, 18))
  addCircle(Point(18, 370))
  addCircle(Point(370, 18))
  addCircle(Point(370, 370))

  frame += canvas.doDrawing()
}
