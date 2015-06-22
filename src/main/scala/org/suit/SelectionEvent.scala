/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
case class SelectionEvent(private val cSource: Component,
                          private val cId: Option[Int],
                          private val cWhen: Long,
                          objects: Array[AnyRef])
 extends ChangeEvent(cSource, cId, cWhen, Some(objects.size))

object SelectionEvent {
  /**
    * @param e - javax.swing.event.ListSelectionEvent
    * @return with a new SelectionEvent
    */
  def apply(e: javax.swing.event.ListSelectionEvent): SelectionEvent = {
    val list = e.getSource.asInstanceOf[JComponent]
                .getClientProperty("suit-wrapper")
                .asInstanceOf[ListView[_ <: AnyRef]]
    SelectionEvent(list, None, System.currentTimeMillis(),
      list.items.slice(e.getFirstIndex, e.getLastIndex).toArray)
  }
}