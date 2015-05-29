/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import java.awt.Graphics
import java.awt.event.ActionListener
import javax.swing.JPanel

/**
 * @author Steven Dobay
 */
case class RadioButtonGroup()
  extends Bindable[String] with Container {

  private val panel = new JPanel {
    override def paintComponent(g: Graphics) = {
      super.paintComponent(g)
      paintObjects(g)
    }
  }

  protected var buttons = new Array[RadioButton](0)
  private var layoutObject: Option[Layout] = None

  panel.putClientProperty("suit-wrapper", this)

  /**
   * Deselects all radio buttons except the given one.
   */
  protected def deselectIfNot(rb: RadioButton) =
    buttons.foreach(x => if(!rb.equals(x)) x.deselect())

  /**
   * Action handler for buttons.
   *
   * @param btn
   * @return with the mouse handler.
   */
  protected def action(btn: RadioButton) =
    new MouseHandler().handleClick(_ => deselectIfNot(btn))

  /**
   * Adds a new radio button.
   * @param btn
   */
  def +=(btn: RadioButton) = {
    panel.add(btn.wrapped)
    btn.mouseEvents += action(btn)
    buttons = Array.concat(buttons, Array(btn))
  }

  /**
   * Adds a sequnce of radio buttons.
   * @param btns
   */
  def ++=(btns: RadioButton*) = {
    for(btn <- btns) {
      btn.mouseEvents += action(btn)
      panel.add(btn.wrapped)
    }
    buttons = Array.concat(buttons, btns.toArray)
  }

  /**
   * Adds a sequnce of radio buttons.
   * @param btns
   * @return with self.
   */
  def withButtons(btns: RadioButton*) = {
    for(btn <- btns) {
      panel.add(btn.wrapped)
      btn.mouseEvents += action(btn)
    }
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
   * @return with the layout.
   */
  def layout = layoutObject

  /**
   * Selects the firt button by title.
   * @param title
   */
  protected def setValue(title: String) =
    buttons.find(_.text == title) match {
      case Some(btn) => btn.select()
      case _         => ()
    }

  /**
   * Sets the layout manager.
   * @param l
   */
  def layout_=(l: Layout) = {
    layoutObject = Some(l)
    panel.setLayout(l.wrapped)
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

  /**
   * @param l
   */
  protected def removeChangeListener(l: ChangeListenerType) =
    buttons.foreach(_.wrapped.removeActionListener(l))

  /**
   * @return with the component's value
   */
  def componentValue() = selected match {
    case Some(str) => str
    case _         => ""
  }

  /**
   * @return with the wrapped container
   */
  protected[suit] def wrappedContainer = panel
}
