/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.Size

/**
 * @author Steven Dobay
 */
case class RoundRectangle(
  private val cStart: Point,
  private val cSize: Size,
  private val cFilled: Boolean = false,
  arcWidth: Int, arcHeight: Int)
  extends Rectangle(cStart, cSize, cFilled)