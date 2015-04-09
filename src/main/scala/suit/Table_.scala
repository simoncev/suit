/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.Color

/**
 * @author Steven Dobay
 */
case class Table_(private val initRows: Int,
                  private val initColumns: Int,
                  private val columnNames: Array[Object])
  extends Component_ {

  private val table = new Table(initRows, initColumns, columnNames)

  def pack() = table

  val valueAt = Property[(AnyRef, Int, Int)](t =>
    table.valueAt(t._1, t._2, t._3)
  )

  val editingColumn = Property[Int](table.editingColumn(_))

  val editingRow = Property[Int](table.editingRow(_))

  val enableCellSelection = Property[Boolean](
   if(_) table.enableCellSelection()
   else table.disableCellSelection()
  )

  val enableColumnSelection = Property[Boolean](
    if(_) table.enableColumnSelection()
    else table.disableColumnSelection()
  )

  val selectionBackground =
    Property[Color](table.selectionBackground(_))

  val selectionForeground =
    Property[Color](table.selectionForeground(_))
}
