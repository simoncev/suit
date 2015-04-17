/**
 * Copyright© Steven Dobay 2015
 */
package suit.graphics

import suit.Size

/**
 * @author Steven Dobay
 */
case class Rectangle2D(
  private val cStart: Point,
  private val cSize: Size,
  private val cFilled: Boolean = false)
  extends Rectangle(cStart, cSize, cFilled)
