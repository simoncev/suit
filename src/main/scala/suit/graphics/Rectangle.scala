/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit._

/**
 * @author Steven Dobay
 */
abstract class Rectangle(val start: Point,
                         val dim: Size,
                         val filled: Boolean = false) {

  def x() = start.x
  def y() = start.y

  def width() = dim.width()
  def height() = dim.height()
}