/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{ActionListener, InputMethodEvent, InputMethodListener}
import javax.swing.{JComponent, JPasswordField, JTextField}

/**
 * @author Steven Dobay
 */
case class PasswordField(private val initText: String = "")
  extends Widget with Bindable[Array[Char]] with Stateful[Array[Char]] {

  private val field = new JPasswordField(initText)

  def text = field.getPassword

  def text_=(t: String) = field.setText(t)

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

  protected type EventType = EditEvent
  protected type ListenerType = InputMethodListener

  protected def createAndGetListener(proc: EventType => Unit) = {
    val listener = new ListenerType {
      override def caretPositionChanged(event: InputMethodEvent): Unit =
        proc(EditEvent(event, true))
      override def inputMethodTextChanged(event: InputMethodEvent): Unit =
        proc(EditEvent(event, false))
    }
    field.addInputMethodListener(listener)
    listener
  }

  protected def removeListener(l: ListenerType) =
   field.removeInputMethodListener(l)

  protected def onChangeDoBind(v: HolderOf[Array[Char]]) =
    onEdit(_ => v.value = text)

  def className = "PasswordField"

  protected[suit] def wrapped = field

}
