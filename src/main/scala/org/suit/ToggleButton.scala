/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.event.ActionListener
import javax.swing.JToggleButton

/**
 * @author Steven Dobay
 */
case class ToggleButton()
   extends Widget with Bindable[Boolean] with Stateful[Boolean] {
  private val button = new JToggleButton

  button.putClientProperty ("suit-wrapper", this)

  def text = button.getText
  def text_=(t: String) = button.setText(t)

  def selected = button.isSelected

  def selected_=(b: Boolean) = button.setSelected(b)

  def doClick() = button.doClick

  protected[suit] def wrapped = button

  def className = "ToggleButton"

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = ChangeEvent
  protected type ChangeListenerType = ActionListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new ChangeListenerType {
      override def actionPerformed(e: java.awt.event.ActionEvent): Unit =
        proc(ChangeEvent(e))
    }
    button.addActionListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    button.removeActionListener(l)

  def componentValue() = button.isSelected
}
