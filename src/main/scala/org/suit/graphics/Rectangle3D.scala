/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.graphics

import org.suit.Size

/**
 * @author Steven Dobay
 */
case class Rectangle3D(
  private val cStart: Point,
  private val cSize: Size,
  private val cFilled: Boolean = false,
  raised: Boolean)
 extends Rectangle(cStart, cSize, cFilled)
