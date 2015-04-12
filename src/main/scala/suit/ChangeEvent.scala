/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
class ChangeEvent(private val cSource: Component,
                  private val cId: Int,
                  private val cWhen: Long,
                  val changeCount: Long)
 extends Event(cSource, cId, cWhen)

object ChangeEvent {
  def apply(s: Component, i: Int, w: Long, count: Long) =
   new ChangeEvent(s, i, w, count)

  def apply(e: java.awt.event.ActionEvent) =
   new ChangeEvent(e.getSource.asInstanceOf[JComponent]
                    .getClientProperty("scala-frame-wrapper")
                    .asInstanceOf[Component],
                   e.getID, e.getWhen, 1)

  def apply(e: javax.swing.event.ChangeEvent) =
    new ChangeEvent(e.getSource.asInstanceOf[JComponent]
                     .getClientProperty("scala-frame-wrapper")
                     .asInstanceOf[Component],
                     -1, -1L, -1)
}