/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JLabel

/**
 * @author Steven Dobay
 */
case class Label(private val initTitle: String) extends Widget {

  private val label = new JLabel(initTitle)

  label.putClientProperty("suit-wrapper", this)

  /**
   * @return with the label's text
   */
  def text = label.getText

  /**
   * Sets the label's text
   * @param t
   */
  def text_=(t: String) =
    label.setText(t)

  def withText(txt: String) = {
    label.setText(txt)
    this
  }

  /**
   * @return with the name of the class
   */
  def className = "Label"

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = label

  /**
   * @param proc
   */
  override
  def onAction(proc: ActionEvent => Unit): Unit =
    label.addMouseListener(
      new MouseHandler().handleClick(e => proc(e.toActionEvent)).create
    )
}
