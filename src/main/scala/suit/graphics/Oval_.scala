/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.{Property, Size}

/**
 * @author Steven Dobay
 *
 * Dispatcher for ovals.
 */
case class Oval_(private var initPoint: Point = Point(0, 0)) {
  private var initSize = Size(0, 0)
  private var isFilled = false

  val size = Property[Size](initSize = _)
  val startPoint = Property[Point](initPoint = _)
  val filled = Property[Boolean](isFilled = _)

  /**
   * @return with the packed oval
   */
  def pack() = new Oval(Rectangle2D(initPoint, initSize, isFilled))
}
