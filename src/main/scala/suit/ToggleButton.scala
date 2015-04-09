/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.JToggleButton

/**
 * @author Steven Dobay
 */
case class ToggleButton() extends Widget {
  private val button = new JToggleButton

  button.putClientProperty ("scala-frame-wrapper", this)

  def text = button.getText
  def text_=(t: String) = button.setText(t)

  def doClick() = button.doClick

  def wrapped = button

  def className = "ToggleButton"
}
