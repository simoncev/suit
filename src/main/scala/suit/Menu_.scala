/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
abstract class Menu_(private val txt: String) {
  private val menu = new Menu(txt)

  def pack() = menu

  val title = Property[String](menu.title = _)

  val newItem = Property[MenuItem](menu += _)

  val newItems = Property[List[MenuItem]](for(i <- _) menu += i)

  val onAction = Property[ActionEvent => Unit](menu.onAction_=(_))
}
