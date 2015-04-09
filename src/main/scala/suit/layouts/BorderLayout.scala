/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit.layouts

import suit._

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
  def wrapped = layout
}
