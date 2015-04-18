/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

/**
 * @author Steven Dobay
 *
 * Dispatcher for Rectangle2Ds.
 */
case class Rectangle2D_(private var initStartPoint: Point = Point(0, 0))
  extends Rectangle_(initStartPoint) {

  /**
   * @return with the initialized rectangle.
   */
  def pack(): Rectangle = new Rectangle2D(initPoint, initSize, isFilled)
}
