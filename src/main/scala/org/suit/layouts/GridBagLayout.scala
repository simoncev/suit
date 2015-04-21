/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package org.suit.layouts

import org.suit._

/**
 * @author Steven Dobay
 */
case class GridBagLayout() extends Layout {
  private val layout = new java.awt.GridBagLayout()

  /**
   * @return with a pointer to the wrapped layout manager
   */
  protected[suit] def wrapped = layout
}
