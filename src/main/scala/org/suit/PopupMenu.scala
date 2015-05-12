/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JPopupMenu

/**
 * @author Steven Dobay
 */
case class PopupMenu() extends Component {
  private val popup = new JPopupMenu

  popup.putClientProperty("suit-wrapper", this)

  def +=(item: MenuItem) =
    popup.add(item.wrapped)

  def ++=(items: MenuItem*) =
   for(item <- items) popup.add(item.wrapped)

  def -=(item: MenuItem) = popup.remove(item.wrapped)

  protected[suit] def wrapped = popup

  def className = "PopupMenu"
}
