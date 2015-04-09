/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
abstract class PasswordField_(private val txt: String = "")
  extends Widget_ {
  private val field = new PasswordField(txt)

  def pack() = field

  val text = Property[String](field.text = _)

  val onEdit = Property[EditEvent => Unit](field.onEdit(_))
}
