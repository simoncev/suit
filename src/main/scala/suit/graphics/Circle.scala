/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit._

/**
 * @author Steven Dobay
 */
case class Circle(start: Point, dim: Size,
                  startAngle: Int, arcAngle: Int,
                  filled: Boolean) {
  def x() = start.x
  def y() = start.y

  def width() = dim.width()
  def height() = dim.height()
}
