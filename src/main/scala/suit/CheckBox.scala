/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.ActionListener
import javax.swing.JCheckBox

/**
 * @author Steven Dobay
 */
case class CheckBox(private val initIsChecked: Boolean = false)
 extends Bindable[Boolean]  {

  private val btn = new JCheckBox()
  btn.setSelected(initIsChecked)

  def isSelected() = btn.isSelected

  def select() = btn.setSelected(true)

  def deselect() = btn.setSelected(false)

  def text = btn.getText

  def text_=(t: String) = btn.setText(t)

 protected[suit] def wrapped = btn

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

 def bindValue() = isSelected()
}
