/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
case class MouseEvent(private val cSource: Component,
                      private val cId: Int,
                      private val cWhen: Long,
                      x: Int, y: Int,
                      clickCount: Int,
                      popupTrigger: Boolean)
  extends Event(cSource, cId, cWhen) {
  /**
   * @return with a new actionevent
   */
  def toActionEvent = new ActionEvent(source, id, when)
}

object MouseEvent {
  /**
   * @param e
   * @return with a new MouseEvent created from a java.awt.event.MouseEvent
   */
  def apply(e: java.awt.event.MouseEvent) =
   new MouseEvent(e.getSource.asInstanceOf[JComponent]
                             .getClientProperty("scala-frame-wrapper")
                             .asInstanceOf[Component],
                  e.getID, e.getWhen,
                  e.getX, e.getY, e.getClickCount, e.isPopupTrigger)
}
