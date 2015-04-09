/**
 * Copyright© Steven Dobay 2015
 */
package suit

/**
 * @author Steven Dobay
 * For properties' meaning go to RadioButton
 */
abstract class RadioButton_(private val txt: String)
  extends Widget_ {

  private val btn = new RadioButton(txt)

  def pack() = btn

  val text = Property[String](btn.text = _)

  val selected = Property[Boolean](
    b => if(b) btn.select() else btn.deselect()
  )
}
