/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.ActionListener
import javax.swing.JToggleButton
import javax.swing.event.ChangeListener
import javax.swing.event

/**
 * @author Steven Dobay
 */
case class ToggleButton()
   extends Widget with Bindable[Boolean] with Stateful[Boolean] {
  private val button = new JToggleButton

  button.putClientProperty ("scala-frame-wrapper", this)

  def text = button.getText
  def text_=(t: String) = button.setText(t)

  def doClick() = button.doClick

  protected def onChangeDoBind(v: HolderOf[Boolean]) = {
    button.addChangeListener(new ChangeListener {
      override def stateChanged(e: event.ChangeEvent): Unit =
       v.value = button.isSelected
    })
  }

  protected type EventType = ChangeEvent
  protected type ListenerType = ActionListener

  protected def createAndGetListener(proc: EventType => Unit) = {
    val listener = new ListenerType {
      override def actionPerformed(e: java.awt.event.ActionEvent): Unit =
        proc(ChangeEvent(e))
    }
    button.addActionListener(listener)
    listener
  }

  protected def removeListener(l: ListenerType) =
    button.removeActionListener(l)

  protected[suit] def wrapped = button

  def className = "ToggleButton"
}
