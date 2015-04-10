/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{InputMethodEvent, InputMethodListener}
import javax.swing.{JPasswordField, JTextField}

/**
 * @author Steven Dobay
 */
case class PasswordField(private val initText: String = "")
  extends Widget with Bindable[Array[Char]] {

  private val field = new JPasswordField(initText)

  def text = field.getPassword

  def text_=(t: String) = field.setText(t)

  def onEdit(proc: EditEvent => Unit) = {
    field.addInputMethodListener(new InputMethodListener {
      override def caretPositionChanged(e: InputMethodEvent): Unit =
        proc(EditEvent(e))
      override def inputMethodTextChanged(e: InputMethodEvent): Unit =
        proc(EditEvent(e))
    })
  }

  def withEditAction(e: EditEvent => Unit) = {
    onEdit(e)
    this
  }

  protected def onChange(v: HolderOf[Array[Char]]) =
    onEdit(_ => v.value = text)

  def className = "PasswordField"

  protected[suit] def wrapped = field

}
