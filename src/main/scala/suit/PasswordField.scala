/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{InputMethodEvent, InputMethodListener}
import javax.swing.JTextField

/**
 * @author Steven Dobay
 */
case class PasswordField(private val initText: String = "") extends Widget {
  private val field = new JTextField(initText)

  def text = field.getText

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

  def className = "PasswordField"

  def wrapped = field

}
