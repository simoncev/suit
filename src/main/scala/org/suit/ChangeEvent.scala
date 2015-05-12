/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
class ChangeEvent(private val cSource: Component,
                  private val cId: Option[Int],
                  private val cWhen: Long,
                  val changeCount: Option[Long])
 extends Event(cSource, cId, cWhen)

object ChangeEvent {
  def apply(s: Component, i: Option[Int], w: Long, count: Option[Long]) =
   new ChangeEvent(s, i, w, count)

  def apply(e: java.awt.event.ActionEvent) =
   new ChangeEvent(e.getSource.asInstanceOf[JComponent]
                    .getClientProperty("suit-wrapper")
                    .asInstanceOf[Component],
                   Some(e.getID), e.getWhen, None)

  def apply(e: javax.swing.event.ChangeEvent) =
    new ChangeEvent(e.getSource.asInstanceOf[JComponent]
                     .getClientProperty("suit-wrapper")
                     .asInstanceOf[Component],
                     None, -1L, None)
}