/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit.layouts

import org.suit._

/**
 * @author Steven Dobay
 */
case class BorderLayout() extends Layout {
  private val layout = new java.awt.BorderLayout()

  /**
   * @return with the size of the horizontal gap
   */
  def hGap = layout.getHgap

  /**
   * @return with the size of the vertical gap
   */
  def vGap = layout.getVgap

  /**
   * Sets the horizontal gap of the layout
   * @param h
   */
  def hGap_=(h: Int) = layout.setHgap(h)

  /**
   * Sets the vertical gap of the layout
   * @param v
   */
  def vGap_=(v: Int) = layout.setVgap(v)

  /**
   * @return with a pointer to the wrapped layout manager
   */
  protected[suit] def wrapped = layout
}

object BorderLayout {
  import java.awt.{ BorderLayout => JBL }
  val AFTER_LAST_LINE = JBL.AFTER_LAST_LINE
  val AFTER_LINE_ENDS = JBL.AFTER_LINE_ENDS
  val BEFORE_FIRST_LINE = JBL.BEFORE_FIRST_LINE
  val BEFORE_LINE_BEGINS = JBL.BEFORE_LINE_BEGINS
  val CENTER = JBL.CENTER
  val EAST = JBL.EAST
  val LINE_END = JBL.LINE_END
  val LINES_TART = JBL.LINE_START
  val NORTH = JBL.NORTH
  val PAGE_END = JBL.PAGE_END
  val PAGE_START = JBL.PAGE_START
  val SOUTH = JBL.SOUTH
  val WEST = JBL.WEST
}