/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
case class TableChangeEvent(
 private val cSource: Component,
 private val cId: Option[Int],
 private val cWhen: Long,
 private val cChangeCount: Option[Long],
 column: Int,
 firstRow: Int,
 lastRow: Int
) extends ChangeEvent(cSource, cId, cWhen, cChangeCount)

object TableChangeEvent {
  def apply(e: javax.swing.event.TableModelEvent, when: Long)
   : TableChangeEvent =
    TableChangeEvent(e.getSource.asInstanceOf[JComponent]
                      .getClientProperty("suit-wrapper")
                      .asInstanceOf[Component],
                     None, when, None,
                     e.getColumn, e.getFirstRow, e.getLastRow)
}
