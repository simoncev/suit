/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
abstract class PopupMenu_ extends Component_ {
  private val menu = new PopupMenu

  def pack() = menu

  val newItem = Property[MenuItem](menu += _)

  val newItems = Property[List[MenuItem]](for(it <- _) menu += it)
}
