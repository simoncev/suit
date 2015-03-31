/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/scala-frame/blob/master/LICENSE
 */
package sframe

import javax.swing.JComponent

/**
 * @author Steven Dobay
 */
abstract class MenuItem(protected var menuItem: JComponent) extends Component {

  menuItem.putClientProperty("scala-frame-wrapper", this)

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
  def wrapped = menuItem
}
