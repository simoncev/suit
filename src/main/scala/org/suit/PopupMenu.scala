/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JPopupMenu

/**
 * @author Steven Dobay
 *
 * Used to create popup menus for components and container.
 */
case class PopupMenu() extends Component {
  private val popup = new JPopupMenu

  popup.putClientProperty("suit-wrapper", this)

  /**
   * Adds a new menuitem
   */
  def +=(item: MenuItem) =
    popup.add(item.wrapped)

  /**
   * Adds multiple menuitems
   */
  def ++=(items: MenuItem*) =
   for(item <- items) popup.add(item.wrapped)

  /**
   * Removes an item from the menuitems
   */
  def -=(item: MenuItem) = popup.remove(item.wrapped)

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = popup

  /**
   * @return with the name of the class
   */
  def className = "PopupMenu"
}
