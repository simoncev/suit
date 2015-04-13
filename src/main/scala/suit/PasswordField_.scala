/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
abstract class PasswordField_(private val txt: String = "")
  extends Widget_ {
  private val field = new PasswordField()
  field.password = txt

  def pack() = field

  val password = Property[String](field.password = _)

  val onEdit = Property[EditEvent => Unit](field.onEdit(_))

  val onChange = Property[ChangeEvent => Unit](field.changeEvents += _)
}
