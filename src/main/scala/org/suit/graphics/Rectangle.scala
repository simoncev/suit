/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import org.suit._

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