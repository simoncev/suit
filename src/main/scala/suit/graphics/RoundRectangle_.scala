/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.{Property, Size}

/**
 * @author Steven Dobay
 *
 * Dispatcher for RoundRectangles.
 */
case class RoundRectangle_(private var initStartPoint: Point = Point(0, 0))
  extends Rectangle_(initStartPoint) {
   protected var initArc = Size(0, 0)

   val arcSize = Property[Size](initArc = _)

  /**
   * @return with the initialized round rectangle.
   */
  def pack(): Rectangle =
    new RoundRectangle(initPoint, initSize, initArc, isFilled)
}
