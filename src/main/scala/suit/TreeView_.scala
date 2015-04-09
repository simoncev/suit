/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * For properties' meaning go to TreeView
 */
abstract class TreeView_ extends Component_ {

  private val tree = new TreeView

  def pack() = tree

  val newPath = Property[TreePath](tree += _)

  val newPaths = Property[List[TreePath]](
    for(path <- _) tree += path
  )

  val collapsedPath = Property[TreePath](tree.collapsePath(_))
  val expandedPath = Property[TreePath](tree.expandPath(_))

  val collapsedRow = Property[Int](tree.collapseRow(_))
  val expandedRow = Property[Int](tree.expandRow(_))

  val leadSelectionPath = Property[TreePath](tree.leadSelectionPath(_))

  val surelyVisiblePath = Property[TreePath](tree.makeVisible(_))
}
