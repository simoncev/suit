/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JTree
import javax.swing.tree.{TreePath => JTreePath}

/**
 * @author Steven Dobay
 */
case class TreeView() extends Component {
  private val tree = new JTree()

  def isSpecialized = false

  tree.putClientProperty ("scala-frame-wrapper", this)

  /**
   * @param path
   */
  def += (path: JTreePath) = tree.addSelectionPath(path)

  def += (ix: Int) = tree.addSelectionRow(ix)

  /**
   * @param paths
   */
  def ++= (paths: Array[JTreePath]) = tree.addSelectionPaths(paths)

  def ++= (indexes: Array[Int]) = tree.addSelectionRows(indexes)

  def -= (path: JTreePath) = tree.removeSelectionPath(path)

  def -= (ix: Int) = tree.removeSelectionRow(ix)

  def --= (paths: Array[JTreePath]) = tree.removeSelectionPaths(paths)

  def --= (indexes: Array[Int]) = tree.removeSelectionRows(indexes)

  /**
   * @param path
   * @return with self
   */
  def withPath(path: JTreePath) = {
    this.+=(path)
    this
  }

  /**
   * @param paths
   * @return with self
   */
  def withPaths(paths: Array[JTreePath]) = {
    this.++=(paths)
    this
  }

  /**
   * @param path
   */
  def collapsePath(path: JTreePath) = tree.collapsePath(path)

  /**
   * @param path
   */
  def expandPath(path: JTreePath) = tree.expandPath(path)

  /**
   * @param ix
   */
  def collapseRow(ix: Int) = tree.collapseRow(ix)

  /**
   * @param ix
   */
  def expandRow(ix: Int) = tree.expandRow(ix)

  /**
   * @param path
   */
  def leadSelectionPath(path: JTreePath) =
    tree.setLeadSelectionPath(path)

  /**
   * @param path
   * @return true if the path is collapsed
   */
  def isCollapsed(path: JTreePath) = tree.isCollapsed(path)

  /**
   * @param ix
   * @return true if the path is collapsed
   */
  def isCollapsed(ix: Int) = tree.isCollapsed(ix)

  /**
   * @param path
   * @return with true if the path is visible
   */
  def isVisible(path: JTreePath) = tree.isVisible(path)

  /**
   * @param path
   */
  def makeVisible(path: JTreePath) = tree.makeVisible(path)

  def closestPathTo(x: Int, y: Int) =
    tree.getClosestPathForLocation(x, y)

  def closestRowTo(x: Int, y: Int) =
    tree.getClosestRowForLocation(x, y)

  def isEditable() = tree.isEditable
  def isEditing() = tree.isEditing

  def isEditable(path: JTreePath) =
    tree.isPathEditable(path)
  
  def wrapped = tree
  def className = "TreeView"
}
