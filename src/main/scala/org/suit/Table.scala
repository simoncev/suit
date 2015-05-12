/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.Color
import javax.swing.event.{TableModelEvent, TableModelListener}
import javax.swing.{JScrollPane, JTable}

/**
 * @author Steven Dobay
 */
case class Table(private val initRows: Int,
                 private val initColumns: Int,
                 private val columnNames: Array[Object])
   extends Bindable[Array[Array[AnyRef]]] {

  private var data = Array.ofDim[AnyRef](initRows, initColumns)
  private val table = new JTable(data, columnNames)
  private val scrollPane = new JScrollPane(table)

  table.putClientProperty("suit-wrapper", this)

  def rows() = table.getRowCount
  def columns() = table.getColumnCount

  def tableData() = data

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

  def editingColumn = table.getEditingColumn

  protected def setValue(v: Array[Array[AnyRef]]) =
    for(i <- 0 to data.size)
      for(j <- 0 to data.size)
        valueAt(v(i)(j), i, j)

  /**
   * @param ix
   */
  def editingColumn(ix: Int) =
    table.setEditingColumn(ix)

  def editingRow = table.getEditingRow

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

  protected[suit] def wrapped = table
  def className = "Table"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = TableChangeEvent
  protected type ChangeListenerType = TableModelListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new ChangeListenerType {
      override def tableChanged(e: TableModelEvent): Unit =
        proc(TableChangeEvent(e, System.currentTimeMillis()))
    }
    table.getModel.addTableModelListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    table.getModel.removeTableModelListener(l)

  def componentValue() = data
}
