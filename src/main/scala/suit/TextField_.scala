/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * For properties' meaning go to TextField
 */
abstract class TextField_(private val txt: String = "")
  extends Widget_ {

  private val field = new TextField(txt)

  def pack() = field

  val text = Property[String](field.text = _)

  val onEdit = Property[ChangeEvent => Unit](field.changeEvents += _)

  val onChange = Property[ChangeEvent => Unit](field.changeEvents += _)
}
