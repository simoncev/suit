/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JToggleButton
import javax.swing.event.{ChangeEvent, ChangeListener}

/**
 * @author Steven Dobay
 */
case class ToggleButton()
   extends Widget with Bindable[Boolean] {
  private val button = new JToggleButton

  button.putClientProperty ("scala-frame-wrapper", this)

  def text = button.getText
  def text_=(t: String) = button.setText(t)

  def doClick() = button.doClick

  protected def onChange(v: HolderOf[Boolean]) = {
    button.addChangeListener(new ChangeListener {
      override def stateChanged(e: ChangeEvent): Unit =
       v.value = button.isSelected
    })
  }

  protected[suit] def wrapped = button

  def className = "ToggleButton"
}
