/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JRadioButton

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
   * @return with the name of the class
   */
  def className = "RadioButton"

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = button
}
