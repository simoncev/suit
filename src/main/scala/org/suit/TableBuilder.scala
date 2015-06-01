/**
 * Copyright© by Steven Dobay on 2015
 */
package org.suit

import scala.language.existentials

import javax.swing.event.{TableModelEvent, TableModelListener}
import javax.swing.{DefaultCellEditor, JTable}
import javax.swing.table.AbstractTableModel

/**
 * @author Steven Dobay
 */
trait TableBuilder {
  private var table: Option[JTable] = None
  private var checkBoxColumns: List[(Int, CheckBox)] = List()
  private var listBoxColumns: List[(Int, ListBox[Any])] = List()
  private var textFieldColumns: List[(Int, TextField)] = List()
  private var changeListener: Option[(AnyRef, Int, Int) => Unit] = None

  var columnClasses: List[Class[_]] = List()
  var columnNames: List[String] = List()
  var rows = 0
  var columns = 0

  /**
   * Builds the table and its abstract model.
   */
  def buildTable() = {
    table = Some(new JTable(createModel()))

    for((ix, cb) <- checkBoxColumns)
      table.get.getColumnModel.getColumn(ix)
               .setCellEditor(new DefaultCellEditor(cb.wrapped))

    for((ix, lb) <- listBoxColumns)
      table.get.getColumnModel.getColumn(ix)
        .setCellEditor(new DefaultCellEditor(lb.wrapped))

    for((ix, tf) <- textFieldColumns)
      table.get.getColumnModel.getColumn(ix)
               .setCellEditor(new DefaultCellEditor(tf.wrapped))
    
    Table(table.get)
  }

  /**
   * Creates a new tablemodel based on the given
   * procedures and values.
   * @return with a new tablemodel.
   */
  private def createModel() = new AbstractTableModel {
    override def getRowCount: Int = rows

    override def getColumnCount: Int = columns

    override def getValueAt(i: Int, i1: Int): AnyRef = {
      val v = valueAt(i, i1)
      if(v.isInstanceOf[Any]) v.toString else v.asInstanceOf[AnyRef]
    }

    override def isCellEditable(rix: Int, cix: Int) = isEditableAt(rix, cix)

    override def getColumnName(cix: Int) =
      if(columnNames.isEmpty) "" else columnNames(cix)

    override def setValueAt(value: AnyRef, row: Int, column: Int) = {
      super.fireTableDataChanged()
      super.fireTableCellUpdated(row, column)
      if(changeListener.isDefined) changeListener.get(value, row, column)
    }

    override def getColumnClass(columnIx: Int): Class[_] =
      if(columnClasses.isEmpty) classOf[String]
      else columnClasses(columnIx)
  }

  /**
   * @param row
   * @param column
   * @return true if the cell is editable at the given position.
   */
  def isEditableAt(row: Int, column: Int): Boolean

  /**
   * @param row
   * @param column
   * @return with the object at the given position.
   */
  def valueAt(row: Int, column: Int): Any

  /**
   * Event handler for changing a column.
   * @param proc
   */
  def onChange(proc: (AnyRef, Int, Int) => Unit): Unit =
     changeListener = Some(proc)

  /**
   * Sets the column to be rendered by a checkbox.
   *
   * @param columnIx
   * @param checkBox
   */
  def createCheckBoxColumn(columnIx: Int, checkBox: CheckBox) =
    checkBoxColumns = checkBoxColumns ++ List((columnIx, checkBox))

  /**
   * Sets the column to be rendered by a text field.
   * @param columnIx
   * @param textField
   */
  def createTextFieldColumn(columnIx: Int, textField: TextField) =
    textFieldColumns = textFieldColumns ++ List((columnIx, textField))

  /**
   * Sets the column to be rendered by a listbox (combo box).
   * @param columnIx
   * @param listBox
   */
  def createListBoxColumn(columnIx: Int, listBox: ListBox[Any]) =
    listBoxColumns = listBoxColumns ++ List((columnIx, listBox))
}