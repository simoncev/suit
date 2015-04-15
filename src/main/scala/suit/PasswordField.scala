/**
 * Copyright© Steven Dobay 2015
 */
package suit

import java.awt.event.{InputMethodEvent, InputMethodListener}
import javax.swing.JPasswordField
import javax.swing.text.Document

/**
 * @author Steven Dobay
 */
case class PasswordField()
  extends TextComponentLike[Array[Char]]("") with Widget {

  private val field = new JPasswordField()

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

  /**
   * @return with the text of the component
   */
  def text: String = field.getPassword.toString

  /**
   * Sets the component's text
   * @param str
   */
  def text_=(str: String): Unit =
    field.setText(str)

  /**
   * @return with the password
   */
  def password = field.getPassword

  /**
   * Sets the initial text(or password)
   * @param pass
   */
  def password_=(pass: String) = field.setText(pass)

  /**
   * @return with the name of the class
   */
  def className = "PasswordField"

  /**
   * @return with a pointer to the wrapped JComponent
   */
  protected[suit] def wrapped = field

  /**
   * @return with the component's value(the password)
   */
  def componentValue() = field.getPassword
}
