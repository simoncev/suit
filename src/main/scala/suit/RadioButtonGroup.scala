/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.ActionListener
import javax.swing.JPanel
import javax.swing.event.ChangeListener

/**
 * @author Steven Dobay
 */
case class RadioButtonGroup()
  extends Bindable[Array[Boolean]] with Container {
  private val panel = new JPanel
  private var buttons = new Array[RadioButton](5)

  def +=(btn: RadioButton) = {
    panel.add(btn.wrapped)
    buttons = Array.concat(buttons, Array(btn))
  }

  def withButton(btn: RadioButton) = {
    panel.add(btn.wrapped)
    buttons = Array.concat(buttons, Array(btn))
  }

  def ++=(btns: RadioButton*) = {
    for(btn <- btns) panel.add(btn.wrapped)
    buttons = Array.concat(buttons, btns.toArray)
  }

  def withButtons(btns: RadioButton*) = {
    for(btn <- btns) panel.add(btn.wrapped)
    buttons = Array.concat(buttons, btns.toArray)
    this
  }

  def -=(btn: RadioButton) = {
    panel.remove(btn.wrapped)
    buttons = buttons.filter(_ != btn)
  }

  def getButtons = buttons

  def selected: Option[String] = buttons.find(_.isSelected) match {
    case Some(btn) => Some(btn.asInstanceOf[RadioButton].text)
    case _         => None
  }

  def className = "RadioButtonGroup"

  protected[suit] def wrapped = panel

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
    buttons.foreach(_.wrapped.addActionListener(listener))
    listener
  }

  protected def removeChangeListener(l: ChangeListenerType) =
    buttons.foreach(_.wrapped.removeActionListener(l))

  def componentValue() = buttons.map(_.isSelected())

  protected[suit] def wrappedContainer = panel
}
