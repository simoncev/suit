/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.Size

/**
 * @author Steven Dobay
 */
case class RoundRectangle(
  private val startPoint: Point,
  private val initSize: Size,
  private val arcSize: Size,
  private val initFilled: Boolean = false)
  extends Rectangle(startPoint, initSize, initFilled)