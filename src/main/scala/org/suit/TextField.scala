/**
 * Copyright© Steven Dobay 2015
 */
package org.suit

import javax.swing.JTextField
import javax.swing.text.Document

/**
 * @author Steven Dobay
 * Simple component to get text-based inputs.
 */
case class TextField(private val initText: String = "")
   extends TextComponentLike[String](initText) with Widget {

  private val field = new JTextField(initText)

  field.putClientProperty ("suit-wrapper", this)

  /**
   * @return with the swing document
   */
  def document: Document = field.getDocument

  /**
   * Sets the swing document
   * @param doc
   */
  def document_=(doc: Document): Unit =
    field.setDocument(doc)

  /**
   * @return with the caret's index
   */
  def caretPosition: Int = field.getCaretPosition

  /**
   * Sets the caret's position
   * @param pos
   */
  def caretPosition_=(pos: Int): Unit =
    field.setCaretPosition(pos)

  protected def setValue(v: String) =
    field.setText(v)

  /**
   * @return with the text of the component
   */
  def text: String = field.getText()

  /**
   * Sets the component's text
   * @param str
   */
  def text_=(str: String): Unit =
    field.setText(str)

  /**
   * @return with the name of the class
   */
  def className = "TextField"

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = field

  /**
   * @return with the text of this component
   */
  def componentValue() = text
}
