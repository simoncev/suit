/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 *
 * Dispatcher for ListBox.
 */
abstract class ListBox_[T](items: T*) extends Widget_ {
  private val list = new ListBox[T]()
  for(item <- items) list.items += item

  val onChange = Property[ItemEvent[T] => Unit](list.changeEvents += _)

  val newItem = Property[T](list.items += _)

  val newItems = Property[List[T]](for(item <- _) list.items += item)

  val selectedIndex = Property[Int](list.selectedIndex = _)

  val selectedItem = Property[T](list.selectedItem = _)

  def pack() = list
}
