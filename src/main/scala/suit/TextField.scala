/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{InputMethodEvent, InputMethodListener}
import javax.swing.JTextField

/**
 * @author Steven Dobay
 */
case class TextField(private val initText: String = "")
   extends Widget with Bindable[String] {

  private val field = new JTextField(initText)

  field.putClientProperty ("scala-frame-wrapper", this)

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

  protected def onChange(v: HolderOf[String]) =
   onEdit(_ => v.value = text)

  def className = "TextField"

  def wrapped = field

}
