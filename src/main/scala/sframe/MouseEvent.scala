/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
case class MouseEvent(private var cSource: JComponent, private val cId: Int,
                      private val cWhen: Long, private val cModifiers: Int,
                      private val cX: Int, private val cY: Int,
                      private val cClickCount: Int,
                      private val cPopupTrigger: Boolean)
 extends java.awt.event.MouseEvent(cSource.asInstanceOf[JComponent],
                                   cId, cWhen, cModifiers,
                                   cX, cY, cClickCount, cPopupTrigger) {

  /**
   * Converting to the new component to link it in the source
   */
  private val compSource = cSource.getClientProperty("scala-frame-wrapper")
                              .asInstanceOf[Component]

  /**
   * @return with the wrapper source
   */
  override def getSource = compSource

  /**
   * @return with a new actionevent
   */
  def toActionEvent = new ActionEvent(cSource, cId, cWhen, cModifiers)
}

object MouseEvent {
  /**
   * @param e
   * @return with a new MouseEvent created from a java.awt.event.MouseEvent
   */
  def apply(e: java.awt.event.MouseEvent) =
   new MouseEvent(e.getSource.asInstanceOf[JComponent],
                  e.getID, e.getWhen, e.getModifiers,
                  e.getX, e.getY, e.getClickCount, e.isPopupTrigger)
}
