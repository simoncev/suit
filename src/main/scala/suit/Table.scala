/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.Color
import javax.swing.{JScrollPane, JTable}

/**
 * @author Steven Dobay
 */
case class Table(private val initRows: Int,
                 private val initColumns: Int,
                 private val columnNames: Array[Object])
   extends Component {
  private val data = Array.ofDim[AnyRef](initRows, initColumns)
  private val table = new JTable(data, columnNames)
  private val scrollPane = new JScrollPane(table)

  def rows() = table.getRowCount
  def columns() = table.getColumnCount

  def tabelData() = data

  /**
   * @param obj
   * @param x
   * @param y
   */
  def valueAt(obj: AnyRef, x: Int, y: Int) =
    table.setValueAt(obj, x, y)

  /**
   * @param x
   * @param y
   * @return
   */
  def valueAt(x: Int, y: Int) =
    table.getValueAt(x, y)

  def editingColumn() = table.getEditingColumn

  /**
   * @param ix
   */
  def editingColumn(ix: Int) =
    table.setEditingColumn(ix)

  def editingRow() = table.getEditingRow

  /**
   * @param ix
   */
  def editingRow(ix: Int) =
    table.setEditingRow(ix)

  def selectedColumn() = table.getSelectedColumn
  def selectedRow() = table.getSelectedRow

  def selectedColumnsCount() =
    table.getSelectedColumnCount
  def selectedRowsCount() =
    table.getSelectedRowCount

  def selectedRows() = table.getSelectedRows
  def selectedColumns() = table.getSelectedColumns

  /**
   * @return true if cell-selection is enabled
   */
  def isCellSelectionEnabled() =
    table.getCellSelectionEnabled

  def isColumnSelectionEnabled() =
    table.getColumnSelectionAllowed

  def enableCellSelection() =
    table.setCellSelectionEnabled(true)

  def disableCellSelection() =
    table.setCellSelectionEnabled(false)

  def enableColumnSelection() =
    table.setColumnSelectionAllowed(true)

  def disableColumnSelection() =
    table.setColumnSelectionAllowed(false)

  def selectionBackground(bg: Color) =
    table.setSelectionBackground(bg)

  def selectionBackground() =
    table.getSelectionBackground

  def selectionForeground(bg: Color) =
    table.setSelectionForeground(bg)

  def selectionForeground() =
    table.getSelectionForeground

  def isCellSelected(x: Int, y: Int) =
    table.isCellSelected(y, x)

  def isColumnSelected(ix: Int) =
    table.isColumnSelected(ix)

  def isRowSelected(ix: Int) =
    table.isRowSelected(ix)

  def wrapped = table
  def className = "Table"
}
