/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit

import java.awt.event.{ActionEvent => JActionEvent}
import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
case class ActionEvent(private val cSource: Component,
                       private val cId: Option[Int],
                       private val cWhen: Long)
 extends Event(cSource, cId, cWhen) {

  def toMouseEvent =
    new MouseEvent(source, id, when, -1, -1, 1, false)

  def toAWTActionEvent: JActionEvent =
    new JActionEvent(source.wrapped,
                     if(cId.isDefined) cId.get else -1,
                     "", when, -1)
}

object ActionEvent {
  def apply(e: java.awt.event.ActionEvent) = {
    val comp = e.getSource.asInstanceOf[JComponent]
                .getClientProperty("suit-wrapper")
                .asInstanceOf[Component]
    new ActionEvent(comp, Some(e.getID), e.getWhen)
  }
}
