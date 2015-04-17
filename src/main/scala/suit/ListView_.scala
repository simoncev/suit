/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * For the properties' meaning got to ListView
 */
abstract class ListView_(private val items: AnyRef*) extends Component_ {

  private val view = new ListView(items)

  def pack() = view

  def addScrollPane = view.addScrollPane()

  val visibleRowCount = Property[Int](view.visibleRowCount = _)

  val selectionMode = Property[Int](view.selectionMode = _)

  val layoutOrientation = Property[Int](view.layoutOrientation = _)

  val fixedSize = Property[Size](view.fixedSize(_))

  val onSelection =
    Property[(ListView, Int, Int) => Unit](view.onSelection(_))

  val onAction = Property[ActionEvent => Unit](view.onAction(_))
}
