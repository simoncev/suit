/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import org.suit.Size

/**
 * @author Steven Dobay
 */
case class RoundRectangle(
  private val startPoint: Point,
  private val initSize: Size,
  private val arcSize: Size,
  private val initFilled: Boolean = false)
  extends Rectangle(startPoint, initSize, initFilled)