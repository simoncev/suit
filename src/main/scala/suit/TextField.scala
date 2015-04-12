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
   extends Widget with Bindable[String] with Stateful[String] {

  /**
   * The super type's representation
   */
  protected type ChangerEvent = EditEvent
  protected type ListenerType = InputMethodListener

  private val field = new JTextField(initText)

  field.putClientProperty ("scala-frame-wrapper", this)

  def text = field.getText

  def text_=(t: String) = field.setText(t)

  protected def onChangeDoBind(v: HolderOf[String]) =
   changeEvents += (_ => v.value = text)

  protected def createAndGetListener(proc: EventType => Unit): ListenerType = {
    val iml = new ListenerType {
      override def caretPositionChanged(ev: InputMethodEvent): Unit =
       proc(EditEvent(ev, true).asInstanceOf[EventType])
      override def inputMethodTextChanged(ev: InputMethodEvent): Unit =
       proc(EditEvent(ev, false).asInstanceOf[EventType])
    }
    field.addInputMethodListener(iml)
    iml
  }

  protected def removeListener(l: ListenerType): Unit =
    field.removeInputMethodListener(l)

  def className = "TextField"

  protected[suit] def wrapped = field
}
