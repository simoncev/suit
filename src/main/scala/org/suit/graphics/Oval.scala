/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

/**
 * @author Steven Dobay
 */
case class Oval(rec: Rectangle) {
  def x() = rec.x()
  def y() = rec.y()

  def width() = rec.width()
  def height() = rec.height()

  def filled() = rec.filled
}
