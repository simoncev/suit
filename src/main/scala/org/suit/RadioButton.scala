/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.{event, JRadioButton}
import javax.swing.event.ChangeListener

/**
 * @author Steven Dobay
 */
case class RadioButton(private val initTitle: String)
   extends Widget {
  private val button = new JRadioButton(initTitle)

  button.putClientProperty("suit-wrapper", this)

  def isSelected() = button.isSelected

  def select() = button.setSelected(true)

  def deselect() = button.setSelected(false)

  def text = button.getText

  def text_=(t: String) = button.setText(t)

  /**
   * Handler of change events.
   */
  object changeEvents
    extends EventManager[ChangeEvent => Unit, ChangeListener]() {

    /**
     * Create a new listener
     * @param proc
     * @return with the created listener
     */
    protected def createAndAddListener(proc: ChangeEvent => Unit) = {
      val listener = new ChangeListener {
        override def stateChanged(ev: event.ChangeEvent): Unit =
          proc(ChangeEvent(ev))
      }
      button.addChangeListener(listener)
      listener
    }

    /**
     * Removes the listener from the registration places
     * @param listener
     */
    protected def removeListener(listener: ChangeListener) =
      button.removeChangeListener(listener)
  }

  /**
   * @return with the name of the class
   */
  def className = "RadioButton"

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = button
}
