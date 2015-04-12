/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
case class SelectionEvent(private val cSource: Component,
                          private val cId: Int,
                          private val cWhen: Long,
                          objects: Array[AnyRef])
 extends ChangeEvent(cSource, cId, cWhen, objects.size)

object SelectionEvent {
  /**
    * @param e - javax.swing.event.ListSelectionEvent
    * @return with a new SelectionEvent
    */
  def apply(e: javax.swing.event.ListSelectionEvent): SelectionEvent = {
    val list = e.getSource.asInstanceOf[JComponent]
                .getClientProperty("scala-frame-wrapper")
                .asInstanceOf[ListView]
    SelectionEvent(list, -1, -1,
      list.items.slice(e.getFirstIndex, e.getLastIndex).toArray)
  }
}