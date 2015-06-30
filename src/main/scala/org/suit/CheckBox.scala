/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.event.ActionListener
import javax.swing.JCheckBox

/**
 * @author Steven Dobay
 */
case class CheckBox(private val initIsChecked: Boolean = false)
 extends Bindable[Boolean]  {

  private val btn = new JCheckBox()

  btn.putClientProperty("suit-wrapper", this)

  btn.setSelected(initIsChecked)

 /**
  * @return with true if the button is checked
  */
  def isSelected() = btn.isSelected

 /**
  * Set the button to be checked.
  */
  def select() = btn.setSelected(true)

 /**
  * De-check the button
  */
  def deselect() = btn.setSelected(false)

 /**
  * Builder to set the text of the button
  */
  def withText(txt: String) = {
    btn.setText(txt)
    this
  }

 /**
  * @return with the text of the button
  */
  def text = btn.getText

 /**
  * Sets the text of the button
  */
  def text_=(t: String) = btn.setText(t)

  protected def setValue(v: Boolean) =
    if(v) select() else deselect()

 /**
  * @return with a pointer to the wrapped JComponent
  */
  protected[suit] def wrapped = btn

 /**
  * @return with the name of the class
  */
  def className = "CheckBox"

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
   btn.addActionListener(listener)
   listener
  }

 protected def removeChangeListener(l: ChangeListenerType) =
  btn.removeActionListener(l)

 /**
  * @return with the component's value
  */
 def componentValue() = isSelected()
}
