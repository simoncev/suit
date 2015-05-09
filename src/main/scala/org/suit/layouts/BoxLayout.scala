/**
 * Copyright© Steven Dobay 2015
 */
package org.suit.layouts

import javax.swing.{ BoxLayout => JBoxLayout }

/**
 * @author Steven Dobay
 *
 * @version 1.0
 * @param axis
 * @param C : implicit container
 */
case class BoxLayout(axis: Int)(implicit C: org.suit.Container)
   extends org.suit.Layout {
  private val layout =
    new JBoxLayout(C.wrappedContainer.asInstanceOf[java.awt.Container],
                   axis)
  /**
   * @return with the wrapped layout.
   */
  protected[suit] def wrapped = layout
}
