/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 */
case class CheckBox_(private val isChecked: Boolean = false)
 extends Component_ {

  private val btn = new CheckBox(isChecked)

  val selected = Property[Boolean](
    b => if(b) btn.select() else btn.deselect()
  )

  val text = Property[String](btn.text = _)

  def pack() = btn
}
