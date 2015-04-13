/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{InputMethodEvent, InputMethodListener}
import javax.swing.JPasswordField

/**
 * @author Steven Dobay
 */
case class PasswordField()
  extends Widget with Bindable[Array[Char]] with Stateful[Array[Char]] {

  private val field = new JPasswordField()

  def password = field.getPassword

  def password_=(pass: String) = field.setText(pass)

  def onEdit(proc: EditEvent => Unit) = {
    field.addInputMethodListener(new InputMethodListener {
      override def caretPositionChanged(e: InputMethodEvent): Unit =
        proc(EditEvent(e, true))
      override def inputMethodTextChanged(e: InputMethodEvent): Unit =
        proc(EditEvent(e, false))
    })
  }

  def withEditAction(e: EditEvent => Unit) = {
    onEdit(e)
    this
  }

  def className = "PasswordField"

  protected[suit] def wrapped = field

  /**
   * Section of Stateful's methods
   */
  protected type ChangeEventType = EditEvent
  protected type ChangeListenerType = InputMethodListener

  protected def createAndAddChangeListener(proc: ChangeEventType => Unit) = {
    val listener = new ChangeListenerType {
      override def caretPositionChanged(event: InputMethodEvent): Unit =
        proc(EditEvent(event, true))
      override def inputMethodTextChanged(event: InputMethodEvent): Unit =
        proc(EditEvent(event, false))
    }
    field.addInputMethodListener(listener)
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
   field.removeInputMethodListener(l)

  def bindValue() = field.getPassword
}
