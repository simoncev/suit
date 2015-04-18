/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.Property

/**
 * @author Steven Dobay
 *
 * Dispatcher for Rectangle3Ds.
 */
case class Rectangle3D_(private var initStartPoint: Point = Point(0, 0))
  extends Rectangle_(initStartPoint) {
  private var isRaised = false

  val raised = Property[Boolean](isRaised = _)

  /**
   * @return with the initialized rectangle.
   */
  def pack(): Rectangle =
    new Rectangle3D(initPoint, initSize, isFilled, isRaised)
}
