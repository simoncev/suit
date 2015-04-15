/**
 * Copyright© Steven Dobay 2015
 */
package suit

import javax.swing.text.Document

/**
 * @author Steven Dobay
 */
abstract class PasswordField_(private val txt: String = "")
  extends Widget_ {
  private val field = new PasswordField()
  field.password = txt

  def pack() = field

  val password = Property[String](field.password = _)

  val text = Property[String](field.text = _)

  val document = Property[Document](field.document = _)

  val caretPosition = Property[Int](field.caretPosition = _)

  val onChange = Property[ChangeEvent => Unit](field.changeEvents += _)
}