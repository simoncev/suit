/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit.layouts

import org.suit._

/**
 * @author Steven Dobay
 */
case class CardLayout() extends Layout {
  private val layout = new java.awt.CardLayout()

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
