/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import org.suit._

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
