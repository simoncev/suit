/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit

import javax.swing.JComponent
import helpers.ComponentHelpers._

/**
 * @author Steven Dobay
 */
class MouseEvent(private val cSource: Component,
                 private val cId: Option[Int],
                 private val cWhen: Long,
                 val x: Int, val y: Int,
                 val clickCount: Int,
                 val isPopupTrigger: Boolean)
  extends Event(cSource, cId, cWhen) {
  /**
   * @return with a new actionevent
   */
  def toActionEvent = new ActionEvent(source, id, when)
}

object MouseEvent {

  /**
   * Simple static factory - introduced because we can't
   * extend a case class(look after MouseMotionEvent).
   *
   * @param s
   * @param i
   * @param w
   * @param x
   * @param y
   * @param c
   * @param p
   * @return
   */
  def apply(s: Component, i: Int, w: Long,
            x: Int, y: Int, c: Int, p: Boolean) =
   new MouseEvent(s, Some(i), w, x, y, c, p)

  /**
   * @param e
   * @return with a new MouseEvent created from a java.awt.event.MouseEvent
   */
  def apply(e: java.awt.event.MouseEvent) =
   new MouseEvent(e.getSource.asInstanceOf[JComponent].readSuitComponent,
                  Some(e.getID), e.getWhen,
                  e.getX, e.getY, e.getClickCount, e.isPopupTrigger)
}
