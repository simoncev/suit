/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JComponent
import java.awt.event.{ItemEvent => JItemEvent}

/**
 * @author Steven Dobay
 */
case class ItemEvent[T](private val cSource: Component,
                        private val cId: Option[Int],
                        private val cWhen: Long,
                        private val cChangeCount: Option[Long],
                        selectedItem: Option[T],
                        newSelection: Boolean)
 extends ChangeEvent(cSource, cId, cWhen, cChangeCount)

object ItemEvent {
  import helpers.ComponentHelpers._
  def apply[T](e: JItemEvent): ItemEvent[T] =
    new ItemEvent[T](e.getSource.asInstanceOf[JComponent].readSuitComponent,
                     Some(e.getID), System.currentTimeMillis(), None,
                     if(e.getItem != null) Some(e.getItem.asInstanceOf[T])
                     else None,
                     if(e.getStateChange == JItemEvent.DESELECTED) false
                     else true)
}