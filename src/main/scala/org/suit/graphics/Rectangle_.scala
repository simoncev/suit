/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import org.suit.{Property, Size}

/**
 * @author Steven Dobay
 *
 * Dispatcher for Rectangles.
 */
abstract class Rectangle_(protected var initPoint: Point) {
  protected var initSize = Size(0, 0)
  protected var isFilled = false

  val size = Property[Size](initSize = _)
  val startPoint = Property[Point](initPoint = _)
  val filled = Property[Boolean](isFilled = _)

  /**
   * @return with the initialized rectangle.
   */
  def pack(): Rectangle
}
