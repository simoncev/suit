/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit

import java.awt.event.{ActionEvent => JActionEvent}
import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
case class ActionEvent(private val cSource: Component,
                       private val cId: Int,
                       private val cWhen: Long)
 extends Event(cSource, cId, cWhen) {

  def toMouseEvent =
    new MouseEvent(source, id, when, -1, -1, 1, false)

  def toAWTActionEvent: JActionEvent =
    new JActionEvent(source.wrapped, id, "", when, -1)
}

object ActionEvent {
  def apply(e: java.awt.event.ActionEvent) = {
    val comp = e.getSource.asInstanceOf[JComponent]
                .getClientProperty("scala-frame-wrapper")
                .asInstanceOf[Component]
    new ActionEvent(comp, e.getID, e.getWhen)
  }
}