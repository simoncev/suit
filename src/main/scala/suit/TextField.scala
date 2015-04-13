/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{InputMethodEvent, InputMethodListener}
import javax.swing.JTextField

/**
 * @author Steven Dobay
 * Simple component to get text-based inputs.
 */
case class TextField(private val initText: String = "")
   extends Bindable[String] with Widget {

  private val field = new JTextField(initText)

  field.putClientProperty ("suit-wrapper", this)

  def text = field.getText

  def text_=(t: String) = field.setText(t)

  def className = "TextField"

  protected[suit] def wrapped = field

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = EditEvent
  protected type ChangeListenerType = InputMethodListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit): ChangeListenerType = {
    val iml = new ChangeListenerType {
      override def caretPositionChanged(ev: InputMethodEvent): Unit =
       proc(EditEvent(ev, true))
      override def inputMethodTextChanged(ev: InputMethodEvent): Unit =
       proc(EditEvent(ev, false))
    }
    field.addInputMethodListener(iml)
    iml
  }

  protected def removeChangeListener(l: ChangeListenerType): Unit =
    field.removeInputMethodListener(l)

  def componentValue() = text
}
