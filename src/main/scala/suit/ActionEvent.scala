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
case class ActionEvent(private var cSource: JComponent, private val cId: Int,
                       private val cWhen: Long, private val cModifiers: Int)
  extends JActionEvent(cSource, cId, "", cWhen, cModifiers) {

   private val compSource = cSource.getClientProperty("scala-frame-wrapper")
                               .asInstanceOf[Component]

  override def getSource = compSource

  def toMouseEvent =
    new MouseEvent(cSource, cId, cWhen, cModifiers, -1, -1, 1, false)

  def toAWTActionEvent: JActionEvent =
    new JActionEvent(cSource, cId, "", cWhen, cModifiers)
}

object ActionEvent {
  def apply(e: java.awt.event.ActionEvent) =
   new ActionEvent(e.getSource.asInstanceOf[JComponent],
                   e.getID, e.getWhen, e.getModifiers)
}