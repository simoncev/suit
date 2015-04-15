/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JComponent
import helpers.ComponentHelpers._

/**
 * @author Steven Dobay
 *
 * Event-info data class.
 */
case class MouseMotionEvent(private val cSource: Component,
                            private val cId: Option[Int],
                            private val cWhen: Long,
                            private val cX: Int, private val cY: Int,
                            private val cClickCount: Int,
                            private val cPopupTrigger: Boolean,
                            isMouseMoved: Boolean)
 extends MouseEvent(cSource, cId, cWhen, cX, cY, cClickCount, cPopupTrigger) {

  def isMouseDragged = ! isMouseMoved
}

object MouseMotionEvent {
  def apply(e: java.awt.event.MouseEvent, isMouseMoved: Boolean)
  :MouseMotionEvent =
   MouseMotionEvent(e.getSource.asInstanceOf[JComponent].readSuitComponent,
                    Some(e.getID), e.getWhen, e.getX, e.getY, e.getClickCount,
                    e.isPopupTrigger, isMouseMoved)
}