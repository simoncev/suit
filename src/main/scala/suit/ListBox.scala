/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{ItemEvent => JItemEvent, ItemListener}
import javax.swing.JComboBox

/**
 * @author Steven Dobay
 *
 * Simple component for showing
 */
case class ListBox[T](private val initItems: T*)
   extends Bindable[Option[T]] {
  private val list = new JComboBox[T]()
  for(item <- initItems) list.addItem(item)

  /**
   * Handler of ListBoxes' items
   */
  object items {
    /**
     * Adds a new item.
     * @param item
     */
    def +=(item: T) = list.addItem(item)

    /**
     * Removes the given item.
     * @param item
     */
    def -=(item: T) = list.removeItem(item)

    /**
     * Removes an item by index.
     * @param itemIndex
     */
    def -=(itemIndex: Int) = list.removeItem(itemIndex)

    /**
     * Adds multiply items at once.
     * @param items
     */
    def ++=(items: T*) = for(item <- items) list.addItem(item)

    /**
     * Removes multiply items at once.
     * @param items
     */
    def --=(items: T*) = for(item <- items) list.removeItem(item)

    /**
     * @return with the selected item.
     */
    def selected() = selectedItem

    /**
     * @return with the number of items.
     */
    def count() = list.getItemCount
  }

  /**
   * @return with the selected item's index.
   */
  def selectedIndex = list.getSelectedIndex

  /**
   * Sets the selected item by its index.
   * @param ix
   */
  def selectedIndex_=(ix: Int) = list.setSelectedIndex(ix)

  /**
   * @return with the selected item.
   */
  def selectedItem = list.getSelectedItem match {
    case null => None
    case x    => Some(x.asInstanceOf[T])
  }

  /**
   * Sets the selected item.
   * @param item
   */
  def selectedItem_=(item: T) = list.setSelectedItem(item)

  /**
   * @return with an array of the selected objects.
   */
  def selectedObjects(implicit e: scala.reflect.ClassTag[T]) =
    list.getSelectedObjects.map(_.asInstanceOf[T]).toArray

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = list

  /**
   * @return with the name of the class
   */
  def className = "ListBox"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = ItemEvent[T]
  protected type ChangeListenerType = ItemListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new ChangeListenerType {
      override def itemStateChanged(e: JItemEvent): Unit =
        proc(ItemEvent[T](e))
    }
    list.addItemListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    list.removeItemListener(l)

  /**
   * @return with the selected objects.
   */
  def componentValue() = selectedItem
}
