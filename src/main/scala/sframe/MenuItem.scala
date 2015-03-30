/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
abstract class MenuItem(protected var menuItem: JComponent)
  extends Component {

  menuItem.putClientProperty("scala-frame-wrapper", this)

  /**
   * @param p
   */
  def setContainer(p: Container) = {}

  /**
   * @return with a pointer to the parent optionally
   */
  override def getContainer: Option[Container] = None

  /**
   * Adds the action-handling to the menuitem.
   * This replacement over @> is required because menus only
   * accepts the instances of MenuItem
   * @param proc
   * @return with a reference to this
   */
  def &>(proc: ActionEvent => Unit) = {
    onAction(proc)
    this
  }

  /**
   * @return with the wrapped menuitem
   */
  def getWrapped = menuItem

  /**
   * @param key
   * @return with the property at the given key
   */
  def property(key: String) = menuItem.getClientProperty(key)

  /**
   * Puts the property to the client property
   */
  def property_=(key: String, value: AnyRef) =
    menuItem.putClientProperty(key, value)
}
