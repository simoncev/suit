/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}
import javax.swing.{JScrollPane, JList, ListSelectionModel, JComponent}

import scala.reflect.ClassTag

/**
 * @author Steven Dobay
 * Component for visualizing lists.
 */
case class ListView[T <: AnyRef](items: Vector[T])
                                (implicit CT: ClassTag[T])
   extends Bindable[Array[T]] {

  /**
   * @return with an array containing the items.
   */
  val values = items.toArray

  /**
   * @param ix : the index
   * @return with the item at the given index.
   */
  def value(ix: Int) = items(ix)

  private var list = new JList[T](values)

  list.putClientProperty("suit-wrapper", this)

  /**
   * Adds scrollpane to the list
   */
  def addScrollPane() = {
    val listScroller = new JScrollPane(list)
    this
  }

  /**
   * Sets the visible rowcount
   * @param n
   */
  def visibleRowCount_=(n: Int) = {
    list.setVisibleRowCount(n)
    this
  }

  def withVisibleRowCount(n: Int) = {
    list.setVisibleRowCount(n)
    this
  }

  /**
   * @return with the number of visible rows
   */
  def visibleRowCount = list.getVisibleRowCount

  /**
   * Sets the selection mode
   * @param mode
   */
  def selectionMode_=(mode: Int) =
    list.setSelectionMode(mode)

  def withSelectionMode(mode: Int) = {
    selectionMode = mode
    this
  }

  /**
   * @return with the selection mode
   */
  def selectionMode = list.getSelectionMode

  protected def setValue(values: Array[T]) =
     for(v <- values) list.setSelectedValue(v, false)

  /**
   * Sets the layout orientation
   * @param mode
   */
  def layoutOrientation_=(mode: Int) = {
    list.setLayoutOrientation(mode)
    this
  }

  /**
   * @return with the layout's orientation
   */
  def layoutOrientation = list.getLayoutOrientation

  /**
   * Sets the fixed cell-size
   * @param d
   */
  def fixedSize(d: Size) = {
    list.setFixedCellHeight(d.getHeight.toInt)
    list.setFixedCellWidth(d.getWidth.toInt)
    this
  }

  /**
   * Adds a selection listener to this list view
   *
   * @param proc
   */
  def onSelection(proc: (ListView[T], Int, Int) => Unit) = {
    list.addListSelectionListener(new ListSelectionListener {
      override def valueChanged(e: ListSelectionEvent): Unit =
        proc(e.getSource()
              .asInstanceOf[JComponent]
              .getClientProperty("suit-wrapper")
              .asInstanceOf[ListView[T]],
             e.getFirstIndex, e.getLastIndex)
    })
    this
  }

  /**
   * Adds action handling to the list
   * @param proc
   */
  override def onAction(proc: ActionEvent => Unit): Unit =
    list.addMouseListener(
      new MouseHandler().handleClick(e => proc(e.toActionEvent)).create
    )

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = list

  /**
   * Sets the
   * @param l
   */
  protected[suit] def setWrapped(l: JList[T]) = {
    list = l
  }

  /**
   * @return with the name of the class
   */
  def className = "ListView"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = SelectionEvent
  protected type ChangeListenerType = ListSelectionListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new ChangeListenerType {
      override def valueChanged(e: ListSelectionEvent): Unit =
       proc(SelectionEvent(e))
    }
    list.addListSelectionListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    list.removeListSelectionListener(l)

  def componentValue() =
    list.getSelectedValuesList.toArray.asInstanceOf[Array[T]]
}

/**
 * Static modes
 */
object ListView {
  /**
   * Selectionmodels
   */
  val SINGLE_INTERVAL_SELECTION = ListSelectionModel.SINGLE_INTERVAL_SELECTION
  val SINGLE_SELECTION = ListSelectionModel.SINGLE_SELECTION
  val MULTIPLE_INTERVAL_SELECTION =
    ListSelectionModel.MULTIPLE_INTERVAL_SELECTION

  /**
   * Wrap strategies
   */
  val HORIZONTAL_WRAP = JList.HORIZONTAL_WRAP
  val VERTICAL_WRAP = JList.VERTICAL_WRAP
  val VERTICAL = JList.VERTICAL

  /**
   * @param l
   * @return with a new ListView wrapping the given JList
   */
  def apply[T <: AnyRef](l: JList[T])(implicit CT: ClassTag[T]) = {
    val lw = new ListView[T](Vector[T]())
    lw.setWrapped(l)
    lw
  }
}