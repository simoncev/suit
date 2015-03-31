/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

import javax.swing.event.{ListSelectionEvent, ListSelectionListener}
import javax.swing.{JScrollPane, JList, ListSelectionModel}

/**
 * @author Steven Dobay
 */
case class ListView(items: AnyRef*) extends Component {
  private var list = new JList(items.toArray)

  /**
   * Adds scrollpane to the list
   */
  def addScrollPane() = {
    val listScroller = new JScrollPane(list)
  }

  /**
   * Sets the visible rowcount
   * @param n
   */
  def visibleRowCount_=(n: Int) = list.setVisibleRowCount(n)

  /**
   * @return with the number of visible rows
   */
  def visibleRowCount = list.getVisibleRowCount

  /**
   * Sets the selection mode
   * @param mode
   */
  def selectionMode_=(mode: Int) = list.setSelectionMode(mode)

  /**
   * @return with the selection mode
   */
  def selectionMode = list.getSelectionMode

  /**
   * Sets the layout orientation
   * @param mode
   */
  def layoutOrientation_=(mode: Int) =
    list.setLayoutOrientation(mode)

  /**
   * @return with the layout's orientation
   */
  def layoutOrientation = list.getLayoutOrientation

  /**
   * Sets the fixed cell-size
   * @param d
   */
  def fixedSize(d: Dim) = {
    list.setFixedCellHeight(d.getHeight.toInt)
    list.setFixedCellWidth(d.getWidth.toInt)
  }

  /**
   * Adds a selection listener to this listview
   *
   * @param proc
   */
  def onSelection(proc: (ListView, Int, Int) => Unit) = {
    list.addListSelectionListener(new ListSelectionListener {
      override def valueChanged(e: ListSelectionEvent): Unit =
        proc(ListView(e.getSource), e.getFirstIndex, e.getLastIndex)
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
  def wrapped = list

  /**
   * Sets the
   * @param l
   */
  def setWrapped(l: JList[AnyRef]) = {
    list = l
  }

  /**
   * @return with the name of the class
   */
  def className = "ListView"

  /**
   * @param obj
   * @return true if the wrapped objects equals
   */
  override def equals(obj: Any) =
    if(obj.isInstanceOf[ListView])
      obj.asInstanceOf[Component].wrapped == wrapped
    else if(obj.isInstanceOf[JList[_]])
      obj.asInstanceOf[JList[_]] == wrapped
    else false
}

/**
 * Static modes
 */
object List {
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
  def apply(l: JList[AnyRef]) = {
    val lw = new ListView()
    lw.setWrapped(l)
    lw
  }
}