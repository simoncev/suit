/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.{Property, Size}

/**
 * @author Steven Dobay
 *
 * Dispatcher for circles.
 */
case class Circle_(private var initStartPoint: Point = Point(0, 0)) {
  private var initSize: Size = Size(0, 0)
  private var initStartAngle: Int = 0
  private var initArcAngle = 360
  private var isFilled = false

  val startPoint = Property[Point](initStartPoint = _)

  val size = Property[Size](initSize = _)

  val arcAngle = Property[Int](initArcAngle = _)

  val startAngle = Property[Int](initStartAngle = _)

  val filled = Property[Boolean](isFilled = _)

  /**
   * @return with the packed circle
   */
  def pack() = new Circle(initStartPoint, initSize,
                          initStartAngle, initArcAngle, isFilled)

}
