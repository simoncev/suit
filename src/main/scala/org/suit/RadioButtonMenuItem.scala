/**
 * Copyright© Steven Dobay 2015
 * LICENSE: LGPLv3.0 - https://github.com/stevendobay/suit/blob/master/LICENSE
 */
package org.suit

import java.awt.event
import java.awt.event.ActionListener
import javax.swing.JRadioButtonMenuItem


/**
 * @author Steven Dobay
 */
case class RadioButtonMenuItem(private val initTitle: String,
                               private val isSelected: Boolean = false)
  extends MenuItem(new JRadioButtonMenuItem(initTitle, isSelected)) {

  private def radioMenuItem = menuItem.asInstanceOf[JRadioButtonMenuItem]

  /**
   * @return with the title of the menuitem
   */
  def title = radioMenuItem.getText

  /**
   * Sets the menuitem's title
   * @param t
   */
  def title_=(t: String) = radioMenuItem.setText(t)

  /**
   * @return true if the menuitem is checked
   */
  def selected() = radioMenuItem.isSelected

  /**
   * Radios the menuitem
   */
  def select() = radioMenuItem.setSelected(true)

  /**
   * Unselects the button
   */
  def unselect() = radioMenuItem.setSelected(false)

  /**
   * @return with the name of the class
   */
  def className = "RadioButtonMenuItem"

  /**
   * @param proc
   */
  override def onAction(proc: ActionEvent => Unit): Unit =
    radioMenuItem.addActionListener(new ActionListener {
      override def actionPerformed(e: event.ActionEvent): Unit =
        proc(ActionEvent(e))
    })
}