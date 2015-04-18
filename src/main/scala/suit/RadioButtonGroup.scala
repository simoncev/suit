/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.ActionListener
import javax.swing.{JComponent, JPanel}
import javax.swing.event.ChangeListener

/**
 * @author Steven Dobay
 */
case class RadioButtonGroup()
  extends Bindable[Array[Boolean]] with Container {
  private val panel = new JPanel
  private var buttons = new Array[RadioButton](5)

  panel.putClientProperty("suit-wrapper", this)

  /**
   * Adds a new radio button.
   * @param btn
   */
  def +=(btn: RadioButton) = {
    panel.add(btn.wrapped)
    buttons = Array.concat(buttons, Array(btn))
  }

  /**
   * Adds a sequnce of radio buttons.
   * @param btns
   */
  def ++=(btns: RadioButton*) = {
    for(btn <- btns) panel.add(btn.wrapped)
    buttons = Array.concat(buttons, btns.toArray)
  }

  /**
   * Adds a sequnce of radio buttons.
   * @param btns
   * @return with self.
   */
  def withButtons(btns: RadioButton*) = {
    for(btn <- btns) panel.add(btn.wrapped)
    buttons = Array.concat(buttons, btns.toArray)
    this
  }

  /**
   * Removes the given radio button.
   * @param btn
   */
  def -=(btn: RadioButton) = {
    panel.remove(btn.wrapped)
    buttons = buttons.filter(_ != btn)
  }

  /**
   * @return with all components.
   */
  protected[suit] def allComponents() = panel.getComponents

  /**
   * @return with the number of components.
   */
  protected[suit] def componentsSize() =
    panel.getComponentCount

  /**
   * @return with an array of buttons.
   */
  def getButtons = buttons

  def selected: Option[String] = buttons.find(_.isSelected) match {
    case Some(btn) => Some(btn.asInstanceOf[RadioButton].text)
    case _         => None
  }

  /**
   * @return with the name of the class
   */
  def className = "RadioButtonGroup"

  /**
   * @return with a pointer to the wrapped JComponent
   */
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
